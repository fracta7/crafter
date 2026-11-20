package com.fracta7.crafter

import com.fracta7.crafter.domain.usecases.StackBreakdown
import com.fracta7.crafter.domain.usecases.getContainerBreakdown
import com.fracta7.crafter.domain.usecases.getStackAmount
import com.fracta7.crafter.domain.usecases.getStackText
import com.fracta7.crafter.domain.usecases.resourceAmount
import org.junit.Assert.assertEquals
import org.junit.Test

class StackCalculatorTest {

    @Test
    fun getStackAmountHandlesZeroLessThanStackExactAndRemainder() {
        assertEquals(0 to null, getStackAmount(0, 64))
        assertEquals(0 to null, getStackAmount(-5, 64))
        assertEquals(0 to null, getStackAmount(10, 0))
        assertEquals(0 to 17, getStackAmount(17, 64))
        assertEquals(2 to null, getStackAmount(128, 64))
        assertEquals(2 to 7, getStackAmount(135, 64))
        assertEquals(3 to null, getStackAmount(3, 1))
        assertEquals(1 to 5, getStackAmount(21, 16))
    }

    @Test
    fun resourceAmountComputesCeilingBatches() {
        assertEquals(1, resourceAmount(result = 8, amountNeeded = 1))
        assertEquals(1, resourceAmount(result = 8, amountNeeded = 8))
        assertEquals(2, resourceAmount(result = 8, amountNeeded = 9))
        assertEquals(4, resourceAmount(result = 4, amountNeeded = 13))
    }

    @Test
    fun getContainerBreakdownFormatsShulkersAndDoubleChestsAcrossStackSizes() {
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 0, stacks = 0, remainder = 0),
            getContainerBreakdown(amount = 0, stackSize = 64)
        )
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 0, stacks = 0, remainder = 0),
            getContainerBreakdown(amount = 100, stackSize = 0)
        )
        // 1 shulker of 64-stackable items = 27 * 64 = 1728
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 1, stacks = 0, remainder = 0),
            getContainerBreakdown(amount = 1728, stackSize = 64)
        )
        // 1795 = 1 shulker (1728) + 1 stack (64) + 3
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 1, stacks = 1, remainder = 3),
            getContainerBreakdown(amount = 1795, stackSize = 64)
        )
        // 3456 = 2 shulkers when double chests disabled, or 1 double chest when enabled
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 2, stacks = 0, remainder = 0),
            getContainerBreakdown(amount = 3456, stackSize = 64, includeDoubleChests = false)
        )
        assertEquals(
            StackBreakdown(doubleChests = 1, shulkerBoxes = 0, stacks = 0, remainder = 0),
            getContainerBreakdown(amount = 3456, stackSize = 64, includeDoubleChests = true)
        )
        // Stack size 16: 1 shulker = 432
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 1, stacks = 2, remainder = 5),
            getContainerBreakdown(amount = 432 + 32 + 5, stackSize = 16)
        )
        // Stack size 1: 1 shulker = 27
        assertEquals(
            StackBreakdown(doubleChests = 0, shulkerBoxes = 1, stacks = 3, remainder = 0),
            getContainerBreakdown(amount = 30, stackSize = 1)
        )
    }

    @Test
    fun getStackTextFormatsStandardAndContainerUnitsCleanly() {
        assertEquals("Amount: 42", getStackText(amount = 42, stackSize = 64).text)
        assertEquals("Amount: 135 (2 stacks and 7)", getStackText(amount = 135, stackSize = 64).text)
        assertEquals(
            "Amount: 1728 (1 shulker)",
            getStackText(amount = 1728, stackSize = 64, showShulkerUnits = true).text
        )
        assertEquals(
            "Amount: 1795 (1 shulker, 1 stack, 3)",
            getStackText(amount = 1795, stackSize = 64, showShulkerUnits = true).text
        )
        assertEquals(
            "Amount: 1795 (28 stacks and 3)",
            getStackText(amount = 1795, stackSize = 64, showShulkerUnits = false).text
        )
        assertEquals(
            "Amount: 3456 (1 double chest)",
            getStackText(
                amount = 3456,
                stackSize = 64,
                showShulkerUnits = true,
                includeDoubleChests = true
            ).text
        )
    }
}
