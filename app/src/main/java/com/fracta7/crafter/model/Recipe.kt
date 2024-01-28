package com.fracta7.crafter.model

data class Recipe(
    val result: Item,
    val resultQuantity: Int,
    val requirements: Map<Item, Int>
)