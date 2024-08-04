package com.fracta7.crafter

import com.fracta7.crafter.data.repository.itemsInit
import com.fracta7.crafter.data.repository.recipesInit
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import org.junit.Test

class RecipeValidation {
    @Test
    fun validateRecipes() {
        val items = itemsInit()
        val recipes = recipesInit()
        val craftableItems = items.filter { it.craftable }
        val missingItems = mutableListOf<ItemID>()

        // Check each craftable item for a recipe and print the status
        for (item in craftableItems) {
            val hasRecipe = recipes.any { it.result == item.id }
            if (hasRecipe) {
                println("\"${item.id}\" - OK")
            } else {
                println("\"${item.id}\" - FAIL")
                missingItems.add(item.id)
            }
        }

        // Print missing items if there are any
        if (missingItems.isNotEmpty()) {
            println("=== LIST OF ITEMS WITH MISSING RECIPES ===")
            missingItems.forEach { println("\"$it\"") }
        } else {
            println("All craftable items have recipes.")
        }
    }
}