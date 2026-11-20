package com.fracta7.crafter.ui.crafting_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.usecases.CraftingGraphEdge
import com.fracta7.crafter.domain.usecases.CraftingGraphLayout
import com.fracta7.crafter.domain.usecases.CraftingGraphNode
import com.fracta7.crafter.domain.usecases.GraphNodeKind
import com.fracta7.crafter.domain.usecases.GraphPoint
import com.fracta7.crafter.domain.usecases.findConnectedSubgraph
import com.fracta7.crafter.ui.elements.DrawItem
import com.fracta7.crafter.ui.elements.RecipeDialog
import kotlin.math.roundToInt

private const val MIN_DIAGRAM_SCALE = 0.25f
private const val MAX_DIAGRAM_SCALE = 2.2f

private data class DiagramSemanticColors(
    val rawContainer: Color,
    val rawBorder: Color,
    val intermediateContainer: Color,
    val intermediateBorder: Color,
    val targetContainer: Color,
    val targetBorder: Color,
    val targetAccent: Color,
    val targetBadgeBg: Color,
    val targetBadgeText: Color,
    val leftoverContainer: Color,
    val leftoverBorder: Color,
    val leftoverAccent: Color,
    val leftoverBadgeBg: Color,
    val leftoverBadgeText: Color,
    val selectedBorder: Color
)

private data class EdgeBadgeDrawSpec(
    val rect: Rect,
    val padH: Float,
    val padV: Float,
    val textLayout: TextLayoutResult,
    val bgColor: Color,
    val borderColor: Color,
    val isActive: Boolean
)

@Composable
fun CraftingDiagram(
    layout: CraftingGraphLayout,
    resolveItem: (ItemID) -> Item?,
    resolveRecipeType: (RecipeTypeID) -> RecipeType,
    getRecipesForResult: (ItemID) -> List<Recipe>,
    onSelectRecipe: (ItemID, Int) -> Unit,
    onSetOwnedAmount: (ItemID, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (layout.nodes.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No items to display in diagram.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    var selectedNodeKey by remember { mutableStateOf<String?>(null) }
    var recipeSwapItemId by remember { mutableStateOf<ItemID?>(null) }
    var ownedEditItemId by remember { mutableStateOf<ItemID?>(null) }

    val nodeByKey = remember(layout.nodes) { layout.nodes.associateBy { it.nodeKey } }
    val selectedNode = selectedNodeKey?.let { nodeByKey[it] }
    val highlighted = remember(selectedNode?.nodeKey, layout.edges) {
        selectedNode?.let { findConnectedSubgraph(it.nodeKey, layout.edges) }
    }

    LaunchedEffect(nodeByKey) {
        if (selectedNodeKey != null && selectedNodeKey !in nodeByKey) {
            selectedNodeKey = null
        }
    }

    val outgoingSlots = remember(layout.edges, nodeByKey) {
        computeEdgePortOffsets(
            edges = layout.edges,
            groupKey = { it.fromNodeKey },
            targetYOf = { edge -> edge.waypoints.firstOrNull()?.yDp ?: nodeByKey[edge.toNodeKey]?.yDp ?: 0f },
            tieBreakOf = { it.toNodeKey }
        )
    }
    val incomingSlots = remember(layout.edges, nodeByKey) {
        computeEdgePortOffsets(
            edges = layout.edges,
            groupKey = { it.toNodeKey },
            targetYOf = { edge -> edge.waypoints.lastOrNull()?.yDp ?: nodeByKey[edge.fromNodeKey]?.yDp ?: 0f },
            tieBreakOf = { it.fromNodeKey }
        )
    }

    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val textMeasurer = rememberTextMeasurer()

    val isDark = colorScheme.surface.luminance() < 0.5f
    val semanticColors = remember(colorScheme, isDark) {
        if (isDark) {
            DiagramSemanticColors(
                rawContainer = colorScheme.surfaceContainerLowest,
                rawBorder = colorScheme.outlineVariant,
                intermediateContainer = colorScheme.surfaceContainerHigh,
                intermediateBorder = colorScheme.outline,
                targetContainer = Color(0xFF103E26),
                targetBorder = Color(0xFF4ADE80),
                targetAccent = Color(0xFF86EFAC),
                targetBadgeBg = Color(0xFF22C55E),
                targetBadgeText = Color(0xFF052E16),
                leftoverContainer = Color(0xFF3B2506),
                leftoverBorder = Color(0xFFF59E0B),
                leftoverAccent = Color(0xFFFCD34D),
                leftoverBadgeBg = Color(0xFFF59E0B),
                leftoverBadgeText = Color(0xFF271500),
                selectedBorder = Color(0xFF38BDF8)
            )
        } else {
            DiagramSemanticColors(
                rawContainer = colorScheme.surfaceContainerLowest,
                rawBorder = colorScheme.outlineVariant,
                intermediateContainer = colorScheme.surfaceContainerHigh,
                intermediateBorder = colorScheme.outline,
                targetContainer = Color(0xFFCFF4DB),
                targetBorder = Color(0xFF15803D),
                targetAccent = Color(0xFF14532D),
                targetBadgeBg = Color(0xFF15803D),
                targetBadgeText = Color(0xFFFFFFFF),
                leftoverContainer = Color(0xFFFEF3C7),
                leftoverBorder = Color(0xFFD97706),
                leftoverAccent = Color(0xFF92400E),
                leftoverBadgeBg = Color(0xFFD97706),
                leftoverBadgeText = Color(0xFFFFFFFF),
                selectedBorder = Color(0xFF0284C7)
            )
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
    ) {
        val density = LocalDensity.current
        val viewportW = constraints.maxWidth.toFloat().coerceAtLeast(1f)
        val viewportH = constraints.maxHeight.toFloat().coerceAtLeast(1f)
        val graphWidthPx = with(density) { layout.widthDp.dp.toPx() }
        val graphHeightPx = with(density) { layout.heightDp.dp.toPx() }
        val overscrollPx = with(density) { 64.dp.toPx() }
        val topOverlayReservePx = with(density) { 58.dp.toPx() }
        val bottomInspectorReservePx = with(density) { 88.dp.toPx() }
        val hasInspector = selectedNode != null

        var scale by remember { mutableFloatStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }

        fun clampOffset(candidate: Offset, targetScale: Float): Offset {
            val scaledW = graphWidthPx * targetScale
            val scaledH = graphHeightPx * targetScale
            val extraBottom = if (hasInspector) bottomInspectorReservePx else 0f

            val minX = if (scaledW > viewportW) {
                viewportW - scaledW - overscrollPx
            } else {
                (viewportW - scaledW) / 2f - overscrollPx
            }
            val maxX = if (scaledW > viewportW) {
                overscrollPx
            } else {
                (viewportW - scaledW) / 2f + overscrollPx
            }

            val minY = if (scaledH > viewportH - extraBottom) {
                viewportH - scaledH - overscrollPx - extraBottom
            } else {
                (viewportH - scaledH) / 2f - overscrollPx - extraBottom / 2f
            }
            val maxY = if (scaledH > viewportH) {
                overscrollPx + topOverlayReservePx / 2f
            } else {
                (viewportH - scaledH) / 2f + overscrollPx
            }

            return Offset(
                x = candidate.x.coerceIn(minOf(minX, maxX), maxOf(minX, maxX)),
                y = candidate.y.coerceIn(minOf(minY, maxY), maxOf(minY, maxY))
            )
        }

        fun fitToViewport() {
            val marginPx = with(density) { 12.dp.toPx() }
            val usableW = (viewportW - marginPx * 2f).coerceAtLeast(1f)
            val usableH = (viewportH - topOverlayReservePx - marginPx * 2f).coerceAtLeast(1f)
            val fitScale = minOf(
                usableW / graphWidthPx.coerceAtLeast(1f),
                usableH / graphHeightPx.coerceAtLeast(1f),
                1f
            ).coerceIn(MIN_DIAGRAM_SCALE, 1f)
            val scaledW = graphWidthPx * fitScale
            val scaledH = graphHeightPx * fitScale
            val fitOffset = Offset(
                x = if (scaledW <= viewportW) (viewportW - scaledW) / 2f else marginPx,
                y = topOverlayReservePx + maxOf(marginPx, (usableH - scaledH) / 2f)
            )
            scale = fitScale
            offset = fitOffset
        }

        fun zoomAround(factor: Float, pivot: Offset = Offset(viewportW / 2f, viewportH / 2f)) {
            val oldScale = scale
            val newScale = (oldScale * factor).coerceIn(MIN_DIAGRAM_SCALE, MAX_DIAGRAM_SCALE)
            if (newScale == oldScale) return
            val graphPoint = (pivot - offset) / oldScale
            val newOffset = pivot - graphPoint * newScale
            scale = newScale
            offset = clampOffset(newOffset, newScale)
        }

        LaunchedEffect(layout.nodes.size, layout.sections.size, layout.widthDp, layout.heightDp, viewportW, viewportH) {
            fitToViewport()
        }

        val gridDotColor = colorScheme.outlineVariant.copy(alpha = 0.25f)
        val sectionBgColor = colorScheme.surfaceContainerLow.copy(alpha = 0.35f)
        val sectionBorderColor = colorScheme.outlineVariant.copy(alpha = 0.55f)
        val nodeWidthPxDefault = with(density) { (layout.nodes.firstOrNull()?.widthDp ?: 164f).dp.toPx() }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val spacingPx = 28.dp.toPx() * scale.coerceAtLeast(0.5f)
                    val dotRadius = 1.2.dp.toPx()
                    val phaseX = ((offset.x % spacingPx) + spacingPx) % spacingPx
                    val phaseY = ((offset.y % spacingPx) + spacingPx) % spacingPx
                    var gx = phaseX
                    while (gx < size.width) {
                        var gy = phaseY
                        while (gy < size.height) {
                            drawCircle(
                                color = gridDotColor,
                                radius = dotRadius,
                                center = Offset(gx, gy)
                            )
                            gy += spacingPx
                        }
                        gx += spacingPx
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { selectedNodeKey = null })
                }
                .pointerInput(graphWidthPx, graphHeightPx, viewportW, viewportH, hasInspector) {
                    detectTransformGestures { centroid, pan, zoom, _ ->
                        val oldScale = scale
                        val newScale = (oldScale * zoom).coerceIn(MIN_DIAGRAM_SCALE, MAX_DIAGRAM_SCALE)
                        val graphPoint = (centroid - offset) / oldScale
                        val rawOffset = centroid - graphPoint * newScale + pan
                        scale = newScale
                        offset = clampOffset(rawOffset, newScale)
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart, unbounded = true)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0f, 0f)
                        translationX = offset.x
                        translationY = offset.y
                        scaleX = scale
                        scaleY = scale
                    }
                    .size(width = layout.widthDp.dp, height = layout.heightDp.dp)
                    .drawBehind {
                        // Draw per-target swimlane containers first when there are multiple items.
                        if (layout.sections.size > 1) {
                            val sectionRadius = CornerRadius(18.dp.toPx(), 18.dp.toPx())
                            layout.sections.forEach { section ->
                                val topLeft = Offset(section.xDp.dp.toPx(), section.yDp.dp.toPx())
                                val rectSize = Size(section.widthDp.dp.toPx(), section.heightDp.dp.toPx())
                                drawRoundRect(
                                    color = sectionBgColor,
                                    topLeft = topLeft,
                                    size = rectSize,
                                    cornerRadius = sectionRadius
                                )
                                drawRoundRect(
                                    color = sectionBorderColor,
                                    topLeft = topLeft,
                                    size = rectSize,
                                    cornerRadius = sectionRadius,
                                    style = Stroke(width = 1.2.dp.toPx())
                                )
                            }
                        }

                        val arrowLen = 8.dp.toPx()
                        val arrowHalfWidth = 4.5.dp.toPx()
                        val labelStyle = typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        val padH = 6.dp.toPx()
                        val padV = 2.5.dp.toPx()
                        val badgeCollisionPad = 3.dp.toPx()
                        val pillRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())

                        // Order edges so dimmed edges draw first and highlighted edges sit on top.
                        val orderedEdges = if (highlighted == null) {
                            layout.edges
                        } else {
                            val dimmed = mutableListOf<CraftingGraphEdge>()
                            val active = mutableListOf<CraftingGraphEdge>()
                            for (edge in layout.edges) {
                                if (edge in highlighted.ancestorDescendantEdges) {
                                    active += edge
                                } else {
                                    dimmed += edge
                                }
                            }
                            dimmed + active
                        }

                        val placedBadgeRects = mutableListOf<Rect>()
                        val badgeSpecs = mutableListOf<EdgeBadgeDrawSpec>()

                        // Pass 1: Draw all routed curves and arrowheads, collecting badge locations.
                        for (edge in orderedEdges) {
                            val fromNode = nodeByKey[edge.fromNodeKey] ?: continue
                            val toNode = nodeByKey[edge.toNodeKey] ?: continue

                            val fromPortFraction = outgoingSlots[edge] ?: 0f
                            val toPortFraction = incomingSlots[edge] ?: 0f

                            val startX = (fromNode.xDp + fromNode.widthDp).dp.toPx()
                            val startY = fromNode.yDp.dp.toPx() +
                                fromNode.heightDp.dp.toPx() * (0.5f + fromPortFraction)
                            val endX = toNode.xDp.dp.toPx()
                            val endY = toNode.yDp.dp.toPx() +
                                toNode.heightDp.dp.toPx() * (0.5f + toPortFraction)

                            val waypointsPx = edge.waypoints.map { wp ->
                                Offset(wp.xDp.dp.toPx(), wp.yDp.dp.toPx())
                            }

                            val (edgeColor, strokeWidthDp, isDimmed) = when {
                                highlighted == null && edge.isLeftover -> Triple(
                                    semanticColors.leftoverBorder.copy(alpha = 0.90f),
                                    2.2f,
                                    false
                                )
                                highlighted == null -> Triple(
                                    colorScheme.onSurfaceVariant.copy(alpha = 0.58f),
                                    2f,
                                    false
                                )
                                edge in highlighted.incomingEdges -> Triple(
                                    if (edge.isLeftover) semanticColors.leftoverBorder else semanticColors.selectedBorder,
                                    3f,
                                    false
                                )
                                edge in highlighted.outgoingEdges -> Triple(
                                    if (edge.isLeftover) semanticColors.leftoverBorder else semanticColors.targetBorder,
                                    3f,
                                    false
                                )
                                edge in highlighted.ancestorDescendantEdges -> Triple(
                                    if (edge.isLeftover) semanticColors.leftoverBorder else colorScheme.secondary.copy(alpha = 0.9f),
                                    2.3f,
                                    false
                                )
                                else -> Triple(
                                    colorScheme.outlineVariant.copy(alpha = 0.24f),
                                    1.4f,
                                    true
                                )
                            }

                            val desiredBadgeCenter = drawRoutedEdgePath(
                                start = Offset(startX, startY),
                                end = Offset(endX, endY),
                                waypoints = waypointsPx,
                                nodeWidthPx = nodeWidthPxDefault,
                                color = edgeColor,
                                strokeWidthPx = strokeWidthDp.dp.toPx(),
                                arrowLenPx = arrowLen,
                                arrowHalfWidthPx = arrowHalfWidth
                            )

                            val badgeLabel = if (edge.isLeftover) "+${edge.amount}" else "×${edge.amount}"
                            val textLayout = textMeasurer.measure(
                                text = badgeLabel,
                                style = labelStyle.copy(
                                    color = when {
                                        isDimmed -> colorScheme.onSurfaceVariant.copy(alpha = 0.35f)
                                        edge.isLeftover -> semanticColors.leftoverAccent
                                        else -> colorScheme.onSurface
                                    }
                                )
                            )
                            val pillW = textLayout.size.width + padH * 2f
                            val pillH = textLayout.size.height + padV * 2f
                            val resolvedRect = placeBadgeRectAvoidingOverlaps(
                                desiredCenter = desiredBadgeCenter,
                                width = pillW,
                                height = pillH,
                                padding = badgeCollisionPad,
                                placed = placedBadgeRects
                            )

                            badgeSpecs += EdgeBadgeDrawSpec(
                                rect = resolvedRect,
                                padH = padH,
                                padV = padV,
                                textLayout = textLayout,
                                bgColor = when {
                                    isDimmed -> colorScheme.surface.copy(alpha = 0.65f)
                                    edge.isLeftover -> semanticColors.leftoverContainer
                                    else -> colorScheme.surfaceContainerHigh
                                },
                                borderColor = edgeColor.copy(alpha = if (isDimmed) 0.25f else 0.85f),
                                isActive = !isDimmed
                            )
                        }

                        // Pass 2: Draw quantity pills above all curve strokes so lines never bisect text.
                        badgeSpecs
                            .sortedBy { it.isActive }
                            .forEach { badge ->
                                val topLeft = Offset(badge.rect.left, badge.rect.top)
                                val pillSize = Size(badge.rect.width, badge.rect.height)
                                drawRoundRect(
                                    color = badge.bgColor,
                                    topLeft = topLeft,
                                    size = pillSize,
                                    cornerRadius = pillRadius
                                )
                                drawRoundRect(
                                    color = badge.borderColor,
                                    topLeft = topLeft,
                                    size = pillSize,
                                    cornerRadius = pillRadius,
                                    style = Stroke(width = 1.dp.toPx())
                                )
                                drawText(
                                    textLayoutResult = badge.textLayout,
                                    topLeft = Offset(
                                        x = (badge.rect.left + badge.padH).roundToInt().toFloat(),
                                        y = (badge.rect.top + badge.padV).roundToInt().toFloat()
                                    )
                                )
                            }
                    }
            ) {
                // Render aligned section titles and per-column headers for every swimlane.
                layout.sections.forEach { section ->
                    val sectionTopY = section.yDp + 8f
                    val titleLeftX = section.xDp + 16f
                    val rootItem = resolveItem(section.rootTargetId)

                    if (layout.sections.size > 1) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.offset(x = titleLeftX.dp, y = sectionTopY.dp)
                        ) {
                            DrawItem(itemID = section.rootTargetId, iconSize = 18.dp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${rootItem?.name ?: section.rootTargetId} ×${section.targetAmount}",
                                style = typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = semanticColors.targetAccent
                            )
                        }
                    }

                    val headerY = if (layout.sections.size > 1) section.yDp + 30f else section.yDp + 10f
                    section.columnXsDp.forEachIndexed { colIdx, colX ->
                        val colLabel = when {
                            colIdx == section.goalColumnIndex -> "Goal"
                            colIdx > section.goalColumnIndex -> "Leftover"
                            colIdx == 0 && section.hasRawColumn -> "Raw"
                            else -> "Step $colIdx"
                        }
                        Text(
                            text = colLabel,
                            style = typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                colIdx == section.goalColumnIndex -> semanticColors.targetAccent
                                colIdx > section.goalColumnIndex -> semanticColors.leftoverAccent
                                else -> colorScheme.onSurfaceVariant
                            },
                            modifier = Modifier.offset(x = colX.dp, y = headerY.dp)
                        )
                    }
                }

                layout.nodes.forEach { node ->
                    val item = resolveItem(node.itemId)
                    val recipeType = node.recipeTypeId?.let(resolveRecipeType)
                    val isSelected = selectedNode?.nodeKey == node.nodeKey
                    val isDimmed = highlighted != null && node.nodeKey !in highlighted.connectedNodeKeys
                    DiagramNodeCard(
                        node = node,
                        item = item,
                        recipeType = recipeType,
                        semanticColors = semanticColors,
                        isSelected = isSelected,
                        isDimmed = isDimmed,
                        onClick = {
                            selectedNodeKey = if (isSelected) null else node.nodeKey
                        },
                        modifier = Modifier
                            .offset(x = node.xDp.dp, y = node.yDp.dp)
                            .size(width = node.widthDp.dp, height = node.heightDp.dp)
                    )
                }
            }

            // Top-left compact color legend pill so Goal / Craft / Raw distinction is immediate.
            Surface(
                shape = ShapeDefaults.Large,
                tonalElevation = 4.dp,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    LegendDotItem(
                        color = semanticColors.rawBorder,
                        label = "Raw"
                    )
                    LegendDotItem(
                        color = semanticColors.intermediateBorder,
                        label = "Craft"
                    )
                    LegendDotItem(
                        color = semanticColors.targetBorder,
                        label = "Goal",
                        labelColor = semanticColors.targetAccent
                    )
                    LegendDotItem(
                        color = semanticColors.leftoverBorder,
                        label = "Leftover",
                        labelColor = semanticColors.leftoverAccent
                    )
                }
            }

            // Top-right floating zoom controls.
            Surface(
                shape = ShapeDefaults.Large,
                tonalElevation = 4.dp,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    IconButton(
                        onClick = { zoomAround(1f / 1.25f) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_remove_24),
                            contentDescription = "Zoom out"
                        )
                    }
                    TextButton(
                        onClick = { fitToViewport() },
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(
                            text = "${(scale * 100).roundToInt()}%",
                            style = typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(
                        onClick = { zoomAround(1.25f) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Zoom in"
                        )
                    }
                }
            }

            // Bottom inspector bar when a node is tapped.
            AnimatedVisibility(
                visible = selectedNode != null,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            ) {
                if (selectedNode != null) {
                    val selectedItem = resolveItem(selectedNode.itemId)
                    val selectedStation = selectedNode.recipeTypeId?.let(resolveRecipeType)
                    Surface(
                        shape = ShapeDefaults.Large,
                        tonalElevation = 6.dp,
                        shadowElevation = 4.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            DrawItem(itemID = selectedNode.itemId, iconSize = 36.dp)
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = selectedItem?.name ?: selectedNode.itemId,
                                    style = typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                val detailParts = buildList {
                                    add("Need ×${selectedNode.totalRequired}")
                                    if (selectedNode.owned > 0) add("Have ${selectedNode.owned}")
                                    if (selectedNode.craftCount > 0) add("${selectedNode.craftCount}× craft")
                                    if (selectedNode.leftover > 0) add("${selectedNode.leftover} leftover")
                                    if (selectedStation != null) add(selectedStation.name)
                                }
                                Text(
                                    text = detailParts.joinToString(" • "),
                                    style = typography.bodySmall,
                                    color = colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            TextButton(onClick = { ownedEditItemId = selectedNode.itemId }) {
                                Text("Have")
                            }
                            if (selectedNode.availableRecipeCount > 1) {
                                IconButton(onClick = { recipeSwapItemId = selectedNode.itemId }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_swap_horiz_24),
                                        contentDescription = "Switch recipe"
                                    )
                                }
                            }
                            IconButton(onClick = { selectedNodeKey = null }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear selection"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    val swapNode = recipeSwapItemId?.let { id -> layout.nodes.firstOrNull { it.itemId == id } }
    if (swapNode != null) {
        val recipes = getRecipesForResult(swapNode.itemId)
        if (recipes.size > 1) {
            RecipeDialog(
                recipes = recipes,
                onDismissRequest = { recipeSwapItemId = null },
                onItemSelected = { index ->
                    onSelectRecipe(swapNode.itemId, index)
                },
                selected = swapNode.selectedRecipeIndex,
                resolveItem = resolveItem,
                resolveRecipeType = resolveRecipeType
            )
        }
    }

    val ownedNode = ownedEditItemId?.let { id -> layout.nodes.firstOrNull { it.itemId == id } }
    val ownedItem = ownedNode?.let { resolveItem(it.itemId) }
    if (ownedNode != null && ownedItem != null) {
        OwnedQuantityDialog(
            item = ownedItem,
            requiredAmount = ownedNode.totalRequired,
            currentOwned = ownedNode.owned,
            onDismissRequest = { ownedEditItemId = null },
            onConfirm = { newOwned ->
                onSetOwnedAmount(ownedNode.itemId, newOwned)
            }
        )
    }
}

@Composable
private fun LegendDotItem(
    color: Color,
    label: String,
    labelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = labelColor
        )
    }
}

@Composable
private fun DiagramNodeCard(
    node: CraftingGraphNode,
    item: Item?,
    recipeType: RecipeType?,
    semanticColors: DiagramSemanticColors,
    isSelected: Boolean,
    isDimmed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val isTarget = node.kind == GraphNodeKind.TARGET
    val isLeftover = node.kind == GraphNodeKind.LEFTOVER
    val borderStroke = when {
        isSelected -> BorderStroke(2.8.dp, semanticColors.selectedBorder)
        isTarget -> BorderStroke(2.4.dp, semanticColors.targetBorder)
        isLeftover -> BorderStroke(2.2.dp, semanticColors.leftoverBorder)
        node.kind == GraphNodeKind.INTERMEDIATE -> BorderStroke(1.2.dp, semanticColors.intermediateBorder)
        else -> BorderStroke(1.dp, semanticColors.rawBorder)
    }
    val containerColor = when {
        isTarget -> semanticColors.targetContainer
        isLeftover -> semanticColors.leftoverContainer
        isSelected -> colorScheme.secondaryContainer
        node.kind == GraphNodeKind.INTERMEDIATE -> semanticColors.intermediateContainer
        else -> semanticColors.rawContainer
    }
    val contentAlpha = when {
        isDimmed -> 0.30f
        node.isCompleted && !isSelected -> 0.68f
        else -> 1f
    }

    OutlinedCard(
        shape = RoundedCornerShape(12.dp),
        border = borderStroke,
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor
        ),
        modifier = modifier
            .alpha(contentAlpha)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 7.dp)
        ) {
            Box(modifier = Modifier.size(40.dp)) {
                DrawItem(
                    itemID = node.itemId,
                    iconSize = 36.dp,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (recipeType != null && !isLeftover) {
                    Surface(
                        shape = CircleShape,
                        color = colorScheme.surfaceContainerHighest,
                        border = BorderStroke(
                            1.dp,
                            if (isTarget) semanticColors.targetBorder else colorScheme.outlineVariant
                        ),
                        modifier = Modifier
                            .size(19.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            DrawItem(itemID = recipeType.item, iconSize = 13.dp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item?.name ?: node.itemId,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    if (node.isCompleted) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "Completed",
                            tint = if (isTarget) semanticColors.targetAccent else colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .size(14.dp)
                        )
                    }
                }

                val qtyText = when {
                    isLeftover -> "+${node.leftover}"
                    node.owned in 1 until node.totalRequired -> "${node.owned}/${node.totalRequired}"
                    else -> "×${node.totalRequired}"
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = qtyText,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            isTarget -> semanticColors.targetAccent
                            isLeftover -> semanticColors.leftoverAccent
                            else -> colorScheme.primary
                        },
                        maxLines = 1
                    )
                    if (isTarget) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(semanticColors.targetBadgeBg)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "GOAL",
                                fontSize = 9.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = semanticColors.targetBadgeText,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    } else if (isLeftover) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(semanticColors.leftoverBadgeBg)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "LEFTOVER",
                                fontSize = 9.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = semanticColors.leftoverBadgeText,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isLeftover) {
                        if (recipeType != null) {
                            DrawItem(itemID = recipeType.item, iconSize = 15.dp)
                            Text(
                                text = recipeType.name,
                                style = MaterialTheme.typography.labelSmall,
                                color = semanticColors.leftoverAccent.copy(alpha = 0.85f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        } else {
                            Text(
                                text = "Leftover",
                                style = MaterialTheme.typography.labelSmall,
                                color = semanticColors.leftoverAccent.copy(alpha = 0.85f),
                                maxLines = 1
                            )
                        }
                    } else if (recipeType != null) {
                        DrawItem(itemID = recipeType.item, iconSize = 15.dp)
                        val craftText = if (node.craftCount > 0) "${recipeType.name} • ${node.craftCount}×" else "${recipeType.name} • Done"
                        Text(
                            text = craftText,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isTarget) semanticColors.targetAccent else colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        if (node.availableRecipeCount > 1) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_swap_horiz_24),
                                contentDescription = null,
                                tint = colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    } else if (!isTarget) {
                        Text(
                            text = "Raw",
                            style = MaterialTheme.typography.labelSmall,
                            color = colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

/**
 * Computes subtle vertical port offsets (-0.22..+0.22 of node height) so multiple edges
 * entering or leaving the same card stay separated instead of stacking on one pixel.
 */
private fun computeEdgePortOffsets(
    edges: List<CraftingGraphEdge>,
    groupKey: (CraftingGraphEdge) -> String,
    targetYOf: (CraftingGraphEdge) -> Float,
    tieBreakOf: (CraftingGraphEdge) -> String
): Map<CraftingGraphEdge, Float> {
    val result = mutableMapOf<CraftingGraphEdge, Float>()
    edges.groupBy(groupKey).forEach { (_, group) ->
        if (group.size <= 1) {
            group.forEach { result[it] = 0f }
        } else {
            val sorted = group.sortedWith(
                compareBy<CraftingGraphEdge> { targetYOf(it) }.thenBy(tieBreakOf)
            )
            val span = (0.44f).coerceAtMost(0.11f * sorted.size)
            val step = span / (sorted.size - 1)
            sorted.forEachIndexed { index, edge ->
                result[edge] = -span / 2f + index * step
            }
        }
    }
    return result
}

private fun DrawScope.drawRoutedEdgePath(
    start: Offset,
    end: Offset,
    waypoints: List<Offset>,
    nodeWidthPx: Float,
    color: Color,
    strokeWidthPx: Float,
    arrowLenPx: Float,
    arrowHalfWidthPx: Float
): Offset {
    val curveEndX = (end.x - arrowLenPx + 1.dp.toPx()).coerceAtLeast(start.x)
    val curveEnd = Offset(curveEndX, end.y)

    val curvePath = Path().apply { moveTo(start.x, start.y) }
    val desiredBadgeCenter: Offset

    if (waypoints.isEmpty()) {
        val dx = ((curveEnd.x - start.x) * 0.45f).coerceAtLeast(24.dp.toPx())
        val c1 = Offset(start.x + dx, start.y)
        val c2 = Offset(curveEnd.x - dx, curveEnd.y)
        curvePath.cubicTo(c1.x, c1.y, c2.x, c2.y, curveEnd.x, curveEnd.y)
        desiredBadgeCenter = evalCubicBezier(t = 0.50f, p0 = start, p1 = c1, p2 = c2, p3 = curveEnd)
    } else {
        var prevX = start.x
        var prevY = start.y
        for (wp in waypoints) {
            val dx = ((wp.x - prevX) * 0.45f).coerceAtLeast(18.dp.toPx())
            curvePath.cubicTo(prevX + dx, prevY, wp.x - dx, wp.y, wp.x, wp.y)
            val laneRightX = wp.x + nodeWidthPx
            curvePath.lineTo(laneRightX, wp.y)
            prevX = laneRightX
            prevY = wp.y
        }
        val finalDx = ((curveEnd.x - prevX) * 0.45f).coerceAtLeast(18.dp.toPx())
        curvePath.cubicTo(prevX + finalDx, prevY, curveEnd.x - finalDx, curveEnd.y, curveEnd.x, curveEnd.y)

        val anchorWp = waypoints.first()
        desiredBadgeCenter = Offset(anchorWp.x + nodeWidthPx / 2f, anchorWp.y)
    }

    drawPath(
        path = curvePath,
        color = color,
        style = Stroke(width = strokeWidthPx)
    )

    val arrowPath = Path().apply {
        moveTo(end.x, end.y)
        lineTo(end.x - arrowLenPx, end.y - arrowHalfWidthPx)
        lineTo(end.x - arrowLenPx, end.y + arrowHalfWidthPx)
        close()
    }
    drawPath(path = arrowPath, color = color)

    return desiredBadgeCenter
}

private fun placeBadgeRectAvoidingOverlaps(
    desiredCenter: Offset,
    width: Float,
    height: Float,
    padding: Float,
    placed: MutableList<Rect>
): Rect {
    val step = height + padding * 2f
    for (attempt in 0..16) {
        val deltaY = if (attempt == 0) {
            0f
        } else {
            val magnitude = ((attempt + 1) / 2) * step
            if (attempt % 2 == 1) -magnitude else magnitude
        }
        val left = desiredCenter.x - width / 2f
        val top = desiredCenter.y + deltaY - height / 2f
        val candidate = Rect(left, top, left + width, top + height)
        val padded = Rect(
            left - padding,
            top - padding,
            left + width + padding,
            top + height + padding
        )
        if (placed.none { it.overlaps(padded) }) {
            placed += candidate
            return candidate
        }
    }
    val fallback = Rect(
        desiredCenter.x - width / 2f,
        desiredCenter.y - height / 2f,
        desiredCenter.x + width / 2f,
        desiredCenter.y + height / 2f
    )
    placed += fallback
    return fallback
}

private fun evalCubicBezier(
    t: Float,
    p0: Offset,
    p1: Offset,
    p2: Offset,
    p3: Offset
): Offset {
    val u = 1f - t
    val tt = t * t
    val uu = u * u
    val uuu = uu * u
    val ttt = tt * t
    return Offset(
        x = uuu * p0.x + 3f * uu * t * p1.x + 3f * u * tt * p2.x + ttt * p3.x,
        y = uuu * p0.y + 3f * uu * t * p1.y + 3f * u * tt * p2.y + ttt * p3.y
    )
}
