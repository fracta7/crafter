package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.helper.DrawItem
import com.fracta7.crafter.ui.helper.getItemIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemElement(modifier: Modifier, item: Item, amount: Int = 64, preview: Boolean = true) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        DrawItem(itemID = item.id)
        Column {
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(4.dp)
                    .basicMarquee(),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            val text =
                if (preview) {
                    buildAnnotatedString {
                        append("Stack size: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(item.stackSize.toString())
                        }
                    }
                } else {
                    buildAnnotatedString {
                        append("Amount: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(amount.toString())
                        }
                    }
                }

            Text(
                text = text,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        bottom = 4.dp,
                        end = 4.dp
                    )
                    .basicMarquee()
                ,
                maxLines = 1
            )
        }
    }
}