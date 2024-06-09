package com.fracta7.crafter.ui.helper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DrawItem(modifier: Modifier = Modifier, itemID: String, iconSize: Dp = 64.dp) {
    val imageBitmap =
        ImageBitmap.imageResource(id = getItemIcon(itemID))
    val bitmapPainter = BitmapPainter(
        image = imageBitmap,
        filterQuality = FilterQuality.None
    )
    Image(
        painter = bitmapPainter,
        contentDescription = "item icon",
        modifier = modifier
            .size(iconSize)
            .padding(4.dp),
    )
}