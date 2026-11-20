package com.fracta7.crafter.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID

data class Info(
    val iconID: Int,
    val infoDescription: String,
    val info: String
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemInfoDialog(
    item: Item,
    modifier: Modifier = Modifier,
    resolveItem: (ItemID) -> Item? = { null },
    getRecipesForResult: (ItemID) -> List<Recipe> = { emptyList() },
    getRecipesUsingIngredient: (ItemID) -> List<Recipe> = { emptyList() },
    resolveRecipeType: (RecipeTypeID) -> RecipeType? = { null },
    resolveIngredientRef: (IngredientRef) -> ItemID = { ref ->
        when (ref) {
            is IngredientRef.Item -> ref.id
            is IngredientRef.Tag -> ref.id.removePrefix("minecraft:")
        }
    },
    preferredFuelItemId: ItemID = "coal",
    onDismissRequest: () -> Unit
) {
    var history by remember(item.id) { mutableStateOf(listOf(item.id)) }
    val currentId = history.last()
    val currentItem = resolveItem(currentId) ?: item

    val craftedBy = remember(currentItem.id) { getRecipesForResult(currentItem.id) }
    val usedIn = remember(currentItem.id) { getRecipesUsingIngredient(currentItem.id) }
    var usedInExpanded by remember(currentItem.id) { mutableStateOf(usedIn.size <= 6) }

    fun navigateTo(targetId: ItemID) {
        if (targetId != currentItem.id && resolveItem(targetId) != null) {
            history = history + targetId
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (history.size > 1) {
                        IconButton(onClick = { history = history.dropLast(1) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Previous item"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismissRequest) {
                        Text(text = "Dismiss")
                    }
                }

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                ) {
                    item(key = "header_${currentItem.id}") {
                        DrawItem(itemID = currentItem.id)
                        Text(
                            text = currentItem.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            maxLines = 1,
                            modifier = Modifier.basicMarquee()
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Item Information",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    items(getInfoList(currentItem)) { info ->
                        InfoRow(
                            iconID = info.iconID,
                            infoDescription = info.infoDescription,
                            info = info.info
                        )
                    }

                    if (craftedBy.isNotEmpty()) {
                        item(key = "crafted_by_header") {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "How to craft (${craftedBy.size})",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                        items(
                            items = craftedBy,
                            key = { "craft_${currentItem.id}_${craftedBy.indexOf(it)}" }
                        ) { recipe ->
                            val recipeType = resolveRecipeType(recipe.recipeType)
                            OutlinedCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    if (recipeType != null) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            DrawItem(itemID = recipeType.item, iconSize = 20.dp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = recipeType.name,
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                    }
                                    RecipeGridView(
                                        recipe = recipe,
                                        resolveRecipeType = resolveRecipeType,
                                        resolveIngredientRef = resolveIngredientRef,
                                        preferredFuelItemId = preferredFuelItemId,
                                        onItemClick = { clickedId -> navigateTo(clickedId) },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }

                    if (usedIn.isNotEmpty()) {
                        item(key = "used_in_section") {
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { usedInExpanded = !usedInExpanded }
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Used to craft… (${usedIn.size})",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = if (usedInExpanded) {
                                        Icons.Rounded.KeyboardArrowUp
                                    } else {
                                        Icons.Rounded.KeyboardArrowDown
                                    },
                                    contentDescription = null
                                )
                            }
                            AnimatedVisibility(visible = usedInExpanded) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    usedIn.forEach { recipe ->
                                        val resultItem = resolveItem(recipe.result)
                                        val recipeType = resolveRecipeType(recipe.recipeType)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { navigateTo(recipe.result) }
                                                .padding(vertical = 6.dp)
                                        ) {
                                            DrawItem(itemID = recipe.result, iconSize = 32.dp)
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = resultItem?.name ?: recipe.result,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    maxLines = 1,
                                                    modifier = Modifier.basicMarquee()
                                                )
                                                Text(
                                                    text = "Produces ×${recipe.amount}",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                            if (recipeType != null) {
                                                DrawItem(
                                                    itemID = recipeType.item,
                                                    iconSize = 24.dp
                                                )
                                            }
                                        }
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(iconID: Int, infoDescription: String, info: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconID),
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
            Text(text = infoDescription)
        }
        Text(
            text = info,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.basicMarquee(iterations = 1000)
        )
    }
}

fun getInfoList(item: Item): List<Info> {
    val tags = item.tags.joinToString(", ")
    return listOf(
        Info(
            iconID = R.drawable.round_id_24,
            infoDescription = "Item ID: ",
            info = item.id
        ),
        Info(
            iconID = R.drawable.round_item_name_24,
            infoDescription = "Item name: ",
            info = item.name
        ),
        Info(
            iconID = R.drawable.round_stack_size_24,
            infoDescription = "Stack size: ",
            info = item.stackSize.toString()
        ),
        Info(
            iconID = R.drawable.round_device_hub_24,
            infoDescription = "Decomposable: ",
            info = item.decomposable.toString()
        ),
        Info(
            iconID = R.drawable.round_tags_24,
            infoDescription = "Tags: ",
            info = tags
        )
    )
}

@Preview
@Composable
fun ItemInfoDialogPreview() {
    val item = Item(
        id = "piston",
        name = "Piston",
        stackSize = 64,
        decomposable = true,
        tags = listOf("redstone")
    )
    MaterialTheme(darkColorScheme()) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ItemInfoDialog(item = item, onDismissRequest = {})
        }
    }
}
