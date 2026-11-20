package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fracta7.crafter.R
import com.fracta7.crafter.ui.theme.LocalRegistryIconConfig

@Composable
fun DrawItem(modifier: Modifier = Modifier, itemID: String, iconSize: Dp = 64.dp) {
    val iconConfig = LocalRegistryIconConfig.current
    val context = LocalContext.current

    val model: Any = remember(itemID, iconConfig) {
        val fileName = "$itemID.png"
        val isBundled = iconConfig.bundledItemFiles.isEmpty() || fileName in iconConfig.bundledItemFiles
        if (isBundled || iconConfig.iconBaseUrl.isBlank()) {
            "file:///android_asset/items/$fileName"
        } else {
            val remoteUrl = "${iconConfig.iconBaseUrl.trimEnd('/')}/$fileName"
            ImageRequest.Builder(context)
                .data(remoteUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build()
        }
    }

    AsyncImage(
        model = model,
        placeholder = painterResource(id = R.drawable.rounded_cube_24),
        fallback = painterResource(id = R.drawable.rounded_cube_24),
        error = painterResource(id = R.drawable.rounded_cube_24),
        contentDescription = "item image",
        modifier = modifier.size(iconSize)
    )
}
