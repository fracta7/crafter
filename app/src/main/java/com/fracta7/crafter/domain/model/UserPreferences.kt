package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable
import com.fracta7.crafter.domain.usecases.RecipePreferences

enum class ThemeMode(val label: String) {
    SYSTEM("System"),
    LIGHT("Light"),
    DARK("Dark")
}

enum class PreferredFuel(val itemId: ItemID, val label: String) {
    COAL("coal", "Coal"),
    CHARCOAL("charcoal", "Charcoal"),
    BLAZE_ROD("blaze_rod", "Blaze Rod"),
    DRIED_KELP_BLOCK("dried_kelp_block", "Dried Kelp Block"),
    COAL_BLOCK("coal_block", "Block of Coal"),
    LAVA_BUCKET("lava_bucket", "Lava Bucket")
}

enum class PreferredWood(val id: String, val label: String) {
    OAK("oak", "Oak"),
    SPRUCE("spruce", "Spruce"),
    BIRCH("birch", "Birch"),
    JUNGLE("jungle", "Jungle"),
    ACACIA("acacia", "Acacia"),
    DARK_OAK("dark_oak", "Dark Oak"),
    MANGROVE("mangrove", "Mangrove"),
    CHERRY("cherry", "Cherry"),
    BAMBOO("bamboo", "Bamboo"),
    CRIMSON("crimson", "Crimson"),
    WARPED("warped", "Warped"),
    PALE_OAK("pale_oak", "Pale Oak"),
    POPLAR("poplar", "Poplar")
}

enum class PreferredStone(val id: ItemID, val label: String) {
    COBBLESTONE("cobblestone", "Cobblestone"),
    COBBLED_DEEPSLATE("cobbled_deepslate", "Cobbled Deepslate"),
    BLACKSTONE("blackstone", "Blackstone")
}

@Immutable
data class UserPreferences(
    val preferredFuel: PreferredFuel = PreferredFuel.COAL,
    val preferredWood: PreferredWood = PreferredWood.OAK,
    val preferredStone: PreferredStone = PreferredStone.COBBLESTONE,
    val preferStonecutter: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true,
    val useMojanglesFont: Boolean = true,
    val showShulkerUnits: Boolean = true,
    val keepScreenOnWhileCrafting: Boolean = false,
    val lastRegistryCheckMillis: Long? = null
)

fun UserPreferences.toRecipePreferences(): RecipePreferences = RecipePreferences(
    preferredFuelItemId = preferredFuel.itemId,
    preferredWoodId = preferredWood.id,
    preferredStoneId = preferredStone.id,
    preferStonecutter = preferStonecutter
)
