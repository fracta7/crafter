package com.fracta7.crafter.domain.model

import kotlinx.serialization.Serializable

/**
 * Sealed class to represent different recipe types.
 * @property Crafting using a Crafting Table.
 * @property Smelting using Furnaces.
 * @property Stripping using an Axe to strip blocks.
 * @property Watering using Water Bucket.
 * @property Cutting using Stone Cutter.
 * @property Oxidation exclusively for Copper oxidation.
 * @property Waxing exclusively for Copper waxing.
 * @property Smithing using smithing table for upgrades.
 */
@Serializable
sealed class RecipeType{
    object Crafting : RecipeType()
    object Smelting : RecipeType()
    object Stripping : RecipeType()
    object Watering : RecipeType()
    object Cutting : RecipeType()
    object Oxidation : RecipeType()
    object Waxing : RecipeType()
    object Smithing : RecipeType()
}
