package com.fracta7.crafter.domain.usecases

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.TagRegistry

@Immutable
data class CraftingStep(
    val itemId: ItemID,
    val recipe: Recipe,
    val totalRequired: Int,
    val usedFromOwned: Int,
    val craftCount: Int,
    val totalProduced: Int,
    val leftover: Int,
    val depth: Int
)

@Immutable
data class CraftingNode(
    val itemId: ItemID,
    val requiredAmount: Int,
    val usedFromOwned: Int,
    val selectedRecipeIndex: Int,
    val selectedRecipe: Recipe?,
    val availableRecipes: List<Recipe>,
    val craftCount: Int,
    val leftover: Int,
    val children: List<CraftingNode>
)

@Immutable
data class CraftingPlan(
    val rawMaterials: Map<ItemID, Int> = emptyMap(),
    val leftovers: Map<ItemID, Int> = emptyMap(),
    val steps: List<CraftingStep> = emptyList(),
    val tree: List<CraftingNode> = emptyList()
)

/**
 * Pure, cycle-safe crafting solver using top-down topological demand aggregation.
 * Aggregates shared intermediate item demand across all targets before calculating
 * craft counts and leftovers, returning raw materials, leftovers, bottom-up steps,
 * and an interactive recipe tree.
 */
fun solveCraftingPlan(
    targets: Map<ItemID, Int>,
    recipeRegistry: RecipeRegistry,
    itemRegistry: ItemRegistry,
    selectedRecipes: Map<ItemID, Int> = emptyMap(),
    ownedInventory: Map<ItemID, Int> = emptyMap(),
    recipePreferences: RecipePreferences = RecipePreferences(),
    tagRegistry: TagRegistry = TagRegistry()
): CraftingPlan {
    val positiveTargets = targets.filterValues { it > 0 }
    if (positiveTargets.isEmpty()) return CraftingPlan()

    val sortedRecipesCache = mutableMapOf<ItemID, List<Recipe>>()
    fun getSortedRecipes(itemId: ItemID): List<Recipe> =
        sortedRecipesCache.getOrPut(itemId) {
            resolvePreferredRecipes(
                recipes = recipeRegistry.getRecipesByResult(itemId),
                preferences = recipePreferences,
                itemRegistry = itemRegistry,
                tagRegistry = tagRegistry
            )
        }

    // Pass 1: Build a strict DAG of active recipes and record post-order traversal.
    val visiting = mutableSetOf<ItemID>()
    val visited = mutableSetOf<ItemID>()
    val postOrder = mutableListOf<ItemID>()
    val activeRecipes = mutableMapOf<ItemID, Recipe>()
    val resolvedRecipeIndices = mutableMapOf<ItemID, Int>()

    fun discover(itemId: ItemID) {
        if (itemId in visited) return
        visiting.add(itemId)

        val item = itemRegistry.getItem(itemId)
        val recipes = getSortedRecipes(itemId)
        if (item != null && item.decomposable && recipes.isNotEmpty()) {
            val preferredIdx = (selectedRecipes[itemId] ?: 0).coerceIn(0, recipes.lastIndex)
            val candidateIndices = listOf(preferredIdx) + recipes.indices.filter { it != preferredIdx }
            val chosenIdx = candidateIndices.firstOrNull { idx ->
                recipes[idx].requirements.keys.none { reqId -> reqId in visiting }
            }
            if (chosenIdx != null) {
                val chosenRecipe = recipes[chosenIdx]
                activeRecipes[itemId] = chosenRecipe
                resolvedRecipeIndices[itemId] = chosenIdx
                for (reqId in chosenRecipe.requirements.keys) {
                    discover(reqId)
                }
            }
        }

        visiting.remove(itemId)
        visited.add(itemId)
        postOrder.add(itemId)
    }

    for (targetId in positiveTargets.keys) {
        discover(targetId)
    }

    // Pass 2: Compute bottom-up dependency depths (-1 for raw materials, >= 0 for crafted items).
    val depths = mutableMapOf<ItemID, Int>()
    for (itemId in postOrder) {
        val recipe = activeRecipes[itemId]
        depths[itemId] = if (recipe == null) {
            -1
        } else {
            val maxChildDepth = recipe.requirements.keys.maxOfOrNull { depths[it] ?: -1 } ?: -1
            maxChildDepth + 1
        }
    }

    // Pass 3: Propagate aggregated demand in top-down topological order.
    val pendingDemand = positiveTargets.toMutableMap()
    val remainingOwned = ownedInventory.filterValues { it > 0 }.toMutableMap()
    val rawMaterials = linkedMapOf<ItemID, Int>()
    val leftovers = linkedMapOf<ItemID, Int>()
    val stepMap = mutableMapOf<ItemID, CraftingStep>()

    for (itemId in postOrder.asReversed()) {
        val totalRequired = pendingDemand[itemId] ?: 0
        if (totalRequired <= 0) continue

        val ownedAvailable = remainingOwned[itemId] ?: 0
        val usedFromOwned = minOf(ownedAvailable, totalRequired)
        if (usedFromOwned > 0) {
            remainingOwned[itemId] = ownedAvailable - usedFromOwned
        }

        val netNeeded = totalRequired - usedFromOwned
        if (netNeeded <= 0) continue

        val recipe = activeRecipes[itemId]
        if (recipe == null) {
            rawMaterials[itemId] = (rawMaterials[itemId] ?: 0) + netNeeded
        } else {
            val craftCount = resourceAmount(result = recipe.amount, amountNeeded = netNeeded)
            val totalProduced = craftCount * recipe.amount
            val leftover = totalProduced - netNeeded
            if (leftover > 0) {
                leftovers[itemId] = leftover
            }
            stepMap[itemId] = CraftingStep(
                itemId = itemId,
                recipe = recipe,
                totalRequired = totalRequired,
                usedFromOwned = usedFromOwned,
                craftCount = craftCount,
                totalProduced = totalProduced,
                leftover = leftover,
                depth = depths[itemId] ?: 0
            )
            for ((reqId, reqAmount) in recipe.requirements) {
                if (reqAmount > 0) {
                    pendingDemand[reqId] = (pendingDemand[reqId] ?: 0) + (reqAmount * craftCount)
                }
            }
        }
    }

    val steps = stepMap.values.sortedWith(
        compareBy<CraftingStep> { it.depth }.thenBy { it.itemId }
    )

    // Pass 4: Build recursive tree nodes per target following the cycle-free activeRecipes DAG.
    val remainingOwnedForTree = ownedInventory.filterValues { it > 0 }.toMutableMap()
    fun buildNode(itemId: ItemID, requiredAmount: Int): CraftingNode {
        val ownedAvailable = remainingOwnedForTree[itemId] ?: 0
        val usedFromOwned = minOf(ownedAvailable, requiredAmount)
        if (usedFromOwned > 0) {
            remainingOwnedForTree[itemId] = ownedAvailable - usedFromOwned
        }
        val netNeeded = requiredAmount - usedFromOwned
        val availableRecipes = getSortedRecipes(itemId)
        val recipe = activeRecipes[itemId]
        val selectedIdx = resolvedRecipeIndices[itemId]
            ?: if (availableRecipes.isNotEmpty()) {
                (selectedRecipes[itemId] ?: 0).coerceIn(0, availableRecipes.lastIndex)
            } else {
                0
            }

        return if (recipe != null && netNeeded > 0) {
            val craftCount = resourceAmount(result = recipe.amount, amountNeeded = netNeeded)
            val leftover = (craftCount * recipe.amount) - netNeeded
            val children = recipe.requirements.map { (reqId, reqAmount) ->
                buildNode(reqId, reqAmount * craftCount)
            }
            CraftingNode(
                itemId = itemId,
                requiredAmount = requiredAmount,
                usedFromOwned = usedFromOwned,
                selectedRecipeIndex = selectedIdx,
                selectedRecipe = recipe,
                availableRecipes = availableRecipes,
                craftCount = craftCount,
                leftover = leftover,
                children = children
            )
        } else {
            CraftingNode(
                itemId = itemId,
                requiredAmount = requiredAmount,
                usedFromOwned = usedFromOwned,
                selectedRecipeIndex = selectedIdx,
                selectedRecipe = recipe,
                availableRecipes = availableRecipes,
                craftCount = 0,
                leftover = 0,
                children = emptyList()
            )
        }
    }

    val tree = positiveTargets.map { (targetId, targetAmount) ->
        buildNode(targetId, targetAmount)
    }

    return CraftingPlan(
        rawMaterials = rawMaterials,
        leftovers = leftovers,
        steps = steps,
        tree = tree
    )
}
