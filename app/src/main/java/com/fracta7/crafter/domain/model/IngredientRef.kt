package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
sealed interface IngredientRef {
    @Immutable
    @Serializable
    @SerialName("item")
    data class Item(val id: ItemID) : IngredientRef

    @Immutable
    @Serializable
    @SerialName("tag")
    data class Tag(val id: TagID) : IngredientRef
}

@Immutable
@Serializable
sealed interface RecipeLayout {
    @Immutable
    @Serializable
    @SerialName("shaped")
    data class CraftingShaped(
        val pattern: List<String>,
        val key: Map<String, IngredientRef>
    ) : RecipeLayout

    @Immutable
    @Serializable
    @SerialName("shapeless")
    data class CraftingShapeless(
        val ingredients: List<IngredientRef>
    ) : RecipeLayout

    @Immutable
    @Serializable
    @SerialName("furnace")
    data class Furnace(
        val input: IngredientRef,
        val requiresFuel: Boolean = true
    ) : RecipeLayout

    @Immutable
    @Serializable
    @SerialName("brewing")
    data class Brewing(
        val base: IngredientRef,
        val reagent: IngredientRef
    ) : RecipeLayout

    @Immutable
    @Serializable
    @SerialName("smithing")
    data class Smithing(
        val template: IngredientRef,
        val base: IngredientRef,
        val addition: IngredientRef
    ) : RecipeLayout

    @Immutable
    @Serializable
    @SerialName("single")
    data class SingleInput(
        val input: IngredientRef,
        val catalyst: IngredientRef? = null
    ) : RecipeLayout
}
