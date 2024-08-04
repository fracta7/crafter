package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.RecipeType

fun recipeTypesInit(): List<RecipeType> {
    return listOf(
        RecipeType(id = "crafting", name = "Crafting", item = "crafting_table"),
        RecipeType(id = "smelting", name = "Smelting", item = "furnace"),
        RecipeType(id = "stripping", name = "Stripping", item = "diamond_axe"),
        RecipeType(id = "watering", name = "Watering", item = "water_bucket"),
        RecipeType(id = "cutting", name = "Cutting", item = "stonecutter"),
        RecipeType(id = "oxidation", name = "Oxidation", item = "oxidized_copper"),
        RecipeType(id = "waxing", name = "Waxing", item = "honeycomb"),
        RecipeType(id = "smithing", name = "Smithing", item = "smithing_table"),
        RecipeType(id = "fuel", name = "Fuel", item = "coal")
    )
}