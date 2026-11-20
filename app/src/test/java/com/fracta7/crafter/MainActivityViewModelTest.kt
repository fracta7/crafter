package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.ui.main_activity.MainActivityViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun fakeRepository(): AppRepository {
        val items = ItemRegistry().apply {
            addItem(Item("stone", "Stone", 64, decomposable = false, tags = listOf("building")))
            addItem(Item("redstone", "Redstone Dust", 64, decomposable = false, tags = listOf("redstone")))
            addItem(Item("repeater", "Redstone Repeater", 64, decomposable = true, tags = listOf("redstone")))
            addItem(Item("piston", "Piston", 64, decomposable = true, tags = listOf("redstone")))
            addItem(Item("oak_planks", "Oak Planks", 64, decomposable = true, tags = listOf("building")))
            addItem(Item("spruce_planks", "Spruce Planks", 64, decomposable = true, tags = listOf("building")))
            addItem(Item("chest", "Chest", 64, decomposable = true, tags = listOf("functional")))
            addItem(Item("oak_door", "Oak Door", 64, decomposable = true, tags = listOf("building")))
        }
        val recipes = RecipeRegistry().apply {
            addRecipe(Recipe("piston", 1, mapOf("redstone" to 1, "stone" to 4), "crafting_shaped"))
            addRecipe(Recipe("repeater", 1, mapOf("redstone" to 3, "stone" to 3), "crafting_shaped"))
            addRecipe(Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"))
            addRecipe(Recipe("oak_door", 3, mapOf("oak_planks" to 6), "crafting_shaped"))
        }
        val categories = listOf(
            Category("building", "Building Blocks", "stone"),
            Category("redstone", "Redstone Blocks", "redstone")
        )
        val recipeTypes = mapOf(
            "crafting_shaped" to RecipeType("crafting_shaped", "Crafting", "crafting_table")
        )
        return FakeAppRepository.from(items, recipes, recipeTypes, categories)
    }

    @Test
    fun filtersByPreIndexedCategoriesAndSearchQuery() {
        val viewModel = MainActivityViewModel(
            fakeRepository(),
            FakeProjectRepository(),
            FakeUserPreferencesRepository()
        )
        assertEquals(2, viewModel.catalogState.value.sections.size)

        viewModel.onSearchQueryChange("redstone")
        val searchSections = viewModel.catalogState.value.sections
        assertEquals(1, searchSections.size)
        assertEquals("redstone", searchSections.single().category.id)
        assertEquals(listOf("redstone", "repeater"), searchSections.single().items.map { it.id })

        viewModel.onSearchQueryChange("")
        viewModel.toggleAllCategories()
        assertTrue(viewModel.catalogState.value.selectedCategories.isEmpty())
        assertTrue(viewModel.catalogState.value.sections.isEmpty())

        viewModel.toggleAllCategories()
        assertEquals(setOf("building", "redstone"), viewModel.catalogState.value.selectedCategories)

        viewModel.selectOnlyCategory("building")
        val buildingOnly = viewModel.catalogState.value.sections
        assertEquals(1, buildingOnly.size)
        assertEquals("building", buildingOnly.single().category.id)
    }

    @Test
    fun hydratesDraftAndUpdatesQueueAndSavesProject() = runTest {
        val stone = Item("stone", "Stone", 64, decomposable = false, tags = listOf("building"))
        val projectRepo = FakeProjectRepository(
            listOf(
                CraftingProject(
                    id = 1L,
                    name = "Current Draft",
                    isDraft = true,
                    targets = mapOf("stone" to 10)
                )
            )
        )
        val viewModel = MainActivityViewModel(
            fakeRepository(),
            projectRepo,
            FakeUserPreferencesRepository()
        )

        assertEquals(10, viewModel.items[stone])

        viewModel.adjustItemAmount(stone, 5)
        assertEquals(15, viewModel.items[stone])

        viewModel.setItemAmount(stone, 64)
        assertEquals(64, viewModel.items[stone])

        var savedId = -1L
        viewModel.saveQueueAsProject("Stone Build") { savedId = it }
        assertTrue(savedId > 0)
        assertEquals("Stone Build", projectRepo.getProjectById(savedId)?.name)
        assertEquals(mapOf("stone" to 64), projectRepo.getProjectById(savedId)?.targets)

        viewModel.adjustItemAmount(stone, -64)
        assertFalse(viewModel.items.containsKey(stone))
    }

    @Test
    fun itemInfoRecipesReflectPreferredWoodSetting() = runTest {
        val prefsRepo = FakeUserPreferencesRepository(
            UserPreferences(preferredWood = PreferredWood.SPRUCE)
        )
        val viewModel = MainActivityViewModel(fakeRepository(), FakeProjectRepository(), prefsRepo)

        val chestRecipe = viewModel.getRecipesForResult("chest").single()
        assertEquals(mapOf("spruce_planks" to 8), chestRecipe.requirements)

        val doorRecipe = viewModel.getRecipesForResult("oak_door").single()
        assertEquals(mapOf("oak_planks" to 6), doorRecipe.requirements)

        val spruceUses = viewModel.getRecipesUsingIngredient("spruce_planks")
        assertEquals(listOf("chest"), spruceUses.map { it.result })

        val oakUses = viewModel.getRecipesUsingIngredient("oak_planks")
        assertEquals(listOf("oak_door"), oakUses.map { it.result })
    }
}
