package com.fracta7.crafter.util

/**
 * Function for calculating required amount
 * taking into account resulting amount.
 * @param result amount of items the recipe produces.
 * @param amountNeeded amount of items needed for
 * following the recipe.
 */
fun resourceAmount(result: Int, amountNeeded: Int): Int {

    return if (amountNeeded < result) {
        1
    } else {
        if (amountNeeded % result == 0) {
            amountNeeded / result
        } else {
            (amountNeeded / result) + 1
        }
    }
}