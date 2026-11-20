package com.fracta7.crafter.domain.usecases

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

/**
 * Builds annotated text for stack/container amount and item amount for composable text.
 */
fun getStackText(
    amount: Int,
    stackSize: Int,
    showShulkerUnits: Boolean = true,
    includeDoubleChests: Boolean = false
): AnnotatedString {
    return buildAnnotatedString {
        append("Amount: ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(amount.toString())
        }

        if (showShulkerUnits && stackSize > 0 && amount >= SLOTS_PER_SHULKER * stackSize) {
            val breakdown = getContainerBreakdown(
                amount = amount,
                stackSize = stackSize,
                includeDoubleChests = includeDoubleChests
            )
            val parts = buildList<Pair<Int, String?>> {
                if (breakdown.doubleChests > 0) {
                    add(
                        breakdown.doubleChests to
                            if (breakdown.doubleChests == 1) "double chest" else "double chests"
                    )
                }
                if (breakdown.shulkerBoxes > 0) {
                    add(
                        breakdown.shulkerBoxes to
                            if (breakdown.shulkerBoxes == 1) "shulker" else "shulkers"
                    )
                }
                if (breakdown.stacks > 0) {
                    add(
                        breakdown.stacks to
                            if (breakdown.stacks == 1) "stack" else "stacks"
                    )
                }
                if (breakdown.remainder > 0) {
                    add(breakdown.remainder to null)
                }
            }
            if (parts.isNotEmpty()) {
                append(" (")
                parts.forEachIndexed { index, (value, label) ->
                    if (index > 0) append(", ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(value.toString())
                    }
                    if (label != null) {
                        append(" $label")
                    }
                }
                append(")")
            }
        } else {
            val stack = getStackAmount(amount = amount, stackSize = stackSize)
            if (stack.first != 0) {
                val stackText = if (stack.first == 1) "stack" else "stacks"
                append(" (")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stack.first.toString())
                }
                append(" $stackText")
                if (stack.second != null) {
                    append(" and ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stack.second.toString())
                    }
                }
                append(")")
            }
        }
    }
}
