package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.usecases.parseLitematicaMaterialList
import org.junit.Assert.assertEquals
import org.junit.Test

class LitematicaParserTest {

    private fun testRegistry(): ItemRegistry = ItemRegistry().apply {
        addItem(Item("oak_planks", "Oak Planks", 64, decomposable = true))
        addItem(Item("redstone", "Redstone Dust", 64, decomposable = false))
        addItem(Item("piston", "Piston", 64, decomposable = true))
        addItem(Item("hopper", "Hopper", 64, decomposable = true))
        addItem(Item("torch", "Torch", 64, decomposable = true))
        addItem(Item("iron_block", "Block of Iron", 64, decomposable = true))
    }

    @Test
    fun parsesAsciiTableWithHeaderAliasesDuplicatesAndUnmatched() {
        val txt = """
            +----------------------------------------+-------+---------+-----------+
            | Material List for schematic 'IronFarm'                               |
            +----------------------------------------+-------+---------+-----------+
            | Item                                   | Total | Missing | Available |
            +----------------------------------------+-------+---------+-----------+
            | Oak Planks                             | 1,280 |     640 |       640 |
            | Redstone Wire                          |    32 |      16 |        16 |
            | Torch                                  |     4 |       4 |         0 |
            | Wall Torch                             |     2 |       2 |         0 |
            | Unknown Modded Block                   |    10 |      10 |         0 |
            +----------------------------------------+-------+---------+-----------+
            | Item                                   | Total | Missing | Available |
            +----------------------------------------+-------+---------+-----------+
        """.trimIndent()

        val result = parseLitematicaMaterialList(txt, testRegistry())

        assertEquals("IronFarm", result.suggestedProjectName)
        assertEquals(3, result.matched.size)
        val planks = result.matched.first { it.matchedItemId == "oak_planks" }
        assertEquals(1280, planks.total)
        assertEquals(640, planks.missing)

        val redstone = result.matched.first { it.matchedItemId == "redstone" }
        assertEquals(32, redstone.total)
        assertEquals(16, redstone.missing)

        // Torch + Wall Torch aggregate onto `torch`
        val torch = result.matched.first { it.matchedItemId == "torch" }
        assertEquals(6, torch.total)
        assertEquals(6, torch.missing)

        assertEquals(1, result.unmatched.size)
        assertEquals("Unknown Modded Block", result.unmatched.single().rawName)
    }

    @Test
    fun parsesCsvWithQuotedNamesAndNormalizedIds() {
        val csv = """
            "Item","Total","Missing","Available"
            "Piston",12,8,4
            "minecraft:hopper",4,4,0
            "Block of Iron",9,0,9
        """.trimIndent()

        val result = parseLitematicaMaterialList(csv, testRegistry())
        assertEquals(3, result.matched.size)
        assertEquals(8, result.matched.first { it.matchedItemId == "piston" }.missing)
        assertEquals(4, result.matched.first { it.matchedItemId == "hopper" }.total)
        assertEquals(9, result.matched.first { it.matchedItemId == "iron_block" }.total)
    }
}
