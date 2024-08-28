package com.fracta7.crafter.data.local.db.mapper

import com.fracta7.crafter.data.local.db.Converters
import com.fracta7.crafter.data.local.db.entity.ItemEntity
import com.fracta7.crafter.domain.model.Item

fun Item.toEntity(): ItemEntity {
    return ItemEntity(
        key = null,
        id = id,
        name = name,
        stackSize = stackSize,
        craftable = craftable,
        tags = Converters().fromTagList(this.tags),
        custom = this.custom
    )
}

fun ItemEntity.toDomain(): Item {
    return Item(
        id = id!!,
        name = name!!,
        stackSize = stackSize!!,
        craftable = craftable!!,
        tags = Converters().toTagList(tags!!),
        custom = custom!!
    )
}