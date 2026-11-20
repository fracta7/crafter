package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.usecases.RecipePreferences
import com.fracta7.crafter.domain.usecases.solveCraftingPlan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CraftingSolverTest {

    private fun buildRegistry(
        items: List<Item>,
        recipes: List<Recipe>
    ): Pair<ItemRegistry, RecipeRegistry> {
        val itemRegistry = ItemRegistry().apply { items.forEach { addItem(it) } }
        val recipeRegistry = RecipeRegistry().apply { recipes.forEach { addRecipe(it) } }
        return itemRegistry to recipeRegistry
    }

    @Test
    fun aggregatesSharedIntermediateDemandAcrossMultipleTargets() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("oak_log", "Oak Log", 64, decomposable = false),
                Item("oak_planks", "Oak Planks", 64, decomposable = true),
                Item("stick", "Stick", 64, decomposable = true),
                Item("chest", "Chest", 64, decomposable = true),
                Item("wooden_pickaxe", "Wooden Pickaxe", 1, decomposable = true)
            ),
            recipes = listOf(
                Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"),
                Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"),
                Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"),
                Recipe("wooden_pickaxe", 1, mapOf("oak_planks" to 3, "stick" to 2), "crafting_shaped")
            )
        )

        // Pickaxe needs 3 planks + 2 sticks (1 batch of sticks = 2 planks, leaving 2 sticks).
        // Chest needs 8 planks.
        // Aggregated planks needed = 8 + 3 + 2 = 13 -> 4 log crafts (16 planks, 3 leftover).
        val plan = solveCraftingPlan(
            targets = mapOf("chest" to 1, "wooden_pickaxe" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )

        assertEquals(mapOf("oak_log" to 4), plan.rawMaterials)
        assertEquals(mapOf("stick" to 2, "oak_planks" to 3), plan.leftovers)
    }

    @Test
    fun exactMultipleDoesNotProducePhantomRawItemOrZeroLeftover() {
        // Regression test for the old ItemDecompose bug where amount % recipe.amount == 0
        // inserted a 0 entry into rawMaterialsLeftOvers and then added +1 raw material.
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("oak_log", "Oak Log", 64, decomposable = false),
                Item("oak_planks", "Oak Planks", 64, decomposable = true),
                Item("crafting_table", "Crafting Table", 64, decomposable = true),
                Item("ladder", "Ladder", 64, decomposable = true),
                Item("stick", "Stick", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"),
                Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"),
                Recipe("crafting_table", 1, mapOf("oak_planks" to 4), "crafting_shaped"),
                Recipe("ladder", 3, mapOf("stick" to 7), "crafting_shaped")
            )
        )

        // Needs 3 ladders (1 craft exact) -> 7 sticks -> 2 crafts (8 sticks, 1 leftover), consuming 4 planks.
        // Plus 4 planks for crafting_table = 8 planks exact -> 2 logs, 0 plank/ladder leftovers.
        val plan = solveCraftingPlan(
            targets = mapOf("crafting_table" to 1, "ladder" to 3),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )

        assertEquals(mapOf("oak_log" to 2), plan.rawMaterials)
        assertEquals(mapOf("stick" to 1), plan.leftovers)
        assertTrue(plan.leftovers.values.all { it > 0 })
    }

    @Test
    fun recipeSelectionOverridesRecalculateRawMaterialsLeftoversStepsAndTree() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("stone", "Stone", 64, decomposable = false),
                Item("cobblestone", "Cobblestone", 64, decomposable = false),
                Item("coal", "Coal", 64, decomposable = false),
                Item("lava_bucket", "Lava Bucket", 1, decomposable = false),
                Item("fuel", "Fuel", 64, decomposable = true),
                Item("stone_slab", "Stone Slab", 64, decomposable = true)
            ),
            recipes = listOf(
                // Fuel recipes sorted by efficiency: lava_bucket (100/1) first, coal (8/1) second.
                Recipe("fuel", 100, mapOf("lava_bucket" to 1), "fuel"),
                Recipe("fuel", 8, mapOf("coal" to 1), "fuel"),
                // Slab recipes sorted by efficiency: crafting (6/3 = 2.0, reqs 3) vs stonecutting (2/1 = 2.0, reqs 1).
                Recipe("stone_slab", 6, mapOf("stone" to 3), "crafting_shaped"),
                Recipe("stone_slab", 2, mapOf("stone" to 1), "stonecutting")
            )
        )

        // Default chooses stonecutting (2/1, fewer total reqs: 1) -> 1 slab needs 1 stone, 1 leftover.
        val defaultPlan = solveCraftingPlan(
            targets = mapOf("stone_slab" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )
        assertEquals(mapOf("stone" to 1), defaultPlan.rawMaterials)
        assertEquals(mapOf("stone_slab" to 1), defaultPlan.leftovers)

        // Switching stone_slab to index 1 (crafting_shaped: 6 slabs from 3 stone) yields 5 leftovers.
        val switchedPlan = solveCraftingPlan(
            targets = mapOf("stone_slab" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            selectedRecipes = mapOf("stone_slab" to 1)
        )
        assertEquals(mapOf("stone" to 3), switchedPlan.rawMaterials)
        assertEquals(mapOf("stone_slab" to 5), switchedPlan.leftovers)
        assertEquals(1, switchedPlan.tree.first().selectedRecipeIndex)
        assertEquals(5, switchedPlan.tree.first().leftover)
    }

    @Test
    fun subtractsOwnedInventoryFromRawAndIntermediateNeeds() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("iron_ingot", "Iron Ingot", 64, decomposable = false),
                Item("oak_log", "Oak Log", 64, decomposable = false),
                Item("oak_planks", "Oak Planks", 64, decomposable = true),
                Item("stick", "Stick", 64, decomposable = true),
                Item("iron_pickaxe", "Iron Pickaxe", 1, decomposable = true)
            ),
            recipes = listOf(
                Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"),
                Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"),
                Recipe("iron_pickaxe", 1, mapOf("iron_ingot" to 3, "stick" to 2), "crafting_shaped")
            )
        )

        // Own 1 iron_ingot and 2 sticks -> still need 2 iron_ingots, 0 sticks, 0 logs.
        val plan = solveCraftingPlan(
            targets = mapOf("iron_pickaxe" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            ownedInventory = mapOf("iron_ingot" to 1, "stick" to 2, "oak_log" to 99)
        )

        assertEquals(mapOf("iron_ingot" to 2), plan.rawMaterials)
        assertTrue(plan.leftovers.isEmpty())
        assertEquals(listOf("iron_pickaxe"), plan.steps.map { it.itemId })
    }

    @Test
    fun breaksDirectAndMutualRecipeCyclesSafely() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("diamond", "Diamond", 64, decomposable = false),
                Item("trim", "Trim", 64, decomposable = true),
                Item("cycle_a", "Cycle A", 64, decomposable = true),
                Item("cycle_b", "Cycle B", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe("trim", 2, mapOf("trim" to 1, "diamond" to 7), "crafting_shaped"),
                Recipe("cycle_a", 1, mapOf("cycle_b" to 1), "crafting_shapeless"),
                Recipe("cycle_b", 1, mapOf("cycle_a" to 1), "crafting_shapeless")
            )
        )

        val trimPlan = solveCraftingPlan(
            targets = mapOf("trim" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )
        assertEquals(mapOf("trim" to 1), trimPlan.rawMaterials)

        val cyclePlan = solveCraftingPlan(
            targets = mapOf("cycle_a" to 2),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )
        assertEquals(mapOf("cycle_b" to 2), cyclePlan.rawMaterials)
    }

    @Test
    fun ordersStepsBottomUpByDependencyDepth() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("oak_log", "Oak Log", 64, decomposable = false),
                Item("oak_planks", "Oak Planks", 64, decomposable = true),
                Item("stick", "Stick", 64, decomposable = true),
                Item("ladder", "Ladder", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"),
                Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"),
                Recipe("ladder", 3, mapOf("stick" to 7), "crafting_shaped")
            )
        )

        val plan = solveCraftingPlan(
            targets = mapOf("ladder" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )

        assertEquals(listOf("oak_planks", "stick", "ladder"), plan.steps.map { it.itemId })
        assertEquals(listOf(0, 1, 2), plan.steps.map { it.depth })
    }

    @Test
    fun treeNodeCalculatesExactLeftoverInsteadOfModuloZero() {
        // Regression test for CraftingElement L109 `(crafts * recipe.amount) % amount`,
        // which returned `8 % 1 = 0` leftovers when 1 item was requested from an 8-yield recipe.
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("glass", "Glass", 64, decomposable = false),
                Item("white_dye", "White Dye", 64, decomposable = false),
                Item("white_stained_glass", "White Stained Glass", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe(
                    result = "white_stained_glass",
                    amount = 8,
                    requirements = mapOf("glass" to 8, "white_dye" to 1),
                    recipeType = "crafting_shaped"
                )
            )
        )

        val plan = solveCraftingPlan(
            targets = mapOf("white_stained_glass" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )

        assertEquals(7, plan.tree.single().leftover)
        assertEquals(mapOf("white_stained_glass" to 7), plan.leftovers)
    }

    @Test
    fun appliesPreferredWoodStrictlyAcrossPlanWithoutMutatingOakSpecificRecipes() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("oak_log", "Oak Log", 64, decomposable = false),
                Item("oak_planks", "Oak Planks", 64, decomposable = true),
                Item("spruce_log", "Spruce Log", 64, decomposable = false),
                Item("spruce_planks", "Spruce Planks", 64, decomposable = true),
                Item("spruce_slab", "Spruce Slab", 64, decomposable = true),
                Item("stick", "Stick", 64, decomposable = true),
                Item("chest", "Chest", 64, decomposable = true),
                Item("barrel", "Barrel", 64, decomposable = true),
                Item("campfire", "Campfire", 64, decomposable = true),
                Item("coal", "Coal", 64, decomposable = false),
                Item("oak_door", "Oak Door", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"),
                Recipe("spruce_planks", 4, mapOf("spruce_log" to 1), "crafting_shapeless"),
                Recipe("spruce_slab", 6, mapOf("spruce_planks" to 3), "crafting_shaped"),
                Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"),
                Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"),
                Recipe("barrel", 1, mapOf("oak_planks" to 6, "oak_slab" to 2), "crafting_shaped"),
                Recipe("campfire", 1, mapOf("stick" to 3, "coal" to 1, "oak_log" to 3), "crafting_shaped"),
                Recipe("oak_door", 3, mapOf("oak_planks" to 6), "crafting_shaped")
            )
        )

        val sprucePrefs = RecipePreferences(preferredWoodId = "spruce")
        val plan = solveCraftingPlan(
            targets = mapOf("chest" to 1, "barrel" to 1, "campfire" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            recipePreferences = sprucePrefs
        )

        // Zero oak materials should appear in either rawMaterials or steps.
        assertTrue("oak_log" !in plan.rawMaterials)
        assertTrue("oak_planks" !in plan.rawMaterials)
        assertTrue(plan.steps.none { it.itemId.startsWith("oak_") })
        assertTrue("spruce_log" in plan.rawMaterials)
        assertTrue(plan.steps.any { it.itemId == "spruce_planks" })
        assertTrue(plan.steps.any { it.itemId == "spruce_slab" })

        // Oak Door still strictly requires oak_planks -> oak_log.
        val doorPlan = solveCraftingPlan(
            targets = mapOf("oak_door" to 3),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            recipePreferences = sprucePrefs
        )
        assertEquals(mapOf("oak_log" to 2), doorPlan.rawMaterials)
        assertEquals(listOf("oak_planks", "oak_door"), doorPlan.steps.map { it.itemId })
    }

    @Test
    fun appliesPreferredStoneToFurnaceWhileKeepingPistonOnCobblestone() {
        val (itemRegistry, recipeRegistry) = buildRegistry(
            items = listOf(
                Item("cobblestone", "Cobblestone", 64, decomposable = false),
                Item("blackstone", "Blackstone", 64, decomposable = false),
                Item("iron_ingot", "Iron Ingot", 64, decomposable = false),
                Item("redstone", "Redstone Dust", 64, decomposable = false),
                Item("spruce_log", "Spruce Log", 64, decomposable = false),
                Item("spruce_planks", "Spruce Planks", 64, decomposable = true),
                Item("furnace", "Furnace", 64, decomposable = true),
                Item("piston", "Piston", 64, decomposable = true)
            ),
            recipes = listOf(
                Recipe("spruce_planks", 4, mapOf("spruce_log" to 1), "crafting_shapeless"),
                Recipe("furnace", 1, mapOf("cobblestone" to 8), "crafting_shaped"),
                Recipe(
                    "piston",
                    1,
                    mapOf("cobblestone" to 4, "iron_ingot" to 1, "redstone" to 1, "oak_planks" to 3),
                    "crafting_shaped"
                )
            )
        )

        val plan = solveCraftingPlan(
            targets = mapOf("furnace" to 1, "piston" to 1),
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            recipePreferences = RecipePreferences(
                preferredWoodId = "spruce",
                preferredStoneId = "blackstone"
            )
        )

        assertEquals(8, plan.rawMaterials["blackstone"])
        assertEquals(4, plan.rawMaterials["cobblestone"])
        assertEquals(1, plan.rawMaterials["spruce_log"])
    }
}
