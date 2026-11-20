package com.fracta7.crafter.domain.usecases

import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.TagID
import com.fracta7.crafter.domain.model.TagRegistry

private val OVERWORLD_LOG_WOODS = setOf(
    "oak",
    "spruce",
    "birch",
    "jungle",
    "acacia",
    "dark_oak",
    "mangrove",
    "cherry",
    "pale_oak",
    "poplar"
)

/**
 * Resolves a Minecraft item [tagId] into a concrete [ItemID] honoring [preferences],
 * or falls back deterministically to the first valid member in [tagRegistry].
 */
fun resolveTagItemId(
    tagId: TagID,
    tagRegistry: TagRegistry,
    preferences: RecipePreferences = RecipePreferences(),
    itemRegistry: ItemRegistry? = null
): ItemID? {
    val normalized = TagRegistry.normalizeTagId(tagId)
    val members = tagRegistry.getItemsForTag(normalized)
    if (members.isEmpty()) return null

    val validMembers = if (itemRegistry != null) {
        members.filter { itemRegistry.getItem(it) != null }
    } else {
        members
    }
    if (validMembers.isEmpty()) return null

    val woodId = preferences.preferredWoodId
    val stoneId = preferences.preferredStoneId

    val candidate: ItemID? = when (normalized) {
        "minecraft:planks", "minecraft:wooden_tool_materials" -> "${woodId}_planks"
        "minecraft:wooden_slabs" -> "${woodId}_slab"
        "minecraft:logs_that_burn" -> {
            if (woodId in OVERWORLD_LOG_WOODS) "${woodId}_log" else "oak_log"
        }
        "minecraft:logs" -> when {
            woodId in OVERWORLD_LOG_WOODS -> "${woodId}_log"
            woodId == "crimson" -> "crimson_stem"
            woodId == "warped" -> "warped_stem"
            else -> "oak_log"
        }
        "minecraft:stone_crafting_materials", "minecraft:stone_tool_materials" -> stoneId
        "minecraft:wool" -> "white_wool"
        "minecraft:carpets" -> "white_carpet"
        "minecraft:coals" -> "coal"
        "minecraft:soul_fire_base_blocks" -> "soul_sand"
        "minecraft:sand" -> "sand"
        else -> {
            // Preserve wood/stone preferences on any species-grouped tag (e.g. *_logs) when not species-locked
            when {
                "${woodId}_planks" in validMembers -> "${woodId}_planks"
                stoneId in validMembers -> stoneId
                else -> validMembers.firstOrNull()
            }
        }
    }

    return if (candidate != null && candidate in validMembers) {
        candidate
    } else {
        validMembers.firstOrNull()
    }
}

/**
 * Materializes [Recipe.tagRequirements] into concrete [Recipe.requirements] using [tagRegistry]
 * and [preferences], keeping [Recipe.layout] intact for UI grid rendering.
 */
fun resolveRecipeTags(
    recipe: Recipe,
    tagRegistry: TagRegistry,
    preferences: RecipePreferences = RecipePreferences(),
    itemRegistry: ItemRegistry? = null
): Recipe {
    if (recipe.tagRequirements.isEmpty()) return recipe

    val merged = linkedMapOf<ItemID, Int>()
    merged.putAll(recipe.requirements)

    for ((tagId, qty) in recipe.tagRequirements) {
        if (qty <= 0) continue
        val resolved = resolveTagItemId(
            tagId = tagId,
            tagRegistry = tagRegistry,
            preferences = preferences,
            itemRegistry = itemRegistry
        ) ?: continue
        merged[resolved] = (merged[resolved] ?: 0) + qty
    }

    return recipe.copy(
        requirements = merged,
        tagRequirements = emptyMap()
    )
}

fun resolveIngredientRef(
    ref: IngredientRef,
    tagRegistry: TagRegistry,
    preferences: RecipePreferences = RecipePreferences(),
    itemRegistry: ItemRegistry? = null
): ItemID {
    return when (ref) {
        is IngredientRef.Item -> ref.id
        is IngredientRef.Tag -> resolveTagItemId(
            tagId = ref.id,
            tagRegistry = tagRegistry,
            preferences = preferences,
            itemRegistry = itemRegistry
        ) ?: TagRegistry.normalizeTagId(ref.id).removePrefix("minecraft:")
    }
}
