package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.RecipeType

fun recipeTypesInit(): List<RecipeType> {
    return listOf(
        RecipeType(id = "crafting_shapeless", name = "Crafting", item = "crafting_table"),
        RecipeType(id = "crafting_shaped", name = "Crafting", item = "crafting_table"),
        RecipeType(id = "smelting", name = "Smelting", item = "furnace"),
        RecipeType(id = "smoking", name = "Smoking", item = "smoker"),
        RecipeType(id = "blasting", name = "Blasting", item = "blast_furnace"),
        RecipeType(id = "campfire_cooking", name = "Campfire_cooking", item = "campfire"),
        RecipeType(id = "stripping", name = "Stripping", item = "diamond_axe"),
        RecipeType(id = "watering", name = "Watering", item = "water_bucket"),
        RecipeType(id = "stonecutting", name = "Stonecutting", item = "stonecutter"),
        RecipeType(id = "oxidation", name = "Oxidation", item = "oxidized_copper"),
        RecipeType(id = "waxing", name = "Waxing", item = "honeycomb"),
        RecipeType(id = "smithing_transform", name = "Smithing", item = "smithing_table"),
        RecipeType(id = "fuel", name = "Fuel", item = "coal"),
        RecipeType(id = "brewing", name = "Brewing", item = "brewing_stand")
    )
}