package com.fracta7.crafter.data.local.db.mapper

import com.fracta7.crafter.data.local.db.Converters
import com.fracta7.crafter.data.local.db.entity.RecipeEntity
import com.fracta7.crafter.domain.model.Recipe

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        result = result,
        resultQuantity = resultQuantity,
        requirements = Converters().fromRequirementsMap(requirements),
        recipeType = this.recipeType,
        custom = this.custom
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        result = result!!,
        resultQuantity = resultQuantity!!,
        requirements = Converters().toRequirementsMap(requirements!!),
        recipeType = recipeType!!,
        custom = custom!!
    )
}