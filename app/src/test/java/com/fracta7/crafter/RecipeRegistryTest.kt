package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeRegistryTest {

    @Test
    fun indexesRecipesByResultAndIngredient() {
        val registry = RecipeRegistry()
        val planksRecipe = Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless")
        val stickRecipe = Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped")
        val chestRecipe = Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped")
        val pickaxeRecipe = Recipe(
            "wooden_pickaxe",
            1,
            mapOf("oak_planks" to 3, "stick" to 2),
            "crafting_shaped"
        )

        registry.addRecipe(planksRecipe)
        registry.addRecipe(stickRecipe)
        registry.addRecipe(chestRecipe)
        registry.addRecipe(pickaxeRecipe)

        assertEquals(listOf(planksRecipe), registry.getRecipesByResult("oak_planks"))
        assertEquals(
            listOf(stickRecipe, chestRecipe, pickaxeRecipe),
            registry.getRecipesUsingIngredient("oak_planks")
        )
        assertEquals(listOf(pickaxeRecipe), registry.getRecipesUsingIngredient("stick"))
        assertTrue(registry.getRecipesUsingIngredient("wooden_pickaxe").isEmpty())
    }
}
