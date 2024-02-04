package com.fracta7.crafter.domain.model

/**
 * Registry to hold all recipes.
 * @property addRecipe adds single recipe to the registry.
 * @property getRecipesByResult gets a recipe by resulting item.
 */
class RecipeRegistry {
    private val recipes: MutableList<Recipe> = mutableListOf()

    /**
     * Adds a single recipe to the registry.
     * @param recipe recipe to be added to the registry.
     */
    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    /**
     * Searches recipe for given item.
     * @param result item to be searched.
     * @return list of recipes.
     */
    fun getRecipesByResult(result: Item): List<Recipe> {
        return recipes.filter { it.result == result }
    }
}