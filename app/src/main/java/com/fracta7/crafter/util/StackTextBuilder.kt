package com.fracta7.crafter.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

fun getStackText(amount: Int, stackSize: Int): AnnotatedString{
    return buildAnnotatedString {
        val stack = getStackAmount(amount = amount, stackSize = stackSize)
        val stackText = if (stack.first <= stackSize) "stack" else "stacks"
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