package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.TagRegistry
import com.fracta7.crafter.domain.usecases.RecipePreferences
import com.fracta7.crafter.domain.usecases.resolveRecipeTags
import com.fracta7.crafter.domain.usecases.resolveTagItemId
import org.junit.Assert.assertEquals
import org.junit.Test

class TagResolverTest {

    private val tagRegistry = TagRegistry(
        mapOf(
            "minecraft:planks" to listOf("oak_planks", "spruce_planks", "crimson_planks", "bamboo_planks"),
            "minecraft:wooden_slabs" to listOf("oak_slab", "spruce_slab", "crimson_slab", "bamboo_slab"),
            "minecraft:logs_that_burn" to listOf("oak_log", "spruce_log", "birch_log"),
            "minecraft:logs" to listOf("oak_log", "spruce_log", "crimson_stem", "warped_stem"),
            "minecraft:stone_crafting_materials" to listOf("cobblestone", "blackstone", "cobbled_deepslate"),
            "minecraft:wool" to listOf("black_wool", "red_wool", "white_wool"),
            "minecraft:coals" to listOf("charcoal", "coal"),
            "minecraft:misc_tag" to listOf("first_item", "second_item")
        )
    )

    private val itemRegistry = ItemRegistry().apply {
        listOf(
            "oak_planks", "spruce_planks", "crimson_planks", "bamboo_planks",
            "oak_slab", "spruce_slab", "crimson_slab", "bamboo_slab",
            "oak_log", "spruce_log", "birch_log", "crimson_stem", "warped_stem",
            "cobblestone", "blackstone", "cobbled_deepslate",
            "black_wool", "red_wool", "white_wool", "charcoal", "coal",
            "first_item", "second_item", "redstone"
        ).forEach { id -> addItem(Item(id, id, 64, decomposable = false)) }
    }

    @Test
    fun resolvesWoodStoneWoolAndCoalTagsWithPreferences() {
        val sprucePrefs = RecipePreferences(preferredWoodId = "spruce", preferredStoneId = "blackstone")
        assertEquals(
            "spruce_planks",
            resolveTagItemId("minecraft:planks", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "spruce_slab",
            resolveTagItemId("minecraft:wooden_slabs", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "spruce_log",
            resolveTagItemId("minecraft:logs_that_burn", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "blackstone",
            resolveTagItemId("minecraft:stone_crafting_materials", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "white_wool",
            resolveTagItemId("minecraft:wool", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "coal",
            resolveTagItemId("minecraft:coals", tagRegistry, sprucePrefs, itemRegistry)
        )
        assertEquals(
            "first_item",
            resolveTagItemId("minecraft:misc_tag", tagRegistry, sprucePrefs, itemRegistry)
        )
    }

    @Test
    fun handlesCrimsonStemVsBurnableLogFallbacksAndMaterializesRecipeTags() {
        val crimsonPrefs = RecipePreferences(preferredWoodId = "crimson", preferredStoneId = "cobbled_deepslate")

        assertEquals(
            "crimson_stem",
            resolveTagItemId("minecraft:logs", tagRegistry, crimsonPrefs, itemRegistry)
        )
        assertEquals(
            "oak_log",
            resolveTagItemId("minecraft:logs_that_burn", tagRegistry, crimsonPrefs, itemRegistry)
        )

        val pistonTemplate = Recipe(
            result = "piston",
            amount = 1,
            requirements = mapOf("cobblestone" to 4, "iron_ingot" to 1, "redstone" to 1),
            recipeType = "crafting_shaped",
            tagRequirements = mapOf("minecraft:planks" to 3)
        )
        val resolved = resolveRecipeTags(pistonTemplate, tagRegistry, crimsonPrefs, itemRegistry)
        assertEquals(
            mapOf("cobblestone" to 4, "iron_ingot" to 1, "redstone" to 1, "crimson_planks" to 3),
            resolved.requirements
        )
        assertEquals(emptyMap<String, Int>(), resolved.tagRequirements)
    }
}
