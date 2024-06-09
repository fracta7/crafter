package com.fracta7.crafter.domain.model


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
sealed class RecipeType(val name: String, val item: String) {
    data object Crafting : RecipeType(name = "Crafting", item = "crafting_table")
    data object Smelting : RecipeType(name = "Smelting", item = "furnace")
    data object Stripping : RecipeType(name = "Stripping", item = "diamond_axe")
    data object Watering : RecipeType(name = "Watering", item = "water_bucket")
    data object Cutting : RecipeType(name = "Cutting", item = "stonecutter")
    data object Oxidation : RecipeType(name = "Oxidation", item = "oxidized_copper")
    data object Waxing : RecipeType(name = "Waxing", item = "honeycomb")
    data object Smithing : RecipeType(name = "Smithing", item = "smithing_table")
}
