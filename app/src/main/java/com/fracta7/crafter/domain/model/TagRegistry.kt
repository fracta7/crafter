package com.fracta7.crafter.domain.model

/**
 * Registry mapping Minecraft item tag IDs (e.g. `minecraft:planks`) to member item IDs,
 * along with an O(1) reverse index from item ID to all Minecraft tags containing it.
 */
class TagRegistry(
    tags: Map<TagID, List<ItemID>> = emptyMap()
) {
    private val itemsByTag: Map<TagID, List<ItemID>> = tags.mapValues { it.value.toList() }
    private val tagsByItem: Map<ItemID, List<TagID>> = buildMap<ItemID, MutableList<TagID>> {
        itemsByTag.forEach { (tagId, itemIds) ->
            itemIds.forEach { itemId ->
                getOrPut(itemId) { mutableListOf() }.add(tagId)
            }
        }
    }.mapValues { it.value.toList() }

    fun getItemsForTag(tagId: TagID): List<ItemID> {
        val normalized = normalizeTagId(tagId)
        return itemsByTag[normalized] ?: itemsByTag[tagId].orEmpty()
    }

    fun getTagsForItem(itemId: ItemID): List<TagID> {
        return tagsByItem[itemId].orEmpty()
    }

    fun getAllTags(): Map<TagID, List<ItemID>> = itemsByTag

    companion object {
        fun normalizeTagId(raw: TagID): TagID {
            val clean = raw.trim().removePrefix("#")
            return if (':' in clean) clean else "minecraft:$clean"
        }
    }
}
