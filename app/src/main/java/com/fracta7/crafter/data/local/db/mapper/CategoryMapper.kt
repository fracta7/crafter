package com.fracta7.crafter.data.local.db.mapper

import com.fracta7.crafter.data.local.db.entity.CategoryEntity
import com.fracta7.crafter.domain.model.Category

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        item = item,
        custom = custom
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id!!,
        name = name!!,
        item = item!!,
        custom = custom!!
    )
}