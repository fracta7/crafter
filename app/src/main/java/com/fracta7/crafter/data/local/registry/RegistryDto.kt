package com.fracta7.crafter.data.local.registry

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeLayout
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.TagID
import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val id: ItemID,
    val name: String,
    val stackSize: Int = 64,
    val decomposable: Boolean = false,
    val tags: List<TagID> = listOf("other")
) {
    fun toDomain(): Item = Item(
        id = id,
        name = name,
        stackSize = stackSize,
        decomposable = decomposable,
        tags = tags
    )
}

@Serializable
data class RecipeDto(
    val result: ItemID,
    val amount: Int = 1,
    val recipeType: RecipeTypeID,
    val requirements: Map<ItemID, Int> = emptyMap(),
    val tagRequirements: Map<TagID, Int> = emptyMap(),
    val layout: RecipeLayout? = null
) {
    fun toDomain(): Recipe = Recipe(
        result = result,
        amount = amount,
        requirements = requirements,
        recipeType = recipeType,
        tagRequirements = tagRequirements,
        layout = layout
    )
}

@Serializable
data class CategoryDto(
    val id: TagID,
    val name: String,
    val item: ItemID
) {
    fun toDomain(): Category = Category(
        id = id,
        name = name,
        item = item
    )
}

@Serializable
data class RecipeTypeDto(
    val id: RecipeTypeID,
    val name: String,
    val item: ItemID
) {
    fun toDomain(): RecipeType = RecipeType(
        id = id,
        name = name,
        item = item
    )
}
