package com.fracta7.crafter.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.usecases.CraftingNode

@Composable
fun CraftingElement(
    modifier: Modifier = Modifier,
    node: CraftingNode,
    resolveItem: (ItemID) -> Item?,
    resolveRecipeType: (RecipeTypeID) -> RecipeType,
    onSelectRecipe: (ItemID, Int) -> Unit
) {
    val item = resolveItem(node.itemId) ?: return
    var toggle by remember(node.itemId) { mutableStateOf(false) }
    var showDialog by remember(node.itemId) { mutableStateOf(false) }
    val selectedRecipe = node.selectedRecipe
    val recipes = node.availableRecipes

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = selectedRecipe != null) { toggle = !toggle }
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ItemElement(
                modifier = Modifier.weight(1f),
                item = item,
                amount = node.requiredAmount,
                preview = false
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (recipes.size > 1) {
                    if (showDialog) {
                        RecipeDialog(
                            recipes = recipes,
                            onDismissRequest = { showDialog = false },
                            onItemSelected = { index ->
                                onSelectRecipe(node.itemId, index)
                            },
                            selected = node.selectedRecipeIndex,
                            resolveItem = resolveItem,
                            resolveRecipeType = resolveRecipeType
                        )
                    }
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_swap_horiz_24),
                            contentDescription = "Recipe Selector Button"
                        )
                    }
                }
                if (selectedRecipe != null) {
                    val icon =
                        if (toggle) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                    Icon(icon, contentDescription = "dropdown")
                }
            }
        }
        AnimatedVisibility(visible = toggle && selectedRecipe != null) {
            if (selectedRecipe != null) {
                val recipeType = resolveRecipeType(selectedRecipe.recipeType)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DrawItem(itemID = recipeType.item, iconSize = 48.dp)
                        Text(
                            text = recipeType.name,
                            fontWeight = FontWeight.Bold
                        )
                        if (node.leftover > 0) {
                            Text(
                                text = "(${node.leftover} leftovers)",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .basicMarquee()
                                    .padding(horizontal = 4.dp)
                            )
                        }
                    }
                    OutlinedCard {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            node.children.forEach { childNode ->
                                CraftingElement(
                                    node = childNode,
                                    resolveItem = resolveItem,
                                    resolveRecipeType = resolveRecipeType,
                                    onSelectRecipe = onSelectRecipe
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}
