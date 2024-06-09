package com.fracta7.crafter.util

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