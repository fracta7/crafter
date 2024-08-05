package com.fracta7.crafter.ui.crafting_screen.tree

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fracta7.crafter.domain.model.RecipeTreeNode
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.helper.DrawItem

@Composable
fun View(nodes: List<RecipeTreeNode>, repository: AppRepository) {
    val expandedItems = remember { mutableStateListOf<RecipeTreeNode>() }
    LazyColumn {
        nodes(
            nodes,
            isExpanded = {
                expandedItems.contains(it)
            },
            toggleExpanded = {
                if (expandedItems.contains(it)) {
                    expandedItems.remove(it)
                } else {
                    expandedItems.add(it)
                }
            },
            repository = repository
        )
    }
}

fun LazyListScope.nodes(
    nodes: List<RecipeTreeNode>,
    repository: AppRepository,
    isExpanded: (RecipeTreeNode) -> Boolean,
    toggleExpanded: (RecipeTreeNode) -> Unit,
) {
    nodes.forEach { node ->
        node(
            node,
            isExpanded = isExpanded,
            toggleExpanded = toggleExpanded,
            repository = repository
        )
    }
}

fun LazyListScope.node(
    node: RecipeTreeNode,
    repository: AppRepository,
    isExpanded: (RecipeTreeNode) -> Boolean,
    toggleExpanded: (RecipeTreeNode) -> Unit,
) {
    item {
        if (node.recipeType != null) {
            val recipeType = repository.getRecipeType(node.recipeType)
            DrawItem(itemID = recipeType.item, iconSize = 48.dp)
            Text(text = recipeType.name)
        }
        val item = repository.itemRegistryProvider().getItem(node.itemID)!!
        ItemElement(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                toggleExpanded(node)
            }, item = item, amount = node.quantity)
        Divider()
    }
    if (isExpanded(node)) {
        nodes(
            node.children,
            isExpanded = isExpanded,
            toggleExpanded = toggleExpanded,
            repository = repository
        )
    }
}