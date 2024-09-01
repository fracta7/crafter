package com.fracta7.crafter.data.local.db.mapper

import com.fracta7.crafter.data.local.db.Converters
import com.fracta7.crafter.data.local.db.entity.ItemEntity
import com.fracta7.crafter.domain.model.Item

fun Item.toEntity(): ItemEntity {
    return ItemEntity(
        id = id,
        name = name,
        stackSize = stackSize,
        craftable = decomposable,
        tags = Converters().fromTagList(this.tags),
        custom = this.custom
    )
}

fun ItemEntity.toDomain(): Item {
    return Item(
        id = id!!,
        name = name!!,
        stackSize = stackSize!!,
        decomposable = craftable!!,
        tags = Converters().toTagList(tags!!),
        custom = custom!!
    )
}