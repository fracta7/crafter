package com.fracta7.crafter

import com.fracta7.crafter.data.repository.itemsInit
import com.fracta7.crafter.data.repository.recipeTypesInit
import com.fracta7.crafter.data.repository.recipesInit
import com.fracta7.crafter.data.repository.specialRecipesInit
import com.fracta7.crafter.data.repository.tagsInit
import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.TagID
import org.junit.Assert.assertTrue
import org.junit.Test

class DataValidation {
    private val items = itemsInit()
    private val recipes = recipesInit() + specialRecipesInit()
    private val recipeTypes = recipeTypesInit()
    private val categories = tagsInit()

    @Test
    fun validateRecipeAvailability() {
        val missingItems = validateRecipeAvailability(items, recipes)
        assertTrue("Some craftable items are missing recipes.", missingItems.isEmpty())
    }

    @Test
    fun validateRecipeRequirements() {
        val invalidRequirements = validateRecipeRequirements(items, recipes)
        assertTrue("Some recipes have invalid item IDs in their requirements.", invalidRequirements.isEmpty())
    }

    @Test
    fun validateRecipeTypes() {
        val invalidRecipeTypes = validateRecipeTypes(recipeTypes, recipes)
        assertTrue("Some recipes have invalid recipe type", invalidRecipeTypes.isEmpty())
    }

    @Test
    fun validateItemTags() {
        val invalidTags = validateItemTags(categories, items)
        assertTrue("Some items have invalid tags/categories.", invalidTags.isEmpty())
    }
}

private fun validateRecipeAvailability(items: List<Item>, recipes: List<Recipe>): List<ItemID> {
    val craftableItems = items.filter { it.craftable }
    val missingItems = mutableListOf<ItemID>()

    for (item in craftableItems) {
        val hasRecipe = recipes.any { it.result == item.id }
        if (!hasRecipe) {
            missingItems.add(item.id)
        }
    }

    if (missingItems.isNotEmpty()) {
        println("=== LIST OF ITEMS WITH MISSING RECIPES ===")
        missingItems.forEach { println("\"$it\"") }
    } else {
        println("All craftable items have recipes.")
    }

    return missingItems
}

private fun validateRecipeRequirements(items: List<Item>, recipes: List<Recipe>): List<ItemID> {
    val itemIDs = items.map { it.id }.toSet()
    val invalidRequirements = mutableListOf<ItemID>()

    for (recipe in recipes) {
        for (requirement in recipe.requirements.keys) {
            if (requirement !in itemIDs) {
                println("\"$requirement\" in recipe for \"${recipe.result}\" - INVALID ITEM ID")
                invalidRequirements.add(requirement)
            }
        }
    }

    if (invalidRequirements.isNotEmpty()) {
        println("=== LIST OF INVALID ITEM IDS IN REQUIREMENTS ===")
        invalidRequirements.forEach { println("\"$it\"") }
    } else {
        println("All item IDs in requirements are valid.")
    }

    return invalidRequirements
}

private fun validateRecipeTypes(
    recipeTypes: List<RecipeType>,
    recipes: List<Recipe>
): List<RecipeTypeID> {
    val recipeTypeIDs = recipeTypes.map { it.id }.toSet()
    val invalidRecipeTypes = mutableListOf<RecipeTypeID>()

    for (recipe in recipes) {
        if (recipe.recipeType !in recipeTypeIDs) {
            println("\"${recipe.recipeType}\" in recipe for \"${recipe.result}\" - INVALID RECIPE TYPE")
            invalidRecipeTypes.add(recipe.recipeType)
        }
    }

    if (invalidRecipeTypes.isNotEmpty()) {
        println("=== LIST OF INVALID RECIPE TYPES ===")
        invalidRecipeTypes.forEach { println("\"$it\"") }
    } else {
        println("All recipe types are valid.")
    }

    return invalidRecipeTypes
}

private fun validateItemTags(categories: List<Category>, items: List<Item>): List<TagID> {
    val categoryIDs = categories.map { it.id }.toSet()
    val invalidTags = mutableListOf<TagID>()

    for (item in items) {
        for (tag in item.tags) {
            if (tag !in categoryIDs) {
                println("\"$tag\" in item \"${item.id}\" - INVALID TAG ID")
                invalidTags.add(tag)
            }
        }
    }

    if (invalidTags.isNotEmpty()) {
        println("=== LIST OF INVALID TAG IDS ===")
        invalidTags.forEach { println("\"$it\"") }
    } else {
        println("All tag IDs are valid.")
    }

    return invalidTags
}
