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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftingElement(
    modifier: Modifier = Modifier,
    item: Item,
    amount: Int,
    appRepository: AppRepository
) {
    var toggle by remember { mutableStateOf(false) }
    val recipes = appRepository.recipeRegistryProvider().getRecipesByResult(item.id)
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
            if (recipes.size > 1) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.tree_icon_24),
                        contentDescription = "Recipe Selector Button"
                    )
                }
            }
            val icon =
                if (toggle) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
            if (item.craftable) Icon(icon, contentDescription = "dropdown")
        }
        AnimatedVisibility(visible = toggle && item.craftable) {
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
                        recipes[0].requirements.forEach { (itemID, rAmount) ->
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
