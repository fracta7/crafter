package com.fracta7.crafter.domain.usecases

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry

@Immutable
data class LitematicaEntry(
    val rawName: String,
    val matchedItemId: ItemID?,
    val total: Int,
    val missing: Int,
    val available: Int
)

@Immutable
data class LitematicaParseResult(
    val suggestedProjectName: String?,
    val matched: List<LitematicaEntry>,
    val unmatched: List<LitematicaEntry>
)

private val ITEM_ALIASES = mapOf(
    "redstone wire" to "redstone",
    "wall torch" to "torch",
    "redstone wall torch" to "redstone_torch",
    "soul wall torch" to "soul_torch",
    "oak wall sign" to "oak_sign",
    "spruce wall sign" to "spruce_sign",
    "birch wall sign" to "birch_sign",
    "jungle wall sign" to "jungle_sign",
    "acacia wall sign" to "acacia_sign",
    "dark oak wall sign" to "dark_oak_sign",
    "mangrove wall sign" to "mangrove_sign",
    "cherry wall sign" to "cherry_sign",
    "bamboo wall sign" to "bamboo_sign",
    "crimson wall sign" to "crimson_sign",
    "warped wall sign" to "warped_sign",
    "pale oak wall sign" to "pale_oak_sign",
    "water" to "water_bucket",
    "lava" to "lava_bucket"
)

private val SCHEMATIC_HEADER_REGEX =
    Regex("""Material List for(?: schematic)?\s+['"]?([^'"|]+?)['"]?\s*$""", RegexOption.IGNORE_CASE)

/**
 * Parses Litematica material lists exported in ASCII table (.txt) or CSV (.csv) format.
 */
fun parseLitematicaMaterialList(
    content: String,
    itemRegistry: ItemRegistry
): LitematicaParseResult {
    val allItems = itemRegistry.getAll()
    val idByLowerName = allItems.values.associate { it.name.trim().lowercase() to it.id }

    var suggestedName: String? = null
    val parsedRows = mutableListOf<LitematicaEntry>()

    val lines = content.lineSequence().map { it.trim() }.filter { it.isNotEmpty() }.toList()
    for (line in lines) {
        if (suggestedName == null) {
            val cleanedForHeader = line.trim('|', ' ')
            val match = SCHEMATIC_HEADER_REGEX.find(cleanedForHeader)
            if (match != null) {
                val candidate = match.groupValues[1].trim()
                if (candidate.isNotEmpty()) suggestedName = candidate
                continue
            }
        }

        // Skip ASCII borders like +---+---+ and repeated headers
        if (line.startsWith("+") || line.startsWith("-")) continue

        val columns = when {
            line.startsWith("|") && line.endsWith("|") ->
                line.substring(1, line.length - 1).split("|").map { it.trim() }
            line.contains("|") ->
                line.split("|").map { it.trim() }
            else -> parseCsvLine(line)
        }

        if (columns.size < 2) continue
        val rawName = columns[0].trim()
        if (rawName.isEmpty() || rawName.equals("Item", ignoreCase = true)) continue
        if (rawName.startsWith("Material List", ignoreCase = true)) continue

        val total = parseCount(columns.getOrNull(1)) ?: continue
        val missing = parseCount(columns.getOrNull(2)) ?: total
        val available = parseCount(columns.getOrNull(3)) ?: (total - missing).coerceAtLeast(0)

        if (total <= 0 && missing <= 0) continue

        val matchedId = resolveItemId(rawName, idByLowerName, itemRegistry)
        parsedRows.add(
            LitematicaEntry(
                rawName = rawName,
                matchedItemId = matchedId,
                total = total.coerceAtLeast(0),
                missing = missing.coerceAtLeast(0),
                available = available.coerceAtLeast(0)
            )
        )
    }

    val aggregatedMatched = linkedMapOf<ItemID, LitematicaEntry>()
    val unmatched = mutableListOf<LitematicaEntry>()
    for (entry in parsedRows) {
        val id = entry.matchedItemId
        if (id == null) {
            unmatched.add(entry)
        } else {
            val existing = aggregatedMatched[id]
            aggregatedMatched[id] = if (existing == null) {
                entry
            } else {
                existing.copy(
                    total = existing.total + entry.total,
                    missing = existing.missing + entry.missing,
                    available = existing.available + entry.available
                )
            }
        }
    }

    return LitematicaParseResult(
        suggestedProjectName = suggestedName,
        matched = aggregatedMatched.values.toList(),
        unmatched = unmatched
    )
}

private fun resolveItemId(
    rawName: String,
    idByLowerName: Map<String, ItemID>,
    itemRegistry: ItemRegistry
): ItemID? {
    val lowered = rawName.trim().lowercase()
    idByLowerName[lowered]?.let { return it }

    val normalized = lowered
        .removePrefix("minecraft:")
        .replace(Regex("[^a-z0-9]+"), "_")
        .trim('_')
    if (itemRegistry.getItem(normalized) != null) return normalized

    val aliasId = ITEM_ALIASES[lowered] ?: ITEM_ALIASES[normalized.replace('_', ' ')]
    if (aliasId != null && itemRegistry.getItem(aliasId) != null) return aliasId

    return null
}

private fun parseCount(raw: String?): Int? {
    if (raw.isNullOrBlank()) return null
    val cleaned = raw.trim().replace(",", "").replace(" ", "")
    val digits = cleaned.takeWhile { it.isDigit() }
    return digits.toIntOrNull()
}

private fun parseCsvLine(line: String): List<String> {
    val cells = mutableListOf<String>()
    val current = StringBuilder()
    var inQuotes = false
    var i = 0
    while (i < line.length) {
        val ch = line[i]
        when {
            ch == '"' -> {
                if (inQuotes && i + 1 < line.length && line[i + 1] == '"') {
                    current.append('"')
                    i++
                } else {
                    inQuotes = !inQuotes
                }
            }
            ch == ',' && !inQuotes -> {
                cells.add(current.toString().trim())
                current.clear()
            }
            else -> current.append(ch)
        }
        i++
    }
    cells.add(current.toString().trim())
    return cells
}
