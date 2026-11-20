package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.usecases.ExportFormat
import com.fracta7.crafter.domain.usecases.MaterialRowUi
import com.fracta7.crafter.domain.usecases.formatMaterialList
import org.junit.Assert.assertTrue
import org.junit.Test

class MaterialListExporterTest {

    private val items = mapOf(
        "piston" to Item("piston", "Piston", 64, decomposable = true),
        "iron_ingot" to Item("iron_ingot", "Iron Ingot", 64, decomposable = false),
        "oak_planks" to Item("oak_planks", "Oak Planks", 64, decomposable = true),
        "stick" to Item("stick", "Stick", 64, decomposable = true)
    )

    @Test
    fun formatsMarkdownAndPlainTextChecklistsWithLeftovers() {
        val rows = listOf(
            MaterialRowUi("iron_ingot", totalRequired = 128, owned = 64, remaining = 64, isCompleted = false),
            MaterialRowUi("oak_planks", totalRequired = 1728, owned = 1728, remaining = 0, isCompleted = true)
        )
        val targets = mapOf("piston" to 8)
        val leftovers = mapOf("stick" to 2)

        val markdown = formatMaterialList(
            projectName = "Door Build",
            targets = targets,
            materialRows = rows,
            leftovers = leftovers,
            resolveItem = { items[it] },
            format = ExportFormat.MARKDOWN,
            showShulkerUnits = true
        )
        assertTrue(markdown.contains("# Door Build"))
        assertTrue(markdown.contains("- [ ] **Iron Ingot**"))
        assertTrue(markdown.contains("(have 64, need 64)"))
        assertTrue(markdown.contains("- [x] **Oak Planks** — 1728 (1 shulker)"))
        assertTrue(markdown.contains("- Stick ×2"))

        val plain = formatMaterialList(
            projectName = "Door Build",
            targets = targets,
            materialRows = rows,
            leftovers = leftovers,
            resolveItem = { items[it] },
            format = ExportFormat.PLAIN_TEXT,
            showShulkerUnits = false
        )
        assertTrue(plain.contains("Door Build"))
        assertTrue(plain.contains("Oak Planks: 1728 (27 stacks) [DONE]"))
    }
}
