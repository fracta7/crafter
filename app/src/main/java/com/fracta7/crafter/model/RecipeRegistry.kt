package com.fracta7.crafter.model

class RecipeRegistry {
    private val recipes: MutableList<Recipe> = mutableListOf()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun getRecipesByResult(result: Item): List<Recipe> {
        return recipes.filter { it.result == result }
    }
}