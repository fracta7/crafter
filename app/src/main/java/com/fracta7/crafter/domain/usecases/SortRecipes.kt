package com.fracta7.crafter.domain.usecases

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.TagRegistry

@Immutable
data class RecipePreferences(
    val preferredFuelItemId: ItemID? = null,
    val preferredWoodId: String = "oak",
    val preferredStoneId: ItemID = "cobblestone",
    val preferStonecutter: Boolean = true
)

private val OVERWORLD_BURNABLE_WOODS = setOf(
    "oak",
    "spruce",
    "birch",
    "jungle",
    "acacia",
    "dark_oak",
    "mangrove",
    "cherry",
    "pale_oak",
    "poplar"
)

private val BURNABLE_LOG_RECIPES = setOf(
    "campfire",
    "soul_campfire",
    "charcoal"
)

private val STONE_TAG_RECIPES = setOf(
    "furnace",
    "brewing_stand",
    "stone_sword",
    "stone_pickaxe",
    "stone_axe",
    "stone_shovel",
    "stone_hoe",
    "stone_spear"
)

private fun preferredLogFor(woodId: String, burnableOnly: Boolean): ItemID {
    return when {
        woodId in OVERWORLD_BURNABLE_WOODS -> "${woodId}_log"
        !burnableOnly && woodId == "crimson" -> "crimson_stem"
        !burnableOnly && woodId == "warped" -> "warped_stem"
        else -> "oak_log"
    }
}

/**
 * Rewrites generic wood/stone tag ingredients in [recipe] to use the user's preferred materials,
 * preserving species-specific recipes (e.g., `oak_door`, `stripped_oak_log`, `piston`).
 */
fun applyPreferredMaterials(
    recipe: Recipe,
    preferences: RecipePreferences,
    itemRegistry: ItemRegistry? = null
): Recipe {
    val woodId = preferences.preferredWoodId
    val stoneId = preferences.preferredStoneId
    if (woodId == "oak" && stoneId == "cobblestone") return recipe

    val isOakSpecificResult = recipe.result.startsWith("oak_") || recipe.result.startsWith("stripped_oak_")
    val isStoneTagRecipe = recipe.result in STONE_TAG_RECIPES
    val burnableOnly = recipe.result in BURNABLE_LOG_RECIPES

    var changed = false
    val merged = linkedMapOf<ItemID, Int>()

    for ((ingredientId, qty) in recipe.requirements) {
        val candidateId = when {
            !isOakSpecificResult && woodId != "oak" && ingredientId == "oak_planks" -> "${woodId}_planks"
            !isOakSpecificResult && woodId != "oak" && ingredientId == "oak_slab" -> "${woodId}_slab"
            !isOakSpecificResult && woodId != "oak" && ingredientId == "oak_log" ->
                preferredLogFor(woodId, burnableOnly = burnableOnly)
            isStoneTagRecipe && stoneId != "cobblestone" && ingredientId == "cobblestone" -> stoneId
            else -> ingredientId
        }

        val resolvedId = if (
            candidateId != ingredientId &&
            (itemRegistry == null || itemRegistry.getItem(candidateId) != null)
        ) {
            changed = true
            candidateId
        } else {
            ingredientId
        }

        merged[resolvedId] = (merged[resolvedId] ?: 0) + qty
    }

    return if (changed) recipe.copy(requirements = merged) else recipe
}

/**
 * Resolves recipe tags, applies preferred material substitutions, and sorts recipes
 * according to [preferences].
 */
fun resolvePreferredRecipes(
    recipes: List<Recipe>,
    preferences: RecipePreferences = RecipePreferences(),
    itemRegistry: ItemRegistry? = null,
    tagRegistry: TagRegistry = TagRegistry()
): List<Recipe> {
    val resolved = recipes.map { raw ->
        val withTags = resolveRecipeTags(raw, tagRegistry, preferences, itemRegistry)
        applyPreferredMaterials(withTags, preferences, itemRegistry)
    }
    return sortRecipesByEfficiency(resolved, preferences)
}

/**
 * Resolves recipes consuming [itemId] after applying [preferences], so catalog Mini-JEI
 * shows both native uses of [itemId] and generic wood/stone tag recipes mapped to [itemId].
 */
fun resolveRecipesUsingIngredient(
    itemId: ItemID,
    recipeRegistry: RecipeRegistry,
    preferences: RecipePreferences = RecipePreferences(),
    itemRegistry: ItemRegistry? = null,
    tagRegistry: TagRegistry = TagRegistry()
): List<Recipe> {
    val candidates = linkedSetOf<Recipe>()
    candidates.addAll(recipeRegistry.getRecipesUsingIngredient(itemId))

    tagRegistry.getTagsForItem(itemId).forEach { tagId ->
        candidates.addAll(recipeRegistry.getRecipesUsingTag(tagId))
    }

    val woodId = preferences.preferredWoodId
    if (woodId != "oak") {
        if (itemId == "${woodId}_planks") {
            candidates.addAll(recipeRegistry.getRecipesUsingIngredient("oak_planks"))
        }
        if (itemId == "${woodId}_slab") {
            candidates.addAll(recipeRegistry.getRecipesUsingIngredient("oak_slab"))
        }
        if (
            itemId == preferredLogFor(woodId, burnableOnly = false) ||
            itemId == preferredLogFor(woodId, burnableOnly = true)
        ) {
            candidates.addAll(recipeRegistry.getRecipesUsingIngredient("oak_log"))
        }
    }

    val stoneId = preferences.preferredStoneId
    if (stoneId != "cobblestone" && itemId == stoneId) {
        candidates.addAll(recipeRegistry.getRecipesUsingIngredient("cobblestone"))
    }

    return candidates
        .map { raw ->
            val withTags = resolveRecipeTags(raw, tagRegistry, preferences, itemRegistry)
            applyPreferredMaterials(withTags, preferences, itemRegistry)
        }
        .filter { itemId in it.requirements }
        .distinct()
}

/**
 * Function to calculate the efficiency of a recipe.
 * Efficiency is defined as the ratio of result quantity to the sum of requirement quantities.
 */
fun calculateEfficiency(recipe: Recipe): Double {
    val totalRequirements = calculateTotalRequirements(recipe)
    return if (totalRequirements > 0) recipe.amount.toDouble() / totalRequirements else 0.0
}

/**
 * Function to calculate the total amount of items required by a recipe.
 */
fun calculateTotalRequirements(recipe: Recipe): Int {
    return recipe.requirements.values.sum() + recipe.tagRequirements.values.sum()
}

/**
 * Sorts recipes based on user preferences (preferred fuel / stonecutter), then efficiency,
 * then lower total requirements.
 */
fun sortRecipesByEfficiency(
    recipes: List<Recipe>,
    preferences: RecipePreferences = RecipePreferences()
): List<Recipe> {
    return recipes.sortedWith(
        compareBy<Recipe> { recipe ->
            when {
                preferences.preferredFuelItemId != null &&
                    recipe.recipeType == "fuel" &&
                    preferences.preferredFuelItemId in recipe.requirements -> -1
                !preferences.preferStonecutter && recipe.recipeType == "stonecutting" -> 1
                else -> 0
            }
        }
            .thenByDescending { calculateEfficiency(it) }
            .thenBy { calculateTotalRequirements(it) }
            .thenBy { recipe ->
                if (preferences.preferStonecutter) recipe.recipeType != "stonecutting" else false
            }
    )
}
