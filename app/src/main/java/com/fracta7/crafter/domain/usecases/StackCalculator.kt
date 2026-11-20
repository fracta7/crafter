package com.fracta7.crafter.domain.usecases

const val SLOTS_PER_SHULKER = 27
const val SLOTS_PER_DOUBLE_CHEST = 54

data class StackBreakdown(
    val doubleChests: Int,
    val shulkerBoxes: Int,
    val stacks: Int,
    val remainder: Int
)

/**
 * Converts number of items to stacks and items.
 * @param amount amount of items to be converted
 * @param stackSize stack size of given item
 * @return a pair of stack amount and item amount. First - stack amount, second - item amount.
 */
fun getStackAmount(amount: Int, stackSize: Int): Pair<Int, Int?> {
    if (amount <= 0 || stackSize <= 0) return Pair(0, null)
    val fullStacks = amount / stackSize
    val remainder = amount % stackSize
    return if (remainder == 0) {
        Pair(fullStacks, null)
    } else {
        Pair(fullStacks, remainder)
    }
}

/**
 * Breaks down [amount] into double chests (optional), shulker boxes, stacks, and loose items.
 */
fun getContainerBreakdown(
    amount: Int,
    stackSize: Int,
    includeDoubleChests: Boolean = false
): StackBreakdown {
    if (amount <= 0 || stackSize <= 0) {
        return StackBreakdown(doubleChests = 0, shulkerBoxes = 0, stacks = 0, remainder = 0)
    }
    var remaining = amount
    val doubleChests = if (includeDoubleChests) {
        val capacity = SLOTS_PER_DOUBLE_CHEST * stackSize
        val count = remaining / capacity
        remaining %= capacity
        count
    } else {
        0
    }
    val shulkerCapacity = SLOTS_PER_SHULKER * stackSize
    val shulkerBoxes = remaining / shulkerCapacity
    remaining %= shulkerCapacity

    val stacks = remaining / stackSize
    val remainder = remaining % stackSize

    return StackBreakdown(
        doubleChests = doubleChests,
        shulkerBoxes = shulkerBoxes,
        stacks = stacks,
        remainder = remainder
    )
}
