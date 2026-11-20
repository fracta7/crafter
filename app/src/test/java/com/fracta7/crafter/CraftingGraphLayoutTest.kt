package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.usecases.GraphNodeKind
import com.fracta7.crafter.domain.usecases.buildCraftingGraphLayout
import com.fracta7.crafter.domain.usecases.computeCraftingProjection
import com.fracta7.crafter.domain.usecases.findConnectedSubgraph
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CraftingGraphLayoutTest {

    private fun buildTestRegistries(): Pair<ItemRegistry, RecipeRegistry> {
        val items = ItemRegistry().apply {
            addItem(Item("oak_log", "Oak Log", 64, decomposable = false))
            addItem(Item("oak_planks", "Oak Planks", 64, decomposable = true))
            addItem(Item("stick", "Stick", 64, decomposable = true))
            addItem(Item("chest", "Chest", 64, decomposable = true))
            addItem(Item("wooden_pickaxe", "Wooden Pickaxe", 1, decomposable = true))
        }
        val recipes = RecipeRegistry().apply {
            addRecipe(Recipe("oak_planks", 4, mapOf("oak_log" to 1), "crafting_shapeless"))
            addRecipe(Recipe("stick", 4, mapOf("oak_planks" to 2), "crafting_shaped"))
            addRecipe(Recipe("chest", 1, mapOf("oak_planks" to 8), "crafting_shaped"))
            addRecipe(
                Recipe(
                    "wooden_pickaxe",
                    1,
                    mapOf("oak_planks" to 3, "stick" to 2),
                    "crafting_shaped"
                )
            )
        }
        return items to recipes
    }

    @Test
    fun createsSeparateVerticalSwimlanesPerTargetWithIsolatedDagAndGoals() {
        val (itemRegistry, recipeRegistry) = buildTestRegistries()
        val targets = mapOf("chest" to 1, "wooden_pickaxe" to 1)
        val projection = computeCraftingProjection(
            targets = targets,
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )

        val layout = buildCraftingGraphLayout(
            targets = targets,
            materialRows = projection.materialRows,
            stepRows = projection.stepRows,
            ownedInventory = emptyMap()
        )

        // Two separate targets -> two strictly separated vertical swimlanes.
        assertEquals(2, layout.sections.size)
        assertTrue(layout.sections[0].yDp + layout.sections[0].heightDp < layout.sections[1].yDp)

        val lane0Nodes = layout.nodes.filter { it.sectionIndex == 0 }.associateBy { it.nodeKey }
        val lane1Nodes = layout.nodes.filter { it.sectionIndex == 1 }.associateBy { it.nodeKey }

        assertEquals(setOf("0:oak_log", "0:oak_planks", "0:chest"), lane0Nodes.keys)
        // In lane 1 (wooden_pickaxe), oak_planks (+3) and stick (+2) produce dead-end LEFTOVER branches.
        assertEquals(
            setOf(
                "1:oak_log",
                "1:oak_planks",
                "1:oak_planks#leftover",
                "1:stick",
                "1:stick#leftover",
                "1:wooden_pickaxe"
            ),
            lane1Nodes.keys
        )

        assertEquals(GraphNodeKind.TARGET, lane0Nodes.getValue("0:chest").kind)
        assertEquals(GraphNodeKind.TARGET, lane1Nodes.getValue("1:wooden_pickaxe").kind)
        assertEquals(GraphNodeKind.INTERMEDIATE, lane1Nodes.getValue("1:stick").kind)
        assertEquals(GraphNodeKind.LEFTOVER, lane1Nodes.getValue("1:oak_planks#leftover").kind)
        assertEquals(3, lane1Nodes.getValue("1:oak_planks#leftover").leftover)
        assertEquals(GraphNodeKind.LEFTOVER, lane1Nodes.getValue("1:stick#leftover").kind)
        assertEquals(2, lane1Nodes.getValue("1:stick#leftover").leftover)

        val leftoverEdges = layout.edges.filter { it.isLeftover }
        assertEquals(2, leftoverEdges.size)
        assertTrue(leftoverEdges.any { it.fromNodeKey == "1:oak_planks" && it.toNodeKey == "1:oak_planks#leftover" && it.amount == 3 })
        assertTrue(leftoverEdges.any { it.fromNodeKey == "1:stick" && it.toNodeKey == "1:stick#leftover" && it.amount == 2 })

        // Every edge stays strictly inside its own swimlane.
        assertTrue(layout.edges.all { e ->
            e.fromNodeKey.substringBefore(':') == e.toNodeKey.substringBefore(':')
        })

        // In lane 1, oak_planks -> wooden_pickaxe skips the stick column (layer 2) so it gets 1 waypoint.
        val skipEdge = layout.edges.first {
            it.fromNodeKey == "1:oak_planks" && it.toNodeKey == "1:wooden_pickaxe"
        }
        assertEquals(1, skipEdge.waypoints.size)
    }

    @Test
    fun routesSkipLayerEdgesThroughDummyCorridorsWithZeroCardOverlaps() {
        val items = ItemRegistry().apply {
            addItem(Item("pale_oak_log", "Pale Oak Log", 64, decomposable = false))
            addItem(Item("pale_oak_planks", "Pale Oak Planks", 64, decomposable = true))
            addItem(Item("pale_oak_trapdoor", "Pale Oak Trapdoor", 64, decomposable = true))
            addItem(Item("stone", "Stone", 64, decomposable = false))
            addItem(Item("stone_bricks", "Stone Bricks", 64, decomposable = true))
            addItem(Item("spruce_log", "Spruce Log", 64, decomposable = false))
            addItem(Item("spruce_planks", "Spruce Planks", 64, decomposable = true))
            addItem(Item("stick", "Stick", 64, decomposable = true))
            addItem(Item("redstone", "Redstone Dust", 64, decomposable = false))
            addItem(Item("redstone_torch", "Redstone Torch", 64, decomposable = true))
            addItem(Item("repeater", "Redstone Repeater", 64, decomposable = true))
        }
        val recipes = RecipeRegistry().apply {
            addRecipe(Recipe("pale_oak_planks", 4, mapOf("pale_oak_log" to 1), "crafting_shapeless"))
            addRecipe(Recipe("pale_oak_trapdoor", 2, mapOf("pale_oak_planks" to 6), "crafting_shaped"))
            addRecipe(Recipe("stone_bricks", 4, mapOf("stone" to 4), "crafting_shaped"))
            addRecipe(Recipe("spruce_planks", 4, mapOf("spruce_log" to 1), "crafting_shapeless"))
            addRecipe(Recipe("stick", 4, mapOf("spruce_planks" to 2), "crafting_shaped"))
            addRecipe(
                Recipe(
                    "redstone_torch",
                    1,
                    mapOf("redstone" to 1, "stick" to 1),
                    "crafting_shaped"
                )
            )
            addRecipe(
                Recipe(
                    "repeater",
                    1,
                    mapOf("stone" to 3, "redstone" to 1, "redstone_torch" to 2),
                    "crafting_shaped"
                )
            )
        }

        val targets = mapOf("pale_oak_trapdoor" to 64, "stone_bricks" to 64, "repeater" to 64)
        val projection = computeCraftingProjection(
            targets = targets,
            recipeRegistry = recipes,
            itemRegistry = items
        )
        val layout = buildCraftingGraphLayout(
            targets = targets,
            materialRows = projection.materialRows,
            stepRows = projection.stepRows,
            ownedInventory = emptyMap()
        )

        // All 3 end results live in separate swimlanes and carry TARGET kind at the rightmost column.
        assertEquals(3, layout.sections.size)
        val goalNodes = layout.nodes.filter { it.kind == GraphNodeKind.TARGET }
        assertEquals(
            setOf("pale_oak_trapdoor", "stone_bricks", "repeater"),
            goalNodes.map { it.itemId }.toSet()
        )
        goalNodes.forEach { goal ->
            val maxLayerInSection = layout.nodes
                .filter { it.sectionIndex == goal.sectionIndex }
                .maxOf { it.layer }
            assertEquals(maxLayerInSection, goal.layer)
        }

        // Assert NO two real nodes overlap anywhere in the entire canvas.
        for (i in layout.nodes.indices) {
            val a = layout.nodes[i]
            for (j in i + 1 until layout.nodes.size) {
                val b = layout.nodes[j]
                val overlapsX = a.xDp < b.xDp + b.widthDp && b.xDp < a.xDp + a.widthDp
                val overlapsY = a.yDp < b.yDp + b.heightDp && b.yDp < a.yDp + a.heightDp
                assertTrue(
                    "Nodes ${a.nodeKey} and ${b.nodeKey} overlap",
                    !(overlapsX && overlapsY)
                )
            }
        }

        // Verify skip-layer edges route through corridors strictly outside all cards in crossed columns.
        val nodeByKey = layout.nodes.associateBy { it.nodeKey }
        val repeaterEdges = layout.edges.filter { it.fromNodeKey.startsWith("2:") }
        assertTrue(repeaterEdges.any { it.fromItemId == "redstone" && it.toItemId == "repeater" })

        repeaterEdges.forEach { edge ->
            val fromNode = nodeByKey.getValue(edge.fromNodeKey)
            val toNode = nodeByKey.getValue(edge.toNodeKey)
            val skippedLayers = (toNode.layer - fromNode.layer - 1).coerceAtLeast(0)
            assertEquals(skippedLayers, edge.waypoints.size)

            edge.waypoints.forEachIndexed { idx, wp ->
                val colLayer = fromNode.layer + 1 + idx
                val cardsInColumn = layout.nodes.filter {
                    it.sectionIndex == 2 && it.layer == colLayer
                }
                cardsInColumn.forEach { card ->
                    assertTrue(
                        "Waypoint for ${edge.fromItemId}->${edge.toItemId} at col $colLayer ($wp) crosses card ${card.itemId} [${card.yDp}, ${card.yDp + card.heightDp}]",
                        wp.yDp < card.yDp || wp.yDp > card.yDp + card.heightDp
                    )
                }
            }
        }
    }

    @Test
    fun preservesCompletedNodesHandlesRawOnlyTargetsAndIsolatesSubgraphHighlight() {
        val (itemRegistry, recipeRegistry) = buildTestRegistries()

        val rawOnlyTargets = mapOf("oak_log" to 12)
        val rawProjection = computeCraftingProjection(
            targets = rawOnlyTargets,
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry
        )
        val rawLayout = buildCraftingGraphLayout(
            targets = rawOnlyTargets,
            materialRows = rawProjection.materialRows,
            stepRows = rawProjection.stepRows,
            ownedInventory = emptyMap()
        )
        assertEquals(1, rawLayout.nodes.size)
        assertTrue(rawLayout.edges.isEmpty())
        assertTrue(rawLayout.widthDp > 0f && rawLayout.heightDp > 0f)
        assertEquals(GraphNodeKind.TARGET, rawLayout.nodes.single().kind)

        val targets = mapOf("chest" to 1, "wooden_pickaxe" to 1)
        val owned = mapOf("oak_planks" to 8)
        val completedProjection = computeCraftingProjection(
            targets = targets,
            recipeRegistry = recipeRegistry,
            itemRegistry = itemRegistry,
            ownedInventory = owned
        )
        val completedLayout = buildCraftingGraphLayout(
            targets = targets,
            materialRows = completedProjection.materialRows,
            stepRows = completedProjection.stepRows,
            ownedInventory = owned
        )

        // Selecting a node in swimlane 1 only highlights swimlane 1 (including its dead-end leftover branch).
        val stickSubgraph = findConnectedSubgraph("1:stick", completedLayout.edges)
        assertEquals(
            setOf("1:oak_log", "1:oak_planks", "1:stick", "1:stick#leftover", "1:wooden_pickaxe"),
            stickSubgraph.connectedNodeKeys
        )
        assertEquals(1, stickSubgraph.incomingEdges.size)
        assertEquals(2, stickSubgraph.outgoingEdges.size)
    }
}
