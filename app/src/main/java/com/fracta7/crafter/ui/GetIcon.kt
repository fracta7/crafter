package com.fracta7.crafter.ui

import com.fracta7.crafter.R

/**
 * Gets Resource ID for given item id.
 * @param id item ID.
 * @return Resource ID for composable.
 */
fun getItemIcon(id: String): Int{
    val resClass = R.drawable::class.java
    val field = resClass.getField(id)
    return field.getInt(null)
}