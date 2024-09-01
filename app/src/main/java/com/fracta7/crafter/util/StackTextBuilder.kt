package com.fracta7.crafter.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

/**
 * Builds annotated text for stack amount and item amount for composable text.
 * @param amount amount of items to display in text.
 * @param stackSize stack size of item.
 * @return annotated string to be used in text composable.
 */
fun getStackText(amount: Int, stackSize: Int): AnnotatedString{
    return buildAnnotatedString {
        val stack = getStackAmount(amount = amount, stackSize = stackSize)
        val stackText = if (stack.first * stackSize <= stackSize) "stack" else "stacks"
        append("Amount: ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(amount.toString())
        }
        if (stack.first != 0) {
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