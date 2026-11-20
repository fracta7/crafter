package com.fracta7.crafter.domain.usecases

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.RecipeTypeID

enum class GraphNodeKind {
    RAW,
    INTERMEDIATE,
    TARGET,
    LEFTOVER
}

@Immutable
data class GraphPoint(
    val xDp: Float,
    val yDp: Float
)

@Immutable
data class CraftingGraphSection(
    val rootTargetId: ItemID,
    val targetAmount: Int,
    val xDp: Float,
    val yDp: Float,
    val widthDp: Float,
    val heightDp: Float,
    val columnXsDp: List<Float>,
    val hasRawColumn: Boolean,
    val goalColumnIndex: Int = (columnXsDp.size - 1).coerceAtLeast(0)
)

@Immutable
data class CraftingGraphNode(
    val nodeKey: String,
    val sectionIndex: Int,
    val itemId: ItemID,
    val kind: GraphNodeKind,
    val layer: Int,
    val totalRequired: Int,
    val owned: Int,
    val craftCount: Int,
    val leftover: Int,
    val recipeTypeId: RecipeTypeID?,
    val availableRecipeCount: Int,
    val selectedRecipeIndex: Int,
    val isCompleted: Boolean,
    val xDp: Float,
    val yDp: Float,
    val widthDp: Float,
    val heightDp: Float
)

@Immutable
data class CraftingGraphEdge(
    val fromNodeKey: String,
    val toNodeKey: String,
    val fromItemId: ItemID,
    val toItemId: ItemID,
    val amount: Int,
    val waypoints: List<GraphPoint> = emptyList(),
    val isLeftover: Boolean = false
)

@Immutable
data class CraftingGraphLayout(
    val nodes: List<CraftingGraphNode> = emptyList(),
    val edges: List<CraftingGraphEdge> = emptyList(),
    val sections: List<CraftingGraphSection> = emptyList(),
    val widthDp: Float = 0f,
    val heightDp: Float = 0f,
    val layerCount: Int = 0
)

@Immutable
data class HighlightedSubgraph(
    val connectedNodeKeys: Set<String>,
    val incomingEdges: Set<CraftingGraphEdge>,
    val outgoingEdges: Set<CraftingGraphEdge>,
    val ancestorDescendantEdges: Set<CraftingGraphEdge>
)

private const val ROUTE_PREFIX = "__route_"
private const val LEFTOVER_SUFFIX = "#leftover"

/**
 * Builds per-target horizontal swimlanes (stacked vertically along the Y-axis) with
 * Sugiyama dummy routing corridors for skip-layer edges and dead-end leftover branches
 * so separate targets never tangle and lines/badges never overlap node cards.
 */
fun buildCraftingGraphLayout(
    targets: Map<ItemID, Int>,
    materialRows: List<MaterialRowUi>,
    stepRows: List<StepRowUi>,
    ownedInventory: Map<ItemID, Int>,
    nodeWidthDp: Float = 178f,
    nodeHeightDp: Float = 88f,
    columnGapDp: Float = 84f,
    rowGapDp: Float = 22f,
    waypointHeightDp: Float = 32f,
    waypointGapDp: Float = 14f,
    paddingDp: Float = 28f,
    sectionHeaderHeightDp: Float = 34f,
    sectionGapDp: Float = 30f,
    sectionPaddingDp: Float = 20f
): CraftingGraphLayout {
    val positiveTargets = targets.filterValues { it > 0 }
    if (positiveTargets.isEmpty()) return CraftingGraphLayout()

    val stepById = stepRows.associateBy { it.itemId }
    val materialById = materialRows.associateBy { it.itemId }

    val allNodes = mutableListOf<CraftingGraphNode>()
    val allEdges = mutableListOf<CraftingGraphEdge>()
    val allSections = mutableListOf<CraftingGraphSection>()

    var currentY = paddingDp

    positiveTargets.entries.forEachIndexed { sectionIndex, (rootTargetId, targetAmount) ->
        val visiting = mutableSetOf<ItemID>()
        val visited = mutableSetOf<ItemID>()
        val localPostOrder = mutableListOf<ItemID>()
        val activeChildren = mutableMapOf<ItemID, List<Pair<ItemID, Int>>>()

        fun discoverLocal(itemId: ItemID) {
            if (itemId in visited) return
            visiting.add(itemId)
            val step = stepById[itemId]
            if (step != null) {
                val safeChildren = step.recipe.requirements.entries
                    .filter { (reqId, qty) -> qty > 0 && reqId !in visiting }
                    .map { it.key to it.value }
                if (safeChildren.isNotEmpty()) {
                    activeChildren[itemId] = safeChildren
                    for ((reqId, _) in safeChildren) {
                        discoverLocal(reqId)
                    }
                }
            }
            visiting.remove(itemId)
            visited.add(itemId)
            localPostOrder.add(itemId)
        }

        discoverLocal(rootTargetId)

        // Compute local bottom-up depth (-1 for raw inputs, >= 0 for crafted intermediates/target).
        val localDepth = mutableMapOf<ItemID, Int>()
        for (itemId in localPostOrder) {
            val children = activeChildren[itemId].orEmpty()
            localDepth[itemId] = if (children.isEmpty()) {
                -1
            } else {
                children.maxOf { (childId, _) -> localDepth[childId] ?: -1 } + 1
            }
        }

        // Propagate top-down baseline demand strictly within this target's own DAG.
        val localDemand = mutableMapOf(rootTargetId to targetAmount)
        val localRawEdges = mutableListOf<Triple<ItemID, ItemID, Int>>()
        val localCraftCounts = mutableMapOf<ItemID, Int>()
        val localLeftovers = mutableMapOf<ItemID, Int>()

        for (itemId in localPostOrder.asReversed()) {
            val required = localDemand[itemId] ?: 0
            if (required <= 0) continue
            val step = stepById[itemId] ?: continue
            val children = activeChildren[itemId].orEmpty()
            if (children.isEmpty()) continue

            val craftCount = resourceAmount(result = step.recipe.amount, amountNeeded = required)
            localCraftCounts[itemId] = craftCount
            localLeftovers[itemId] = (craftCount * step.recipe.amount) - required

            for ((reqId, reqQty) in children) {
                val edgeAmount = reqQty * craftCount
                localRawEdges += Triple(reqId, itemId, edgeAmount)
                localDemand[reqId] = (localDemand[reqId] ?: 0) + edgeAmount
            }
        }

        // Bucket nodes into layers: raw inputs at layer 0, crafted items at depth + 1.
        val layerBuckets = sortedMapOf<Int, MutableList<ItemID>>()
        for (itemId in localPostOrder) {
            val depth = localDepth[itemId] ?: -1
            val bucketIdx = if (depth < 0) 0 else depth + 1
            layerBuckets.getOrPut(bucketIdx) { mutableListOf() }.add(itemId)
        }

        val orderedLayers = layerBuckets.values
            .map { bucket -> bucket.distinct().sorted().toMutableList() }
            .toMutableList()
        val goalColumnIndex = orderedLayers.lastIndex.coerceAtLeast(0)

        val nodeLayer = mutableMapOf<ItemID, Int>()
        orderedLayers.forEachIndexed { colIdx, layer ->
            layer.forEach { nodeLayer[it] = colIdx }
        }

        // Split skip-layer edges into per-column dummy routing slots so no curve crosses a card.
        val dummySlotId = { edgeIdx: Int, colIdx: Int -> "$ROUTE_PREFIX${sectionIndex}_${edgeIdx}_$colIdx" }
        val expandedSegments = mutableListOf<Pair<String, String>>()
        val slotLayer = nodeLayer.toMutableMap()

        localRawEdges.forEachIndexed { edgeIdx, (fromId, toId, _) ->
            val fromCol = nodeLayer.getValue(fromId)
            val toCol = nodeLayer.getValue(toId)
            if (toCol - fromCol > 1) {
                var prevSlot: String = fromId
                for (col in (fromCol + 1) until toCol) {
                    val slot = dummySlotId(edgeIdx, col)
                    orderedLayers[col].add(slot)
                    slotLayer[slot] = col
                    expandedSegments += prevSlot to slot
                    prevSlot = slot
                }
                expandedSegments += prevSlot to toId
            } else {
                expandedSegments += fromId to toId
            }
        }

        // Add dead-end leftover nodes branching 1 column to the right of any crafted node with leftover > 0.
        val localLeftoverBranches = mutableListOf<Pair<ItemID, Int>>()
        for (itemId in localPostOrder) {
            val step = stepById[itemId] ?: continue
            if (activeChildren[itemId].isNullOrEmpty()) continue
            val localRequired = localDemand[itemId] ?: 0
            val ownedDisplay = minOf(step.owned, localRequired)
            val isDone = step.isCompleted || ownedDisplay >= localRequired
            val leftoverAmount = if (isDone) 0 else (localLeftovers[itemId] ?: step.leftover)
            if (leftoverAmount > 0) {
                val fromCol = nodeLayer.getValue(itemId)
                val targetCol = fromCol + 1
                while (orderedLayers.size <= targetCol) {
                    orderedLayers.add(mutableListOf())
                }
                val leftoverSlotId = "$itemId$LEFTOVER_SUFFIX"
                orderedLayers[targetCol].add(leftoverSlotId)
                slotLayer[leftoverSlotId] = targetCol
                expandedSegments += itemId to leftoverSlotId
                localLeftoverBranches += itemId to leftoverAmount
            }
        }

        val sectionLayerCount = orderedLayers.size

        minimizeCrossings(
            orderedLayers = orderedLayers,
            segments = expandedSegments,
            slotLayer = slotLayer
        )

        val sectionX = paddingDp
        val sectionInnerLeft = sectionX + sectionPaddingDp
        val sectionWidth = sectionPaddingDp * 2f +
            sectionLayerCount * nodeWidthDp +
            (sectionLayerCount - 1).coerceAtLeast(0) * columnGapDp
        val columnXs = List(sectionLayerCount) { colIdx ->
            sectionInnerLeft + colIdx * (nodeWidthDp + columnGapDp)
        }

        val colHeights = orderedLayers.map { columnSlots ->
            computeColumnHeight(
                slots = columnSlots,
                nodeHeightDp = nodeHeightDp,
                rowGapDp = rowGapDp,
                waypointHeightDp = waypointHeightDp,
                waypointGapDp = waypointGapDp
            )
        }
        val sectionContentHeight = colHeights.maxOrNull()?.coerceAtLeast(nodeHeightDp) ?: nodeHeightDp
        val sectionTopContentY = currentY + sectionPaddingDp + sectionHeaderHeightDp
        val sectionHeight = sectionPaddingDp * 2f + sectionHeaderHeightDp + sectionContentHeight

        val waypointPoints = mutableMapOf<String, GraphPoint>()

        orderedLayers.forEachIndexed { colIdx, columnSlots ->
            val colX = columnXs[colIdx]
            val colHeight = colHeights[colIdx]
            var cursorY = sectionTopContentY + (sectionContentHeight - colHeight) / 2f

            columnSlots.forEachIndexed { idx, slotId ->
                val isDummy = slotId.startsWith(ROUTE_PREFIX)
                val slotHeight = if (isDummy) waypointHeightDp else nodeHeightDp

                if (isDummy) {
                    waypointPoints[slotId] = GraphPoint(
                        xDp = colX,
                        yDp = cursorY + waypointHeightDp / 2f
                    )
                } else if (slotId.endsWith(LEFTOVER_SUFFIX)) {
                    val baseItemId = slotId.removeSuffix(LEFTOVER_SUFFIX)
                    val leftoverAmount = localLeftovers[baseItemId] ?: 0
                    val step = stepById[baseItemId]
                    allNodes += CraftingGraphNode(
                        nodeKey = "$sectionIndex:$slotId",
                        sectionIndex = sectionIndex,
                        itemId = baseItemId,
                        kind = GraphNodeKind.LEFTOVER,
                        layer = colIdx,
                        totalRequired = leftoverAmount,
                        owned = 0,
                        craftCount = 0,
                        leftover = leftoverAmount,
                        recipeTypeId = step?.recipe?.recipeType,
                        availableRecipeCount = 0,
                        selectedRecipeIndex = 0,
                        isCompleted = false,
                        xDp = colX,
                        yDp = cursorY,
                        widthDp = nodeWidthDp,
                        heightDp = nodeHeightDp
                    )
                } else {
                    val itemId = slotId
                    val kind = when {
                        itemId == rootTargetId -> GraphNodeKind.TARGET
                        itemId in stepById -> GraphNodeKind.INTERMEDIATE
                        else -> GraphNodeKind.RAW
                    }
                    val step = stepById[itemId]
                    val localRequired = localDemand[itemId] ?: 0
                    val nodeKey = "$sectionIndex:$itemId"

                    if (step != null && activeChildren[itemId].orEmpty().isNotEmpty()) {
                        val ownedDisplay = minOf(step.owned, localRequired)
                        val isDone = step.isCompleted || ownedDisplay >= localRequired
                        allNodes += CraftingGraphNode(
                            nodeKey = nodeKey,
                            sectionIndex = sectionIndex,
                            itemId = itemId,
                            kind = kind,
                            layer = colIdx,
                            totalRequired = localRequired,
                            owned = ownedDisplay,
                            craftCount = if (isDone) 0 else (localCraftCounts[itemId] ?: step.craftCount),
                            leftover = if (isDone) 0 else (localLeftovers[itemId] ?: step.leftover),
                            recipeTypeId = step.recipe.recipeType,
                            availableRecipeCount = step.availableRecipeCount,
                            selectedRecipeIndex = step.selectedRecipeIndex,
                            isCompleted = isDone,
                            xDp = colX,
                            yDp = cursorY,
                            widthDp = nodeWidthDp,
                            heightDp = nodeHeightDp
                        )
                    } else {
                        val material = materialById[itemId]
                        val ownedRaw = material?.owned ?: (ownedInventory[itemId] ?: 0)
                        val ownedDisplay = minOf(ownedRaw, localRequired)
                        val isDone = (material?.isCompleted == true) || ownedDisplay >= localRequired
                        allNodes += CraftingGraphNode(
                            nodeKey = nodeKey,
                            sectionIndex = sectionIndex,
                            itemId = itemId,
                            kind = kind,
                            layer = colIdx,
                            totalRequired = localRequired,
                            owned = ownedDisplay,
                            craftCount = 0,
                            leftover = 0,
                            recipeTypeId = null,
                            availableRecipeCount = 0,
                            selectedRecipeIndex = 0,
                            isCompleted = isDone,
                            xDp = colX,
                            yDp = cursorY,
                            widthDp = nodeWidthDp,
                            heightDp = nodeHeightDp
                        )
                    }
                }

                if (idx < columnSlots.lastIndex) {
                    val nextIsDummy = columnSlots[idx + 1].startsWith(ROUTE_PREFIX)
                    val gap = if (!isDummy && !nextIsDummy) rowGapDp else waypointGapDp
                    cursorY += slotHeight + gap
                }
            }
        }

        localRawEdges.forEachIndexed { edgeIdx, (fromId, toId, amount) ->
            val fromCol = nodeLayer.getValue(fromId)
            val toCol = nodeLayer.getValue(toId)
            val waypoints = if (toCol - fromCol > 1) {
                (fromCol + 1 until toCol).mapNotNull { col ->
                    waypointPoints[dummySlotId(edgeIdx, col)]
                }
            } else {
                emptyList()
            }
            allEdges += CraftingGraphEdge(
                fromNodeKey = "$sectionIndex:$fromId",
                toNodeKey = "$sectionIndex:$toId",
                fromItemId = fromId,
                toItemId = toId,
                amount = amount,
                waypoints = waypoints,
                isLeftover = false
            )
        }

        for ((baseItemId, leftoverAmount) in localLeftoverBranches) {
            allEdges += CraftingGraphEdge(
                fromNodeKey = "$sectionIndex:$baseItemId",
                toNodeKey = "$sectionIndex:$baseItemId$LEFTOVER_SUFFIX",
                fromItemId = baseItemId,
                toItemId = baseItemId,
                amount = leftoverAmount,
                waypoints = emptyList(),
                isLeftover = true
            )
        }

        val hasRawColumn = localPostOrder.any { activeChildren[it].isNullOrEmpty() }
        allSections += CraftingGraphSection(
            rootTargetId = rootTargetId,
            targetAmount = targetAmount,
            xDp = sectionX,
            yDp = currentY,
            widthDp = sectionWidth,
            heightDp = sectionHeight,
            columnXsDp = columnXs,
            hasRawColumn = hasRawColumn,
            goalColumnIndex = goalColumnIndex
        )

        currentY += sectionHeight + sectionGapDp
    }

    val totalWidth = paddingDp * 2f + (allSections.maxOfOrNull { it.widthDp } ?: 0f)
    val totalHeight = (currentY - sectionGapDp) + paddingDp
    val maxLayers = allSections.maxOfOrNull { it.columnXsDp.size } ?: 0

    return CraftingGraphLayout(
        nodes = allNodes,
        edges = allEdges,
        sections = allSections,
        widthDp = totalWidth,
        heightDp = totalHeight,
        layerCount = maxLayers
    )
}

private fun computeColumnHeight(
    slots: List<String>,
    nodeHeightDp: Float,
    rowGapDp: Float,
    waypointHeightDp: Float,
    waypointGapDp: Float
): Float {
    if (slots.isEmpty()) return 0f
    var total = 0f
    slots.forEachIndexed { idx, slotId ->
        val isDummy = slotId.startsWith(ROUTE_PREFIX)
        total += if (isDummy) waypointHeightDp else nodeHeightDp
        if (idx < slots.lastIndex) {
            val nextIsDummy = slots[idx + 1].startsWith(ROUTE_PREFIX)
            total += if (!isDummy && !nextIsDummy) rowGapDp else waypointGapDp
        }
    }
    return total
}

/**
 * Runs 2 forward+backward barycenter sweeps across [orderedLayers] (including skip-layer
 * dummy routing slots) to untangle crossings cleanly.
 */
private fun minimizeCrossings(
    orderedLayers: MutableList<MutableList<String>>,
    segments: List<Pair<String, String>>,
    slotLayer: Map<String, Int>
) {
    if (orderedLayers.size <= 1) return

    val incomingBySlot = segments.groupBy({ it.second }, { it.first })
    val outgoingBySlot = segments.groupBy({ it.first }, { it.second })

    fun buildPositionMap(): Map<String, Int> {
        val pos = mutableMapOf<String, Int>()
        orderedLayers.forEach { layer ->
            layer.forEachIndexed { idx, id -> pos[id] = idx }
        }
        return pos
    }

    fun sortLayerByBarycenter(
        layerIdx: Int,
        neighborsOf: (String) -> List<String>,
        validNeighborLayer: (Int) -> Boolean
    ) {
        val pos = buildPositionMap()
        val currentLayer = orderedLayers[layerIdx]
        val indexed = currentLayer.mapIndexed { originalIndex, slotId ->
            val neighborPositions = neighborsOf(slotId)
                .filter { validNeighborLayer(slotLayer[it] ?: -1) }
                .mapNotNull { pos[it] }
            val barycenter = if (neighborPositions.isNotEmpty()) {
                neighborPositions.average()
            } else {
                originalIndex.toDouble()
            }
            Triple(barycenter, originalIndex, slotId)
        }
        val sorted = indexed.sortedWith(
            compareBy<Triple<Double, Int, String>> { it.first }
                .thenBy { it.second }
                .thenBy { it.third }
        ).map { it.third }
        currentLayer.clear()
        currentLayer.addAll(sorted)
    }

    repeat(2) {
        for (layerIdx in 1 until orderedLayers.size) {
            sortLayerByBarycenter(
                layerIdx = layerIdx,
                neighborsOf = { incomingBySlot[it].orEmpty() },
                validNeighborLayer = { it in 0 until layerIdx }
            )
        }
        for (layerIdx in (orderedLayers.size - 2) downTo 0) {
            sortLayerByBarycenter(
                layerIdx = layerIdx,
                neighborsOf = { outgoingBySlot[it].orEmpty() },
                validNeighborLayer = { it in (layerIdx + 1) until orderedLayers.size }
            )
        }
    }
}

/**
 * Collects all upstream ancestors and downstream descendants connected to [selectedNodeKey]
 * within its swimlane using bounded BFS traversal.
 */
fun findConnectedSubgraph(
    selectedNodeKey: String,
    edges: List<CraftingGraphEdge>
): HighlightedSubgraph {
    val incomingByNode = edges.groupBy { it.toNodeKey }
    val outgoingByNode = edges.groupBy { it.fromNodeKey }

    val visitedUp = linkedSetOf(selectedNodeKey)
    val upQueue = ArrayDeque<String>().apply { add(selectedNodeKey) }
    val ancestorDescendantEdges = linkedSetOf<CraftingGraphEdge>()

    while (upQueue.isNotEmpty()) {
        val current = upQueue.removeFirst()
        for (edge in incomingByNode[current].orEmpty()) {
            ancestorDescendantEdges += edge
            if (visitedUp.add(edge.fromNodeKey)) {
                upQueue.add(edge.fromNodeKey)
            }
        }
    }

    val visitedDown = linkedSetOf(selectedNodeKey)
    val downQueue = ArrayDeque<String>().apply { add(selectedNodeKey) }

    while (downQueue.isNotEmpty()) {
        val current = downQueue.removeFirst()
        for (edge in outgoingByNode[current].orEmpty()) {
            ancestorDescendantEdges += edge
            if (visitedDown.add(edge.toNodeKey)) {
                downQueue.add(edge.toNodeKey)
            }
        }
    }

    return HighlightedSubgraph(
        connectedNodeKeys = visitedUp + visitedDown,
        incomingEdges = incomingByNode[selectedNodeKey]?.toSet().orEmpty(),
        outgoingEdges = outgoingByNode[selectedNodeKey]?.toSet().orEmpty(),
        ancestorDescendantEdges = ancestorDescendantEdges
    )
}
