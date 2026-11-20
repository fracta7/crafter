package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeLayout
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeGridView(
    recipe: Recipe,
    resolveRecipeType: (RecipeTypeID) -> RecipeType? = { null },
    resolveIngredientRef: (IngredientRef) -> ItemID = { ref ->
        when (ref) {
            is IngredientRef.Item -> ref.id
            is IngredientRef.Tag -> ref.id.removePrefix("minecraft:")
        }
    },
    preferredFuelItemId: ItemID = "coal",
    onItemClick: (ItemID) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val recipeType = resolveRecipeType(recipe.recipeType)
    val layout = recipe.layout

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        when (layout) {
            is RecipeLayout.CraftingShaped -> {
                Crafting3x3Grid(
                    slots = Array(3) { r ->
                        Array(3) { c ->
                            val row = layout.pattern.getOrNull(r)
                            val ch = if (row != null && c < row.length) row[c] else ' '
                            if (ch == ' ') null else layout.key[ch.toString()]
                        }
                    },
                    resolveIngredientRef = resolveIngredientRef,
                    onItemClick = onItemClick
                )
            }

            is RecipeLayout.CraftingShapeless -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Crafting3x3Grid(
                        slots = Array(3) { r ->
                            Array(3) { c ->
                                layout.ingredients.getOrNull(r * 3 + c)
                            }
                        },
                        resolveIngredientRef = resolveIngredientRef,
                        onItemClick = onItemClick
                    )
                    Text(
                        text = "Shapeless",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            is RecipeLayout.Furnace -> {
                val inputId = resolveIngredientRef(layout.input)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ItemSlotBox(
                        itemId = inputId,
                        isTag = layout.input is IngredientRef.Tag,
                        onClick = { onItemClick(inputId) }
                    )
                    if (recipeType != null) {
                        DrawItem(itemID = recipeType.item, iconSize = 18.dp)
                    }
                    if (layout.requiresFuel) {
                        ItemSlotBox(
                            itemId = preferredFuelItemId,
                            onClick = { onItemClick(preferredFuelItemId) }
                        )
                    }
                }
            }

            is RecipeLayout.Brewing -> {
                val reagentId = resolveIngredientRef(layout.reagent)
                val baseId = resolveIngredientRef(layout.base)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ItemSlotBox(
                            itemId = reagentId,
                            isTag = layout.reagent is IngredientRef.Tag,
                            onClick = { onItemClick(reagentId) }
                        )
                        DrawItem(
                            itemID = "blaze_powder",
                            iconSize = 20.dp,
                            modifier = Modifier.clickable { onItemClick("blaze_powder") }
                        )
                    }
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    ItemSlotBox(
                        itemId = baseId,
                        isTag = layout.base is IngredientRef.Tag,
                        onClick = { onItemClick(baseId) }
                    )
                }
            }

            is RecipeLayout.Smithing -> {
                val templateId = resolveIngredientRef(layout.template)
                val baseId = resolveIngredientRef(layout.base)
                val addId = resolveIngredientRef(layout.addition)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ItemSlotBox(
                        itemId = templateId,
                        isTag = layout.template is IngredientRef.Tag,
                        onClick = { onItemClick(templateId) }
                    )
                    ItemSlotBox(
                        itemId = baseId,
                        isTag = layout.base is IngredientRef.Tag,
                        onClick = { onItemClick(baseId) }
                    )
                    ItemSlotBox(
                        itemId = addId,
                        isTag = layout.addition is IngredientRef.Tag,
                        onClick = { onItemClick(addId) }
                    )
                }
            }

            is RecipeLayout.SingleInput -> {
                val inputId = resolveIngredientRef(layout.input)
                val catalystRef = layout.catalyst
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ItemSlotBox(
                        itemId = inputId,
                        isTag = layout.input is IngredientRef.Tag,
                        onClick = { onItemClick(inputId) }
                    )
                    if (catalystRef != null) {
                        val catId = resolveIngredientRef(catalystRef)
                        Text(text = "+", style = MaterialTheme.typography.labelMedium)
                        ItemSlotBox(
                            itemId = catId,
                            isTag = catalystRef is IngredientRef.Tag,
                            onClick = { onItemClick(catId) }
                        )
                    } else if (recipeType != null && recipe.recipeType != "fuel") {
                        DrawItem(itemID = recipeType.item, iconSize = 22.dp)
                    }
                }
            }

            null -> {
                FlowRow(
                    modifier = Modifier.weight(1f, fill = false),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    recipe.requirements.forEach { (reqId, reqQty) ->
                        ItemSlotBox(
                            itemId = reqId,
                            count = if (reqQty > 1) reqQty else null,
                            onClick = { onItemClick(reqId) }
                        )
                    }
                }
            }
        }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )

        ItemSlotBox(
            itemId = recipe.result,
            count = if (recipe.amount > 1) recipe.amount else null,
            slotSize = 42.dp,
            highlighted = true
        )
    }
}

@Composable
private fun Crafting3x3Grid(
    slots: Array<Array<IngredientRef?>>,
    resolveIngredientRef: (IngredientRef) -> ItemID,
    onItemClick: (ItemID) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        for (row in 0 until 3) {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                for (col in 0 until 3) {
                    val ref = slots[row][col]
                    if (ref != null) {
                        val resolvedId = resolveIngredientRef(ref)
                        ItemSlotBox(
                            itemId = resolvedId,
                            isTag = ref is IngredientRef.Tag,
                            onClick = { onItemClick(resolvedId) }
                        )
                    } else {
                        ItemSlotBox(itemId = null)
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemSlotBox(
    itemId: ItemID?,
    count: Int? = null,
    isTag: Boolean = false,
    slotSize: Dp = 36.dp,
    highlighted: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val colorScheme = MaterialTheme.colorScheme
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = if (highlighted) {
            colorScheme.primaryContainer.copy(alpha = 0.55f)
        } else {
            colorScheme.surfaceContainerHigh
        },
        border = BorderStroke(
            width = if (highlighted) 1.5.dp else 1.dp,
            color = if (highlighted) colorScheme.primary.copy(alpha = 0.7f) else colorScheme.outlineVariant
        ),
        modifier = Modifier
            .size(slotSize)
            .let { mod ->
                if (onClick != null && itemId != null) mod.clickable(onClick = onClick) else mod
            }
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (itemId != null) {
                DrawItem(itemID = itemId, iconSize = slotSize - 8.dp)
                if (isTag) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(1.5.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(colorScheme.secondaryContainer)
                            .padding(horizontal = 2.dp)
                    ) {
                        Text(
                            text = "#",
                            fontSize = 8.sp,
                            lineHeight = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onSecondaryContainer
                        )
                    }
                }
                if (count != null) {
                    Text(
                        text = count.toString(),
                        fontSize = 10.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onSurface,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(
                                color = colorScheme.surface.copy(alpha = 0.85f),
                                shape = RoundedCornerShape(3.dp)
                            )
                            .padding(horizontal = 2.dp)
                    )
                }
            }
        }
    }
}
