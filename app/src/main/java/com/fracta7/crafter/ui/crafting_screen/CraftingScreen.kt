package com.fracta7.crafter.ui.crafting_screen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.helper.DrawItem
import com.fracta7.crafter.ui.navigation.Route
import com.fracta7.crafter.ui.theme.CrafterTheme
import com.fracta7.crafter.util.decomposeItems
import com.fracta7.crafter.util.getStackAmount
import com.fracta7.crafter.util.getStackText
import com.fracta7.crafter.util.resourceAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftingScreen(navController: NavController, itemID: String, amount: Int) {
    val viewModel = hiltViewModel<CraftingScreenViewModel>()
    val recipe = viewModel.getRecipe(itemID = itemID)
    val item = viewModel.getItemById(itemId = itemID)
    CrafterTheme(dynamicColor = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold(topBar = {
                TopAppBar(title = { Text(text = "Crafter") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                        }
                    })
            }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val recipeIcon = viewModel.getRecipeType(recipe.recipeType).item
                        DrawItem(itemID = recipeIcon, iconSize = 48.dp)
                        Text(text = viewModel.getRecipeType(recipe.recipeType).name, fontWeight = FontWeight.Bold)
                    }
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DrawItem(
                                itemID = itemID,
                                iconSize = 96.dp,
                                modifier = Modifier.padding(4.dp)
                            )
                            Column {
                                Text(
                                    text = item.name,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )
                                val stackText =
                                    getStackText(amount = amount, stackSize = item.stackSize)
                                Text(text = stackText, fontSize = 12.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "Steps", modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), fontWeight = FontWeight.Bold)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        recipe.requirements.forEach { (itemId, amountRecipeRequirement) ->
                            val itemRequirement = viewModel.getItemById(itemId)
                            item {
                                val requiredAmount = amountRecipeRequirement * resourceAmount(
                                    result = recipe.resultQuantity,
                                    amountNeeded = amount
                                )
                                ItemElement(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            if (itemRequirement.craftable) {
                                                //navController.navigate(Screens.CraftingScreen.withArgs(item.id, requiredAmount.toString()))
                                                navController.navigate(
                                                    Route.Crafting(
                                                        item = itemRequirement.id,
                                                        amount = requiredAmount
                                                    )
                                                )
                                            }
                                        }, item = itemRequirement, amount = requiredAmount, preview = false
                                )
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}