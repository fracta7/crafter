package com.fracta7.crafter.util

/**
 * Converts number of items to stacks and items
 * @param amount amount of items to be converted
 * @param stackSize stack size of given item
 * @return a pair of stack amount and item amount. First - stack amount, second - item amount.
 */
fun getStackAmount(amount: Int, stackSize: Int): Pair<Int, Int?> {
    return when {
        amount <= 0 -> Pair(0, null)
        else -> {
            val fullStacks = amount / stackSize
            val remainder = amount % stackSize
            if (remainder == 0) {
                Pair(fullStacks, null)
            } else {
                Pair(fullStacks, remainder)
            }
        }
    }
}