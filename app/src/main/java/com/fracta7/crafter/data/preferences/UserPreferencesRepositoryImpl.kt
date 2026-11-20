package com.fracta7.crafter.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    private object Keys {
        val PREFERRED_FUEL = stringPreferencesKey("preferred_fuel")
        val PREFERRED_WOOD = stringPreferencesKey("preferred_wood")
        val PREFERRED_STONE = stringPreferencesKey("preferred_stone")
        val PREFER_STONECUTTER = booleanPreferencesKey("prefer_stonecutter")
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
        val USE_MOJANGLES_FONT = booleanPreferencesKey("use_mojangles_font")
        val SHOW_SHULKER_UNITS = booleanPreferencesKey("show_shulker_units")
        val KEEP_SCREEN_ON = booleanPreferencesKey("keep_screen_on_while_crafting")
        val LAST_REGISTRY_CHECK = longPreferencesKey("last_registry_check_millis")
    }

    override val preferences: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs ->
            UserPreferences(
                preferredFuel = enumValueOrDefault(prefs[Keys.PREFERRED_FUEL], PreferredFuel.COAL),
                preferredWood = enumValueOrDefault(prefs[Keys.PREFERRED_WOOD], PreferredWood.OAK),
                preferredStone = when (val raw = prefs[Keys.PREFERRED_STONE]) {
                    "DEEPSLATE" -> PreferredStone.COBBLED_DEEPSLATE
                    else -> enumValueOrDefault(raw, PreferredStone.COBBLESTONE)
                },
                preferStonecutter = prefs[Keys.PREFER_STONECUTTER] ?: true,
                themeMode = enumValueOrDefault(prefs[Keys.THEME_MODE], ThemeMode.SYSTEM),
                dynamicColor = prefs[Keys.DYNAMIC_COLOR] ?: true,
                useMojanglesFont = prefs[Keys.USE_MOJANGLES_FONT] ?: true,
                showShulkerUnits = prefs[Keys.SHOW_SHULKER_UNITS] ?: true,
                keepScreenOnWhileCrafting = prefs[Keys.KEEP_SCREEN_ON] ?: false,
                lastRegistryCheckMillis = prefs[Keys.LAST_REGISTRY_CHECK]
            )
        }

    override suspend fun setPreferredFuel(fuel: PreferredFuel) {
        dataStore.edit { it[Keys.PREFERRED_FUEL] = fuel.name }
    }

    override suspend fun setPreferredWood(wood: PreferredWood) {
        dataStore.edit { it[Keys.PREFERRED_WOOD] = wood.name }
    }

    override suspend fun setPreferredStone(stone: PreferredStone) {
        dataStore.edit { it[Keys.PREFERRED_STONE] = stone.name }
    }

    override suspend fun setPreferStonecutter(prefer: Boolean) {
        dataStore.edit { it[Keys.PREFER_STONECUTTER] = prefer }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { it[Keys.THEME_MODE] = mode.name }
    }

    override suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { it[Keys.DYNAMIC_COLOR] = enabled }
    }

    override suspend fun setUseMojanglesFont(enabled: Boolean) {
        dataStore.edit { it[Keys.USE_MOJANGLES_FONT] = enabled }
    }

    override suspend fun setShowShulkerUnits(enabled: Boolean) {
        dataStore.edit { it[Keys.SHOW_SHULKER_UNITS] = enabled }
    }

    override suspend fun setKeepScreenOnWhileCrafting(enabled: Boolean) {
        dataStore.edit { it[Keys.KEEP_SCREEN_ON] = enabled }
    }

    override suspend fun setLastRegistryCheckMillis(timestamp: Long) {
        dataStore.edit { it[Keys.LAST_REGISTRY_CHECK] = timestamp }
    }

    private inline fun <reified T : Enum<T>> enumValueOrDefault(raw: String?, default: T): T {
        if (raw == null) return default
        return enumValues<T>().firstOrNull { it.name == raw } ?: default
    }
}
