package com.fracta7.crafter.domain.usecases

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.TagRegistry

@Immutable
data class MaterialRowUi(
    val itemId: ItemID,
    val totalRequired: Int,
    val owned: Int,
    val remaining: Int,
    val isCompleted: Boolean
)

@Immutable
data class StepRowUi(
    val itemId: ItemID,
    val recipe: Recipe,
    val totalRequired: Int,
    val owned: Int,
    val craftCount: Int,
    val totalProduced: Int,
    val leftover: Int,
    val depth: Int,
    val availableRecipeCount: Int,
    val selectedRecipeIndex: Int,
    val isCompleted: Boolean,
    val isSatisfiedByParent: Boolean
)

@Immutable
data class CraftingProgress(
    val completedMaterials: Int = 0,
    val totalMaterials: Int = 0,
    val completedSteps: Int = 0,
    val totalSteps: Int = 0,
    val fraction: Float = 0f
)

@Immutable
data class CraftingProjection(
    val plan: CraftingPlan = CraftingPlan(),
    val materialRows: List<MaterialRowUi> = emptyList(),
    val stepRows: List<StepRowUi> = emptyList(),
    val progress: CraftingProgress = CraftingProgress()
)

fun computeCraftingProjection(
    targets: Map<ItemID, Int>,
    recipeRegistry: RecipeRegistry,
    itemRegistry: ItemRegistry,
    selectedRecipes: Map<ItemID, Int> = emptyMap(),
    ownedInventory: Map<ItemID, Int> = emptyMap(),
    recipePreferences: RecipePreferences = RecipePreferences(),
    tagRegistry: TagRegistry = TagRegistry()
): CraftingProjection {
    if (targets.isEmpty() || targets.values.none { it > 0 }) {
        return CraftingProjection()
    }

    val baselinePlan = solveCraftingPlan(
        targets = targets,
        recipeRegistry = recipeRegistry,
        itemRegistry = itemRegistry,
        selectedRecipes = selectedRecipes,
        ownedInventory = emptyMap(),
        recipePreferences = recipePreferences,
        tagRegistry = tagRegistry
    )
    val baselineRawIds = baselinePlan.rawMaterials.keys

    // Subtract owned intermediates/targets without subtracting raw items yet so raw checklist rows remain visible.
    val rawDemandPlan = solveCraftingPlan(
        targets = targets,
        recipeRegistry = recipeRegistry,
        itemRegistry = itemRegistry,
        selectedRecipes = selectedRecipes,
        ownedInventory = ownedInventory.filterKeys { it !in baselineRawIds },
        recipePreferences = recipePreferences,
        tagRegistry = tagRegistry
    )

    val activePlan = solveCraftingPlan(
        targets = targets,
        recipeRegistry = recipeRegistry,
        itemRegistry = itemRegistry,
        selectedRecipes = selectedRecipes,
        ownedInventory = ownedInventory,
        recipePreferences = recipePreferences,
        tagRegistry = tagRegistry
    )

    val materialRows = rawDemandPlan.rawMaterials.map { (itemId, totalRequired) ->
        val owned = minOf(ownedInventory[itemId] ?: 0, totalRequired)
        val remaining = totalRequired - owned
        MaterialRowUi(
            itemId = itemId,
            totalRequired = totalRequired,
            owned = owned,
            remaining = remaining,
            isCompleted = remaining == 0
        )
    }

    val activeStepsById = activePlan.steps.associateBy { it.itemId }
    val stepRows = baselinePlan.steps.map { baseStep ->
        val activeStep = activeStepsById[baseStep.itemId]
        val availableRecipes = resolvePreferredRecipes(
            recipes = recipeRegistry.getRecipesByResult(baseStep.itemId),
            preferences = recipePreferences,
            itemRegistry = itemRegistry,
            tagRegistry = tagRegistry
        )
        val selectedIdx = if (availableRecipes.isNotEmpty()) {
            (selectedRecipes[baseStep.itemId] ?: 0).coerceIn(0, availableRecipes.lastIndex)
        } else {
            0
        }
        val rawOwned = ownedInventory[baseStep.itemId] ?: 0
        if (activeStep != null) {
            StepRowUi(
                itemId = activeStep.itemId,
                recipe = activeStep.recipe,
                totalRequired = activeStep.totalRequired,
                owned = minOf(rawOwned, activeStep.totalRequired),
                craftCount = activeStep.craftCount,
                totalProduced = activeStep.totalProduced,
                leftover = activeStep.leftover,
                depth = activeStep.depth,
                availableRecipeCount = availableRecipes.size,
                selectedRecipeIndex = selectedIdx,
                isCompleted = false,
                isSatisfiedByParent = false
            )
        } else {
            val ownedDisplay = minOf(rawOwned, baseStep.totalRequired)
            StepRowUi(
                itemId = baseStep.itemId,
                recipe = baseStep.recipe,
                totalRequired = baseStep.totalRequired,
                owned = ownedDisplay,
                craftCount = 0,
                totalProduced = 0,
                leftover = 0,
                depth = baseStep.depth,
                availableRecipeCount = availableRecipes.size,
                selectedRecipeIndex = selectedIdx,
                isCompleted = true,
                isSatisfiedByParent = rawOwned < baseStep.totalRequired
            )
        }
    }

    val completedMaterials = materialRows.count { it.isCompleted }
    val totalMaterials = materialRows.size
    val completedSteps = stepRows.count { it.isCompleted }
    val totalSteps = stepRows.size

    val fraction = when {
        activePlan.rawMaterials.isEmpty() && activePlan.steps.isEmpty() -> 1f
        materialRows.isNotEmpty() && stepRows.isNotEmpty() -> {
            val rawUnitFrac = materialRows.sumOf { it.owned }.toFloat() /
                materialRows.sumOf { it.totalRequired }.coerceAtLeast(1)
            val stepFrac = completedSteps.toFloat() / totalSteps
            ((rawUnitFrac + stepFrac) / 2f).coerceIn(0f, 1f)
        }
        materialRows.isNotEmpty() -> {
            (materialRows.sumOf { it.owned }.toFloat() /
                materialRows.sumOf { it.totalRequired }.coerceAtLeast(1)).coerceIn(0f, 1f)
        }
        stepRows.isNotEmpty() -> {
            (completedSteps.toFloat() / totalSteps).coerceIn(0f, 1f)
        }
        else -> 0f
    }

    return CraftingProjection(
        plan = activePlan,
        materialRows = materialRows,
        stepRows = stepRows,
        progress = CraftingProgress(
            completedMaterials = completedMaterials,
            totalMaterials = totalMaterials,
            completedSteps = completedSteps,
            totalSteps = totalSteps,
            fraction = fraction
        )
    )
}
