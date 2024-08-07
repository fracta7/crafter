package com.fracta7.crafter.util

import com.fracta7.crafter.domain.model.Recipe

/**
 * Function to calculate the efficiency of a recipe.
 * Efficiency is defined as the ratio of result quantity to the sum of requirement quantities.
 */
fun calculateEfficiency(recipe: Recipe): Double {
    val totalRequirements = recipe.requirements.values.sum()
    return recipe.resultQuantity.toDouble() / totalRequirements
}

/**
 * Function to calculate the total amount of items required by a recipe.
 */
fun calculateTotalRequirements(recipe: Recipe): Int {
    return recipe.requirements.values.sum()
}

/**
 * Function to sort the list of recipes based on their efficiency.
 * In case of ties, it sorts by the total amount of items required.
 * @param recipes List of recipes to be sorted.
 * @return List of recipes sorted from most efficient to least efficient.
 */
fun sortRecipesByEfficiency(recipes: List<Recipe>): List<Recipe> {
    return recipes.sortedWith(
        compareByDescending<Recipe> { calculateEfficiency(it) }
            .thenComparingInt { calculateTotalRequirements(it) }
    )
}