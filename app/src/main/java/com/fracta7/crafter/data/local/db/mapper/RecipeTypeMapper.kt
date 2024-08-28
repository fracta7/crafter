package com.fracta7.crafter.data.local.db.mapper

import com.fracta7.crafter.data.local.db.entity.RecipeTypeEntity
import com.fracta7.crafter.domain.model.RecipeType

fun RecipeType.toEntity(): RecipeTypeEntity {
    return RecipeTypeEntity(
        key = null,
        id = id,
        name = name,
        item = item,
        custom = custom
    )
}

fun RecipeTypeEntity.toDomain(): RecipeType {
    return RecipeType(
        id = id!!,
        name = name!!,
        item = item!!,
        custom = custom!!
    )
}