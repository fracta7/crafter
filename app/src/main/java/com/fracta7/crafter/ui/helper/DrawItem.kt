package com.fracta7.crafter.ui.helper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fracta7.crafter.R

@Composable
fun DrawItem(modifier: Modifier = Modifier, itemID: String, iconSize: Dp = 64.dp) {
    AsyncImage(
        model = "file:///android_asset/items/$itemID.png",
        placeholder = painterResource(id = R.drawable.rounded_cube_24),
        contentDescription = "item image",
        modifier = modifier.size(iconSize)
    )
}