package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.ui.crafting_screen.CraftingScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CraftingScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun fakeRepository(): AppRepository {
        val items = ItemRegistry().apply {
            addItem(Item("stone", "Stone", 64, decomposable = false))
            addItem(Item("cobblestone", "Cobblestone", 64, decomposable = false))
            addItem(Item("blackstone", "Blackstone", 64, decomposable = false))
            addItem(Item("smooth_stone", "Smooth Stone", 64, decomposable = true))
            addItem(Item("stone_slab", "Stone Slab", 64, decomposable = true))
            addItem(Item("iron_ingot", "Iron Ingot", 64, decomposable = false))
            addItem(Item("oak_log", "Oak Log", 64, decomposable = false))
            addItem(Item("oak_planks", "Oak Planks", 64, decomposable = true))
            addItem(Item("spruce_log", "Spruce Log", 64, decomposable = false))
            addItem(Item("spruce_planks", "Spruce Planks", 64, decomposable = true))
            addItem(Item("stick", "Stick", 64, decomposable = true))
            addItem(Item("chest", "Chest", 64, decomposable = true))
            addItem(Item("furnace", "Furnace", 64, decomposable = true))
            addItem(Item("iron_pickaxe", "Iron Pickaxe", 1, decomposable = true))
            addItem(Item("fuel", "Fuel", 64, decomposable = true))
            addItem(Item("coal", "Coal", 64, decomposable = false))
            addItem(Item("lava_bucket", "Lava Bucket", 1, decomposable = false))
        }
        val recipes = RecipeRegistry().apply {
            addRecipe(Recipe("stone_slab", 6, mapOf("stone" to 3), "crafting_shaped"))
            addRecipe(Recipe("stone_slab", 2, mapOf("stone" to 1), "stonecutting"))
            addRecipe(Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"))
            addRecipe(Recipe("spruce_planks", 4, mapOf("spruce_log" to 1), "crafting_shapeless"))
            addRecipe(Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"))
            addRecipe(Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"))
            addRecipe(Recipe("furnace", 1, mapOf("cobblestone" to 8), "crafting_shaped"))
            addRecipe(Recipe("iron_pickaxe", 1, mapOf("iron_ingot" to 3, "stick" to 2), "crafting_shaped"))
            addRecipe(Recipe("smooth_stone", 1, mapOf("stone" to 1, "fuel" to 1), "smelting"))
            addRecipe(Recipe("fuel", 100, mapOf("lava_bucket" to 1), "fuel"))
            addRecipe(Recipe("fuel", 8, mapOf("coal" to 1), "fuel"))
        }
        val recipeTypes = mapOf(
            "crafting_shaped" to RecipeType("crafting_shaped", "Crafting", "crafting_table"),
            "crafting_shapeless" to RecipeType("crafting_shapeless", "Crafting", "crafting_table"),
            "stonecutting" to RecipeType("stonecutting", "Stonecutting", "stonecutter"),
            "smelting" to RecipeType("smelting", "Smelting", "furnace"),
            "fuel" to RecipeType("fuel", "Fuel", "coal")
        )
        return FakeAppRepository.from(items, recipes, recipeTypes)
    }

    @Test
    fun loadProjectAndSelectRecipeUpdateAndPersistUiState() {
        val projectRepo = FakeProjectRepository(
            listOf(
                CraftingProject(
                    id = 1L,
                    name = "Slab Test",
                    isDraft = false,
                    targets = mapOf("stone_slab" to 1)
                )
            )
        )
        val viewModel = CraftingScreenViewModel(
            fakeRepository(),
            projectRepo,
            FakeUserPreferencesRepository()
        )

        viewModel.loadProject(1L)
        val initialPlan = viewModel.uiState.value.plan
        assertEquals(mapOf("stone" to 1), initialPlan.rawMaterials)
        assertEquals(mapOf("stone_slab" to 1), initialPlan.leftovers)

        viewModel.selectRecipe(itemId = "stone_slab", recipeIndex = 1)
        val updatedState = viewModel.uiState.value
        assertEquals(mapOf("stone_slab" to 1), updatedState.selectedRecipes)
        assertEquals(mapOf("stone" to 3), updatedState.plan.rawMaterials)
        assertEquals(mapOf("stone_slab" to 5), updatedState.plan.leftovers)
        assertEquals(1, updatedState.plan.tree.single().selectedRecipeIndex)
    }

    @Test
    fun ownedInventoryCheckoffPersistsAndUpdatesProgress() {
        val projectRepo = FakeProjectRepository(
            listOf(
                CraftingProject(
                    id = 2L,
                    name = "Pickaxe",
                    isDraft = true,
                    targets = mapOf("iron_pickaxe" to 1)
                )
            )
        )
        val viewModel = CraftingScreenViewModel(
            fakeRepository(),
            projectRepo,
            FakeUserPreferencesRepository()
        )
        viewModel.loadProject(2L)

        val baseline = viewModel.uiState.value
        assertEquals(2, baseline.materialRows.size)
        assertEquals(3, baseline.stepRows.size)
        assertEquals(0f, baseline.progress.fraction, 1e-4f)

        viewModel.setOwnedAmount("iron_ingot", 2)
        val afterPartial = viewModel.uiState.value
        val ironRow = afterPartial.materialRows.first { it.itemId == "iron_ingot" }
        assertEquals(3, ironRow.totalRequired)
        assertEquals(2, ironRow.owned)
        assertEquals(1, ironRow.remaining)
        assertFalse(ironRow.isCompleted)
        assertTrue(afterPartial.progress.fraction > 0f)

        viewModel.toggleItemComplete("iron_ingot", 3)
        val afterToggleRaw = viewModel.uiState.value
        assertTrue(afterToggleRaw.materialRows.first { it.itemId == "iron_ingot" }.isCompleted)

        viewModel.setOwnedAmount("stick", 2)
        val afterStick = viewModel.uiState.value
        assertEquals(listOf("iron_ingot"), afterStick.materialRows.map { it.itemId })
        assertTrue(afterStick.stepRows.first { it.itemId == "oak_planks" }.isSatisfiedByParent)

        viewModel.toggleItemComplete("iron_pickaxe", 1)
        assertEquals(1f, viewModel.uiState.value.progress.fraction, 1e-4f)

        viewModel.saveDraftAsProject("Saved Pickaxe")
        assertFalse(viewModel.uiState.value.isDraft)
        assertEquals("Saved Pickaxe", viewModel.uiState.value.projectName)

        viewModel.resetOwnedInventory()
        assertFalse(viewModel.uiState.value.hasOwnedItems)
        assertEquals(0f, viewModel.uiState.value.progress.fraction, 1e-4f)
    }

    @Test
    fun reactsToPreferredFuelSettingChange() = kotlinx.coroutines.test.runTest {
        val projectRepo = FakeProjectRepository(
            listOf(
                CraftingProject(
                    id = 3L,
                    name = "Smelting",
                    isDraft = false,
                    targets = mapOf("smooth_stone" to 1)
                )
            )
        )
        val prefsRepo = FakeUserPreferencesRepository(
            UserPreferences(preferredFuel = PreferredFuel.COAL)
        )
        val viewModel = CraftingScreenViewModel(fakeRepository(), projectRepo, prefsRepo)
        viewModel.loadProject(3L)

        assertTrue(viewModel.uiState.value.plan.rawMaterials.containsKey("coal"))

        prefsRepo.setPreferredFuel(PreferredFuel.LAVA_BUCKET)
        assertTrue(viewModel.uiState.value.plan.rawMaterials.containsKey("lava_bucket"))
    }

    @Test
    fun reactsToPreferredWoodAndStoneSettingChanges() = kotlinx.coroutines.test.runTest {
        val projectRepo = FakeProjectRepository(
            listOf(
                CraftingProject(
                    id = 4L,
                    name = "Prefs Test",
                    isDraft = false,
                    targets = mapOf("chest" to 1, "furnace" to 1)
                )
            )
        )
        val prefsRepo = FakeUserPreferencesRepository(
            UserPreferences(
                preferredWood = PreferredWood.OAK,
                preferredStone = PreferredStone.COBBLESTONE
            )
        )
        val viewModel = CraftingScreenViewModel(fakeRepository(), projectRepo, prefsRepo)
        viewModel.loadProject(4L)

        assertTrue(viewModel.uiState.value.plan.rawMaterials.containsKey("oak_log"))
        assertTrue(viewModel.uiState.value.plan.rawMaterials.containsKey("cobblestone"))
        assertEquals(
            mapOf("oak_planks" to 8),
            viewModel.getRecipesForResult("chest").single().requirements
        )

        prefsRepo.setPreferredWood(PreferredWood.SPRUCE)
        prefsRepo.setPreferredStone(PreferredStone.BLACKSTONE)

        val updated = viewModel.uiState.value
        assertEquals(mapOf("spruce_log" to 2, "blackstone" to 8), updated.plan.rawMaterials)
        assertTrue(updated.materialRows.any { it.itemId == "spruce_log" })
        assertTrue(updated.materialRows.any { it.itemId == "blackstone" })
        assertTrue(updated.stepRows.any { it.itemId == "spruce_planks" })
        assertEquals(
            mapOf("spruce_planks" to 8),
            updated.stepRows.first { it.itemId == "chest" }.recipe.requirements
        )
        assertEquals(
            mapOf("spruce_planks" to 8),
            viewModel.getRecipesForResult("chest").single().requirements
        )
        assertEquals(
            mapOf("spruce_planks" to 8),
            updated.plan.tree.first { it.itemId == "chest" }.selectedRecipe?.requirements
        )
    }
}
