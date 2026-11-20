package com.fracta7.crafter

import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.RecipeLayout
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.model.toRecipePreferences
import com.fracta7.crafter.domain.usecases.resolvePreferredRecipes
import com.fracta7.crafter.domain.usecases.resolveRecipeTags
import com.fracta7.crafter.domain.usecases.solveCraftingPlan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class RegistryIntegrityTest {

    private fun resolveBundledRegistryDir(): File {
        return listOf(
            File("src/main/assets/registry"),
            File("app/src/main/assets/registry")
        ).firstOrNull { File(it, "manifest.json").isFile }
            ?: error("Could not locate bundled assets/registry directory")
    }

    private val loader = RegistryLoader()
    private val registryDir = resolveBundledRegistryDir()
    private val snapshot = loader.loadFromDirectory(registryDir, RegistrySource.BUNDLED)
    private val items = snapshot.itemRegistry.getAll().values.toList()
    private val itemRegistry = snapshot.itemRegistry
    private val recipeRegistry = snapshot.recipeRegistry
    private val tagRegistry = snapshot.tagRegistry

    @Test
    fun bundledManifestChecksumsMatchFilesOnDisk() {
        assertTrue(loader.verifyDirectoryChecksums(registryDir, snapshot.manifest))
        assertEquals("26.3", snapshot.manifest.mcVersion)
        assertTrue(items.size >= 1693)
        assertTrue(snapshot.recipeCount >= 2069)
    }

    @Test
    fun validateRecipeAvailabilityAndReferencesAndCategories() {
        val categoryIds = snapshot.categories.map { it.id }.toSet()

        items.forEach { item ->
            assertTrue(
                "Item ${item.id} has unknown tags: ${item.tags}",
                item.tags.all { it in categoryIds }
            )
            if (item.decomposable) {
                val recipes = recipeRegistry.getRecipesByResult(item.id)
                assertTrue("Decomposable item ${item.id} has no recipes", recipes.isNotEmpty())
                recipes.forEach { recipe ->
                    assertTrue(
                        "Recipe for ${item.id} references unknown recipeType ${recipe.recipeType}",
                        recipe.recipeType in snapshot.recipeTypes
                    )
                    val resolved = resolveRecipeTags(
                        recipe = recipe,
                        tagRegistry = tagRegistry,
                        itemRegistry = itemRegistry
                    )
                    assertTrue(
                        "Recipe for ${item.id} resolved to empty requirements",
                        resolved.requirements.isNotEmpty()
                    )
                    resolved.requirements.keys.forEach { reqId ->
                        assertTrue(
                            "Recipe for ${item.id} references missing item $reqId",
                            itemRegistry.getItem(reqId) != null
                        )
                    }
                    validateLayoutBounds(item.id, recipe.layout)
                }
            }
        }
    }

    private fun validateLayoutBounds(itemId: String, layout: RecipeLayout?) {
        if (layout is RecipeLayout.CraftingShaped) {
            assertTrue(
                "Shaped grid height > 3 for $itemId",
                layout.pattern.size in 1..3
            )
            layout.pattern.forEach { row ->
                assertTrue("Shaped grid width > 3 for $itemId", row.length <= 3)
                row.forEach { ch ->
                    if (ch != ' ') {
                        val ref = layout.key[ch.toString()]
                        assertTrue("Missing key '$ch' in $itemId shaped layout", ref != null)
                        if (ref is IngredientRef.Item) {
                            assertTrue(
                                "Unknown item '${ref.id}' in $itemId grid",
                                itemRegistry.getItem(ref.id) != null
                            )
                        } else if (ref is IngredientRef.Tag) {
                            assertTrue(
                                "Empty tag '${ref.id}' in $itemId grid",
                                tagRegistry.getItemsForTag(ref.id).isNotEmpty()
                            )
                        }
                    }
                }
            }
        }
    }

    @Test
    fun validateZeroInfiniteCyclesAcrossAllItems() {
        items.forEach { item ->
            val plan = solveCraftingPlan(
                targets = mapOf(item.id to 1),
                recipeRegistry = recipeRegistry,
                itemRegistry = itemRegistry,
                tagRegistry = tagRegistry
            )
            if (item.decomposable) {
                assertTrue("Plan for ${item.id} failed to produce raw materials", plan.rawMaterials.isNotEmpty())
            }
        }
    }

    @Test
    fun validateTippedArrowsGenerationAndDecomposition() {
        val tippedArrows = items.filter { it.id.contains("_tipped_arrow") }
        assertEquals(39, tippedArrows.size)

        tippedArrows.forEach { arrowItem ->
            val plan = solveCraftingPlan(
                targets = mapOf(arrowItem.id to 8),
                recipeRegistry = recipeRegistry,
                itemRegistry = itemRegistry,
                tagRegistry = tagRegistry
            )
            val step = plan.steps.first { it.itemId == arrowItem.id }
            assertEquals(8, step.recipe.requirements["arrow"])
            val lingeringPotionId = step.recipe.requirements.keys.first { it != "arrow" }
            assertTrue(lingeringPotionId.contains("lingering_potion"))
            assertTrue("Plan for ${arrowItem.id} has empty rawMaterials", plan.rawMaterials.isNotEmpty())
        }
    }

    @Test
    fun validatePreferredMaterialsThroughJsonTagResolution() {
        for (wood in PreferredWood.entries) {
            val prefs = UserPreferences(preferredWood = wood).toRecipePreferences()
            val expectedPlanks = "${wood.id}_planks"

            for (genericItem in listOf("chest", "piston", "barrel", "composter", "wooden_pickaxe", "stick")) {
                val plan = solveCraftingPlan(
                    targets = mapOf(genericItem to 1),
                    recipeRegistry = recipeRegistry,
                    itemRegistry = itemRegistry,
                    recipePreferences = prefs,
                    tagRegistry = tagRegistry
                )
                val step = plan.steps.first { it.itemId == genericItem }
                val usedIds = step.recipe.requirements.keys
                if (wood != PreferredWood.OAK) {
                    assertFalse("$genericItem still used oak_planks when wood=${wood.id}", "oak_planks" in usedIds)
                    assertFalse("$genericItem still used oak_slab when wood=${wood.id}", "oak_slab" in usedIds)
                }
                if (genericItem != "composter") {
                    assertTrue(
                        "$genericItem expected $expectedPlanks when wood=${wood.id}, got $usedIds",
                        expectedPlanks in usedIds
                    )
                } else {
                    assertTrue(
                        "composter expected ${wood.id}_slab when wood=${wood.id}, got $usedIds",
                        "${wood.id}_slab" in usedIds
                    )
                }
            }

            val oakDoorRecipe = resolvePreferredRecipes(
                recipeRegistry.getRecipesByResult("oak_door"),
                prefs,
                itemRegistry,
                tagRegistry
            ).first()
            assertEquals(mapOf("oak_planks" to 6), oakDoorRecipe.requirements)

            val charcoalPlan = solveCraftingPlan(
                targets = mapOf("charcoal" to 1),
                recipeRegistry = recipeRegistry,
                itemRegistry = itemRegistry,
                recipePreferences = prefs,
                tagRegistry = tagRegistry
            )
            assertTrue("charcoal" !in charcoalPlan.rawMaterials)
            assertTrue(charcoalPlan.rawMaterials.isNotEmpty())
        }

        for (stone in PreferredStone.entries) {
            val prefs = UserPreferences(preferredStone = stone).toRecipePreferences()
            for (genericStoneItem in listOf("furnace", "brewing_stand", "stone_pickaxe")) {
                val plan = solveCraftingPlan(
                    targets = mapOf(genericStoneItem to 1),
                    recipeRegistry = recipeRegistry,
                    itemRegistry = itemRegistry,
                    recipePreferences = prefs,
                    tagRegistry = tagRegistry
                )
                assertTrue(
                    "Expected $genericStoneItem to use ${stone.id}",
                    stone.id in plan.rawMaterials
                )
            }

            val pistonPlan = solveCraftingPlan(
                targets = mapOf("piston" to 1),
                recipeRegistry = recipeRegistry,
                itemRegistry = itemRegistry,
                recipePreferences = prefs,
                tagRegistry = tagRegistry
            )
            assertTrue("cobblestone" in pistonPlan.rawMaterials)
        }

        for (fuel in PreferredFuel.entries) {
            val prefs = UserPreferences(preferredFuel = fuel).toRecipePreferences()
            val plan = solveCraftingPlan(
                targets = mapOf("smooth_stone" to 1),
                recipeRegistry = recipeRegistry,
                itemRegistry = itemRegistry,
                recipePreferences = prefs,
                tagRegistry = tagRegistry
            )
            val resolvedFuelIds = plan.steps.first { it.itemId == "fuel" }.recipe.requirements.keys
            assertEquals(setOf(fuel.itemId), resolvedFuelIds)
        }
    }
}
