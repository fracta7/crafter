package com.fracta7.crafter.domain.model

/**
 * Registry to hold all recipes with O(1) lookup by result, by concrete ingredient, and by item tag.
 */
class RecipeRegistry {
    private val recipesByResult: MutableMap<ItemID, MutableList<Recipe>> = mutableMapOf()
    private val recipesByIngredient: MutableMap<ItemID, MutableList<Recipe>> = mutableMapOf()
    private val recipesByTag: MutableMap<TagID, MutableList<Recipe>> = mutableMapOf()

    /**
     * Adds a single recipe to the registry.
     * @param recipe recipe to be added to the registry.
     */
    fun addRecipe(recipe: Recipe) {
        recipesByResult.getOrPut(recipe.result) { mutableListOf() }.add(recipe)
        recipe.requirements.keys.forEach { ingredientId ->
            recipesByIngredient.getOrPut(ingredientId) { mutableListOf() }.add(recipe)
        }
        recipe.tagRequirements.keys.forEach { tagId ->
            recipesByTag.getOrPut(TagRegistry.normalizeTagId(tagId)) { mutableListOf() }.add(recipe)
        }
    }

    /**
     * Searches recipes producing the given item.
     * @param result item to be searched.
     * @return list of recipes.
     */
    fun getRecipesByResult(result: ItemID): List<Recipe> {
        return recipesByResult[result]?.toList().orEmpty()
    }

    /**
     * Searches recipes that consume [itemId] directly as an ingredient.
     * @param itemId ingredient item ID.
     * @return list of recipes using [itemId].
     */
    fun getRecipesUsingIngredient(itemId: ItemID): List<Recipe> {
        return recipesByIngredient[itemId]?.toList().orEmpty()
    }

    /**
     * Searches recipes that consume [tagId] via tag requirements.
     */
    fun getRecipesUsingTag(tagId: TagID): List<Recipe> {
        return recipesByTag[TagRegistry.normalizeTagId(tagId)]?.toList().orEmpty()
    }
}
