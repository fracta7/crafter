package com.fracta7.crafter.data.local

import com.fracta7.crafter.domain.model.Category

fun tagsInit(): List<Category> {
    return listOf(
        Category(id = "building", name = "Building Blocks", item = "bricks"),
        Category(id = "colored", name = "Colored Blocks", item = "cyan_wool"),
        Category(id = "natural", name = "Natural Blocks", item = "grass_block"),
        Category(id = "functional", name = "Functional Blocks", item = "oak_sign"),
        Category(id = "redstone", name = "Redstone Blocks", item = "redstone"),
        Category(id = "tools", name = "Tools & Utilities", item = "diamond_pickaxe"),
        Category(id = "combat", name = "Combat", item = "netherite_sword"),
        Category(id = "food", name = "Food & Drinks", item = "golden_apple"),
        Category(id = "ingredients", name = "Ingredients", item = "iron_ingot"),
        Category(id = "potions", name = "Potions", item = "water_bottle"),
        Category(id = "other", name = "Others", item = "structure_block")
    )

//    return mapOf(
//        "building" to "Building Blocks",
//        "colored" to "Colored Blocks",
//        "natural" to "Natural Blocks",
//        "functional" to "Functional Blocks",
//        "redstone" to "Redstone Blocks",
//        "tools" to "Tools & Utilities",
//        "combat" to "Combat",
//        "food" to "Food & Drinks",
//        "ingredients" to "Ingredients",
//        //"eggs" to "Spawn Eggs",
//    )
}