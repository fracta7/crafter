package com.fracta7.crafter.domain.usecases

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID

enum class ExportFormat(val label: String) {
    PLAIN_TEXT("Plain Text"),
    MARKDOWN("Markdown")
}

fun formatMaterialList(
    projectName: String,
    targets: Map<ItemID, Int>,
    materialRows: List<MaterialRowUi>,
    leftovers: Map<ItemID, Int>,
    resolveItem: (ItemID) -> Item?,
    format: ExportFormat,
    showShulkerUnits: Boolean = true
): String {
    val title = projectName.trim().ifBlank { "Crafter Material List" }
    val sb = StringBuilder()

    when (format) {
        ExportFormat.MARKDOWN -> {
            sb.appendLine("# $title")
            sb.appendLine()
            sb.appendLine("## Targets")
            targets.forEach { (id, qty) ->
                val name = resolveItem(id)?.name ?: id
                sb.appendLine("- $name ×$qty")
            }
            sb.appendLine()
            sb.appendLine("## Raw Materials")
            if (materialRows.isEmpty()) {
                sb.appendLine("_None_")
            } else {
                materialRows.forEach { row ->
                    val item = resolveItem(row.itemId)
                    val name = item?.name ?: row.itemId
                    val box = if (row.isCompleted) "[x]" else "[ ]"
                    val stackSize = item?.stackSize ?: 64
                    val summary = getStackText(
                        amount = row.totalRequired,
                        stackSize = stackSize,
                        showShulkerUnits = showShulkerUnits
                    ).text.removePrefix("Amount: ")
                    val progressNote = if (row.owned > 0 && !row.isCompleted) {
                        " (have ${row.owned}, need ${row.remaining})"
                    } else {
                        ""
                    }
                    sb.appendLine("- $box **$name** — $summary$progressNote")
                }
            }
            if (leftovers.isNotEmpty()) {
                sb.appendLine()
                sb.appendLine("## Leftovers")
                leftovers.forEach { (id, qty) ->
                    val name = resolveItem(id)?.name ?: id
                    sb.appendLine("- $name ×$qty")
                }
            }
        }

        ExportFormat.PLAIN_TEXT -> {
            sb.appendLine(title)
            sb.appendLine("=".repeat(title.length.coerceAtLeast(12)))
            sb.appendLine()
            sb.appendLine("Targets:")
            targets.forEach { (id, qty) ->
                val name = resolveItem(id)?.name ?: id
                sb.appendLine("  • $name x$qty")
            }
            sb.appendLine()
            sb.appendLine("Raw Materials:")
            if (materialRows.isEmpty()) {
                sb.appendLine("  (none)")
            } else {
                materialRows.forEach { row ->
                    val item = resolveItem(row.itemId)
                    val name = item?.name ?: row.itemId
                    val stackSize = item?.stackSize ?: 64
                    val summary = getStackText(
                        amount = row.totalRequired,
                        stackSize = stackSize,
                        showShulkerUnits = showShulkerUnits
                    ).text.removePrefix("Amount: ")
                    val status = when {
                        row.isCompleted -> " [DONE]"
                        row.owned > 0 -> " (have ${row.owned}, need ${row.remaining})"
                        else -> ""
                    }
                    sb.appendLine("  • $name: $summary$status")
                }
            }
            if (leftovers.isNotEmpty()) {
                sb.appendLine()
                sb.appendLine("Leftovers:")
                leftovers.forEach { (id, qty) ->
                    val name = resolveItem(id)?.name ?: id
                    sb.appendLine("  • $name x$qty")
                }
            }
        }
    }

    return sb.toString().trimEnd()
}
