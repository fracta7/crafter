package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.usecases.RecipePreferences
import com.fracta7.crafter.domain.usecases.applyPreferredMaterials
import com.fracta7.crafter.domain.usecases.calculateEfficiency
import com.fracta7.crafter.domain.usecases.calculateTotalRequirements
import com.fracta7.crafter.domain.usecases.resolveRecipesUsingIngredient
import com.fracta7.crafter.domain.usecases.sortRecipesByEfficiency
import org.junit.Assert.assertEquals
import org.junit.Test

class SortRecipesTest {

    @Test
    fun calculatesEfficiencyAndTotalRequirements() {
        val recipe = Recipe(
            result = "oak_slab",
            amount = 6,
            requirements = mapOf("oak_planks" to 3),
            recipeType = "crafting_shaped"
        )
        assertEquals(2.0, calculateEfficiency(recipe), 1e-6)
        assertEquals(3, calculateTotalRequirements(recipe))
    }

    @Test
    fun sortsByHigherEfficiencyThenLowerTotalRequirements() {
        val lowEfficiency = Recipe(
            result = "stone_slab",
            amount = 1,
            requirements = mapOf("stone" to 2),
            recipeType = "crafting_shaped"
        )
        val tiedMoreRequirements = Recipe(
            result = "stone_slab",
            amount = 6,
            requirements = mapOf("stone" to 3),
            recipeType = "crafting_shaped"
        )
        val tiedFewerRequirements = Recipe(
            result = "stone_slab",
            amount = 2,
            requirements = mapOf("stone" to 1),
            recipeType = "stonecutting"
        )

        val sorted = sortRecipesByEfficiency(
            listOf(lowEfficiency, tiedMoreRequirements, tiedFewerRequirements)
        )

        assertEquals(
            listOf(tiedFewerRequirements, tiedMoreRequirements, lowEfficiency),
            sorted
        )
    }

    @Test
    fun respectsPreferredFuelAndStonecutterPreference() {
        val lavaFuel = Recipe("fuel", 100, mapOf("lava_bucket" to 1), "fuel")
        val coalFuel = Recipe("fuel", 8, mapOf("coal" to 1), "fuel")
        val coalSorted = sortRecipesByEfficiency(
            listOf(lavaFuel, coalFuel),
            RecipePreferences(preferredFuelItemId = "coal")
        )
        assertEquals(listOf(coalFuel, lavaFuel), coalSorted)

        val craftingSlab = Recipe("stone_slab", 6, mapOf("stone" to 3), "crafting_shaped")
        val stonecutterSlab = Recipe("stone_slab", 2, mapOf("stone" to 1), "stonecutting")
        val noStonecutter = sortRecipesByEfficiency(
            listOf(stonecutterSlab, craftingSlab),
            RecipePreferences(preferStonecutter = false)
        )
        assertEquals(listOf(craftingSlab, stonecutterSlab), noStonecutter)
    }

    @Test
    fun substitutesPreferredWoodAndPreservesOakSpecificRecipes() {
        val sprucePrefs = RecipePreferences(preferredWoodId = "spruce")

        val chest = Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped")
        assertEquals(
            mapOf("spruce_planks" to 8),
            applyPreferredMaterials(chest, sprucePrefs).requirements
        )

        val barrel = Recipe("barrel", 1, mapOf("oak_planks" to 6, "oak_slab" to 2), "crafting_shaped")
        assertEquals(
            mapOf("spruce_planks" to 6, "spruce_slab" to 2),
            applyPreferredMaterials(barrel, sprucePrefs).requirements
        )

        val campfire = Recipe(
            "campfire",
            1,
            mapOf("stick" to 3, "coal" to 1, "oak_log" to 3),
            "crafting_shaped"
        )
        assertEquals(
            mapOf("stick" to 3, "coal" to 1, "spruce_log" to 3),
            applyPreferredMaterials(campfire, sprucePrefs).requirements
        )

        val smoker = Recipe("smoker", 1, mapOf("furnace" to 1, "oak_log" to 4), "crafting_shaped")
        assertEquals(
            mapOf("furnace" to 1, "spruce_log" to 4),
            applyPreferredMaterials(smoker, sprucePrefs).requirements
        )

        val stick = Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped")
        assertEquals(
            mapOf("spruce_planks" to 2),
            applyPreferredMaterials(stick, sprucePrefs).requirements
        )

        // Oak-specific recipes must stay oak even when Spruce is selected.
        val oakDoor = Recipe("oak_door", 3, mapOf("oak_planks" to 6), "crafting_shaped")
        val oakStairs = Recipe("oak_stairs", 4, mapOf("oak_planks" to 6), "crafting_shaped")
        val oakPlanks = Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless")
        val strippedOakLog = Recipe("stripped_oak_log", 1, mapOf("oak_log" to 1), "stripping")
        assertEquals(oakDoor, applyPreferredMaterials(oakDoor, sprucePrefs))
        assertEquals(oakStairs, applyPreferredMaterials(oakStairs, sprucePrefs))
        assertEquals(oakPlanks, applyPreferredMaterials(oakPlanks, sprucePrefs))
        assertEquals(strippedOakLog, applyPreferredMaterials(strippedOakLog, sprucePrefs))
    }

    @Test
    fun handlesCrimsonAndBambooWoodStemAndBurnableEdgeCases() {
        val crimsonPrefs = RecipePreferences(preferredWoodId = "crimson")
        val smoker = Recipe("smoker", 1, mapOf("furnace" to 1, "oak_log" to 4), "crafting_shaped")
        val campfire = Recipe(
            "campfire",
            1,
            mapOf("stick" to 3, "coal" to 1, "oak_log" to 3),
            "crafting_shaped"
        )
        val charcoal = Recipe("charcoal", 1, mapOf("oak_log" to 1, "fuel" to 1), "smelting")
        val barrel = Recipe("barrel", 1, mapOf("oak_planks" to 6, "oak_slab" to 2), "crafting_shaped")

        assertEquals(
            mapOf("furnace" to 1, "crimson_stem" to 4),
            applyPreferredMaterials(smoker, crimsonPrefs).requirements
        )
        // Crimson stems cannot be used in campfire/charcoal in vanilla Minecraft.
        assertEquals(campfire, applyPreferredMaterials(campfire, crimsonPrefs))
        assertEquals(charcoal, applyPreferredMaterials(charcoal, crimsonPrefs))
        assertEquals(
            mapOf("crimson_planks" to 6, "crimson_slab" to 2),
            applyPreferredMaterials(barrel, crimsonPrefs).requirements
        )

        val bambooPrefs = RecipePreferences(preferredWoodId = "bamboo")
        assertEquals(
            mapOf("bamboo_planks" to 6, "bamboo_slab" to 2),
            applyPreferredMaterials(barrel, bambooPrefs).requirements
        )
        assertEquals(smoker, applyPreferredMaterials(smoker, bambooPrefs))
        assertEquals(campfire, applyPreferredMaterials(campfire, bambooPrefs))
    }

    @Test
    fun substitutesPreferredStoneOnStoneTagRecipesOnly() {
        val blackstonePrefs = RecipePreferences(preferredStoneId = "blackstone")
        val cobbledDeepslatePrefs = RecipePreferences(preferredStoneId = "cobbled_deepslate")

        val furnace = Recipe("furnace", 1, mapOf("cobblestone" to 8), "crafting_shaped")
        val brewingStand = Recipe(
            "brewing_stand",
            1,
            mapOf("blaze_rod" to 1, "cobblestone" to 3),
            "crafting_shaped"
        )
        val stonePickaxe = Recipe(
            "stone_pickaxe",
            1,
            mapOf("cobblestone" to 3, "stick" to 2),
            "crafting_shaped"
        )
        assertEquals(
            mapOf("blackstone" to 8),
            applyPreferredMaterials(furnace, blackstonePrefs).requirements
        )
        assertEquals(
            mapOf("blaze_rod" to 1, "blackstone" to 3),
            applyPreferredMaterials(brewingStand, blackstonePrefs).requirements
        )
        assertEquals(
            mapOf("cobbled_deepslate" to 3, "stick" to 2),
            applyPreferredMaterials(stonePickaxe, cobbledDeepslatePrefs).requirements
        )

        // Piston, observer, and cobblestone wall strictly require cobblestone.
        val piston = Recipe(
            "piston",
            1,
            mapOf("cobblestone" to 4, "iron_ingot" to 1, "redstone" to 1, "oak_planks" to 3),
            "crafting_shaped"
        )
        val observer = Recipe(
            "observer",
            1,
            mapOf("cobblestone" to 6, "redstone" to 2, "quartz" to 1),
            "crafting_shaped"
        )
        val cobblestoneWall = Recipe(
            "cobblestone_wall",
            6,
            mapOf("cobblestone" to 6),
            "crafting_shaped"
        )
        assertEquals(piston, applyPreferredMaterials(piston, blackstonePrefs))
        assertEquals(observer, applyPreferredMaterials(observer, blackstonePrefs))
        assertEquals(cobblestoneWall, applyPreferredMaterials(cobblestoneWall, blackstonePrefs))
    }

    @Test
    fun resolvesIngredientLookupUnderPreferredMaterials() {
        val registry = RecipeRegistry().apply {
            addRecipe(Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"))
            addRecipe(Recipe("oak_door", 3, mapOf("oak_planks" to 6), "crafting_shaped"))
            addRecipe(Recipe("spruce_door", 3, mapOf("spruce_planks" to 6), "crafting_shaped"))
        }
        val prefs = RecipePreferences(preferredWoodId = "spruce")

        val spruceUses = resolveRecipesUsingIngredient("spruce_planks", registry, prefs)
        assertEquals(setOf("spruce_door", "chest"), spruceUses.map { it.result }.toSet())

        val oakUses = resolveRecipesUsingIngredient("oak_planks", registry, prefs)
        assertEquals(listOf("oak_door"), oakUses.map { it.result })
    }
}
