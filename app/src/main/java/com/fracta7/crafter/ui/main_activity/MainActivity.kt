package com.fracta7.crafter.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fracta7.crafter.ui.helper.getItemIcon
import com.fracta7.crafter.ui.theme.CrafterTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrafterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainActivityViewModel>()
                    var search by remember { mutableStateOf("") }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            shape = ShapeDefaults.ExtraLarge,
                            label = {
                                Text(
                                    text = "Item name"
                                )
                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            leadingIcon = {
                                Icon(Icons.Rounded.Search, contentDescription = "Search icon")
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    search = ""
                                }) {
                                    Icon(
                                        Icons.Rounded.Clear,
                                        contentDescription = "Clear search query"
                                    )
                                }
                            }
                        )
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            viewModel.state.itemRegistry.getAll()
                                .filter { it.value.name.contains(search, ignoreCase = true) }
                                .forEach { (id, item) ->
                                    item {
                                        val imageBitmap =
                                            ImageBitmap.imageResource(id = getItemIcon(id))
                                        val bitmapPainter = BitmapPainter(
                                            image = imageBitmap,
                                            filterQuality = FilterQuality.None
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Image(
                                                painter = bitmapPainter,
                                                contentDescription = "block",
                                                modifier = Modifier
                                                    .size(64.dp)
                                                    .padding(4.dp),
                                            )
                                            Column {
                                                Text(
                                                    text = item.name,
                                                    modifier = Modifier.padding(4.dp),
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = "Stack size: ${item.stackSize}",
                                                    fontSize = 12.sp,
                                                    modifier = Modifier.padding(
                                                        start = 4.dp,
                                                        bottom = 4.dp,
                                                        end = 4.dp
                                                    )
                                                )
                                            }
                                        }
                                        Divider()
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}