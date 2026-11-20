package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val preferences: Flow<UserPreferences>
    suspend fun setPreferredFuel(fuel: PreferredFuel)
    suspend fun setPreferredWood(wood: PreferredWood)
    suspend fun setPreferredStone(stone: PreferredStone)
    suspend fun setPreferStonecutter(prefer: Boolean)
    suspend fun setThemeMode(mode: ThemeMode)
    suspend fun setDynamicColor(enabled: Boolean)
    suspend fun setUseMojanglesFont(enabled: Boolean)
    suspend fun setShowShulkerUnits(enabled: Boolean)
    suspend fun setKeepScreenOnWhileCrafting(enabled: Boolean)
    suspend fun setLastRegistryCheckMillis(timestamp: Long)
}
