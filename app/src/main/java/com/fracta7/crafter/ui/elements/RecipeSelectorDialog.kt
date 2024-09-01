package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.repository.AppRepository

@Composable
fun RecipeDialog(
    recipes: List<Recipe>,
    onDismissRequest: () -> Unit,
    onItemSelected: (Int) -> Unit,
    selected: Int,
    appRepository: AppRepository
) {
    val size = 40.dp
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Select a Recipe",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn {
                    itemsIndexed(recipes) { index, recipe ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected(index)
                                    onDismissRequest()
                                }
                        ) {
                            val recipeType = appRepository.getRecipeType(recipe.recipeType)
                            val item = appRepository.itemRegistryProvider().getItem(recipe.result)!!
                            recipe.requirements.forEach { (rItemID, _) ->
                                Column {
                                    DrawItem(itemID = rItemID, iconSize = size)
                                }
                            }

                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "pointing arrow"
                            )

                            DrawItem(itemID = item.id, iconSize = size)

                            Icon(
                                painter = painterResource(id = R.drawable.rounded_arrow_range_24),
                                contentDescription = "arrow range icon"
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                DrawItem(itemID = recipeType.item, iconSize = size)
                                RadioButton(
                                    selected = index == selected,
                                    onClick = {
                                        onItemSelected(index)
                                        onDismissRequest()
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}