package com.fracta7.crafter.ui.crafting_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.usecases.CraftingNode
import com.fracta7.crafter.domain.usecases.MaterialRowUi
import com.fracta7.crafter.domain.usecases.StepRowUi
import com.fracta7.crafter.domain.usecases.buildCraftingGraphLayout
import com.fracta7.crafter.ui.elements.CraftingElement

enum class TreeViewMode(val label: String) {
    DIAGRAM("Diagram"),
    LIST("List")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeTab(
    targets: Map<ItemID, Int>,
    materialRows: List<MaterialRowUi>,
    stepRows: List<StepRowUi>,
    ownedInventory: Map<ItemID, Int>,
    treeNodes: List<CraftingNode>,
    viewMode: TreeViewMode,
    onViewModeChange: (TreeViewMode) -> Unit,
    resolveItem: (ItemID) -> Item?,
    resolveRecipeType: (RecipeTypeID) -> RecipeType,
    getRecipesForResult: (ItemID) -> List<Recipe>,
    onSelectRecipe: (ItemID, Int) -> Unit,
    onSetOwnedAmount: (ItemID, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val graphLayout = remember(targets, materialRows, stepRows, ownedInventory) {
        buildCraftingGraphLayout(
            targets = targets,
            materialRows = materialRows,
            stepRows = stepRows,
            ownedInventory = ownedInventory
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            TreeViewMode.entries.forEachIndexed { index, option ->
                SegmentedButton(
                    selected = viewMode == option,
                    onClick = { onViewModeChange(option) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = TreeViewMode.entries.size
                    ),
                    label = { Text(option.label) }
                )
            }
        }

        if (viewMode == TreeViewMode.DIAGRAM) {
            CraftingDiagram(
                layout = graphLayout,
                resolveItem = resolveItem,
                resolveRecipeType = resolveRecipeType,
                getRecipesForResult = getRecipesForResult,
                onSelectRecipe = onSelectRecipe,
                onSetOwnedAmount = onSetOwnedAmount,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
            ) {
                items(items = treeNodes, key = { it.itemId }) { node ->
                    CraftingElement(
                        node = node,
                        resolveItem = resolveItem,
                        resolveRecipeType = resolveRecipeType,
                        onSelectRecipe = onSelectRecipe
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp))
                }
            }
        }
    }
}
