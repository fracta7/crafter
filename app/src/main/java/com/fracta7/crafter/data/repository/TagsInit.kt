package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.TagID
import com.fracta7.crafter.domain.model.TagName

fun tagsInit(): Map<TagID, TagName> {
    return mapOf(
        "building" to "Building Blocks",
        "colored" to "Colored Blocks",
        "natural" to "Natural Blocks",
        "functional" to "Functional Blocks",
        "redstone" to "Redstone Blocks",
        "tools" to "Tools & Utilities",
        "combat" to "Combat",
        "food" to "Food & Drinks",
        "ingredients" to "Ingredients",
        //"eggs" to "Spawn Eggs",
    )
}