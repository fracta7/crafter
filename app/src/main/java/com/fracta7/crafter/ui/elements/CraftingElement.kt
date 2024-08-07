package com.fracta7.crafter.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.ui.helper.DrawItem
import com.fracta7.crafter.util.resourceAmount
import com.fracta7.crafter.util.sortRecipesByEfficiency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftingElement(
    modifier: Modifier = Modifier,
    item: Item,
    amount: Int,
    appRepository: AppRepository
) {
    var toggle by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val recipes = sortRecipesByEfficiency(appRepository.recipeRegistryProvider().getRecipesByResult(item.id))
    var recipeIndex by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { toggle = !toggle }
        .padding(4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ItemElement(
                modifier = Modifier.fillMaxWidth(0.7f),
                item = item,
                amount = amount,
                preview = false
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (recipes.size > 1) {
                    if (showDialog) {
                        RecipeDialog(
                            recipes = recipes,
                            onDismissRequest = { showDialog = false },
                            onItemSelected = { index ->
                                recipeIndex = index
                            },
                            selected = recipeIndex,
                            appRepository = appRepository
                        )
                    }
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_swap_horiz_24),
                            contentDescription = "Recipe Selector Button"
                        )
                    }
                }
                val icon =
                    if (toggle) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                if (item.craftable) Icon(icon, contentDescription = "dropdown")
            }
        }
        AnimatedVisibility(visible = toggle && recipes.isNotEmpty()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val recipeIcon = appRepository.getRecipeType(recipes[0].recipeType).item
                    DrawItem(itemID = recipeIcon, iconSize = 48.dp)
                    Text(
                        text = appRepository.getRecipeType(recipes[0].recipeType).name,
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedCard(onClick = { }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        recipes[recipeIndex].requirements.forEach { (itemID, rAmount) ->
                            val requirementItem =
                                appRepository.itemRegistryProvider().getItem(itemID)!!
                            val requirementAmount =
                                rAmount * resourceAmount(recipes[0].resultQuantity, amount)
                            CraftingElement(
                                modifier = modifier,
                                item = requirementItem,
                                amount = requirementAmount,
                                appRepository = appRepository
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}
