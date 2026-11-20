package com.fracta7.crafter.ui.settings_screen

import android.os.Build
import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.ui.elements.DrawItem

private enum class ActiveSelectorDialog {
    FUEL,
    WOOD,
    STONE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val prefs by viewModel.preferences.collectAsState()
    val registryInfo by viewModel.registryInfo.collectAsState()
    val updateState by viewModel.updateState.collectAsState()
    var activeDialog by remember { mutableStateOf<ActiveSelectorDialog?>(null) }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "crafting_defaults_section") {
                SectionHeader("Crafting Defaults")
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        SelectorRow(
                            title = "Preferred Fuel",
                            subtitle = prefs.preferredFuel.label,
                            iconItemId = prefs.preferredFuel.itemId,
                            onClick = { activeDialog = ActiveSelectorDialog.FUEL }
                        )
                        HorizontalDivider()
                        SelectorRow(
                            title = "Preferred Wood",
                            subtitle = prefs.preferredWood.label,
                            iconItemId = "${prefs.preferredWood.id}_planks",
                            onClick = { activeDialog = ActiveSelectorDialog.WOOD }
                        )
                        HorizontalDivider()
                        SelectorRow(
                            title = "Preferred Stone",
                            subtitle = prefs.preferredStone.label,
                            iconItemId = prefs.preferredStone.id,
                            onClick = { activeDialog = ActiveSelectorDialog.STONE }
                        )
                        HorizontalDivider()
                        SwitchRow(
                            title = "Prefer Stonecutter",
                            subtitle = "Choose Stonecutter recipes over Crafting Table on ties",
                            checked = prefs.preferStonecutter,
                            onCheckedChange = viewModel::setPreferStonecutter
                        )
                    }
                }
            }

            item(key = "appearance_behavior_section") {
                SectionHeader("Appearance & Behavior")
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Theme",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                                ThemeMode.entries.forEachIndexed { index, mode ->
                                    SegmentedButton(
                                        selected = prefs.themeMode == mode,
                                        onClick = { viewModel.setThemeMode(mode) },
                                        shape = SegmentedButtonDefaults.itemShape(
                                            index = index,
                                            count = ThemeMode.entries.size
                                        ),
                                        label = { Text(mode.label) }
                                    )
                                }
                            }
                        }
                        HorizontalDivider()
                        val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                        SwitchRow(
                            title = "Dynamic Color",
                            subtitle = if (supportsDynamicColor) {
                                "Adapt palette from system wallpaper"
                            } else {
                                "Requires Android 12 or higher"
                            },
                            checked = prefs.dynamicColor && supportsDynamicColor,
                            enabled = supportsDynamicColor,
                            onCheckedChange = viewModel::setDynamicColor
                        )
                        HorizontalDivider()
                        SwitchRow(
                            title = "Use Mojangles Font",
                            subtitle = "Minecraft-style pixel typography",
                            checked = prefs.useMojanglesFont,
                            onCheckedChange = viewModel::setUseMojanglesFont
                        )
                        HorizontalDivider()
                        SwitchRow(
                            title = "Show Shulker Box Units",
                            subtitle = "Format 27+ stacks in Shulker Boxes",
                            checked = prefs.showShulkerUnits,
                            onCheckedChange = viewModel::setShowShulkerUnits
                        )
                        HorizontalDivider()
                        SwitchRow(
                            title = "Keep Screen On While Crafting",
                            subtitle = "Prevent sleep while viewing a crafting list",
                            checked = prefs.keepScreenOnWhileCrafting,
                            onCheckedChange = viewModel::setKeepScreenOnWhileCrafting
                        )
                    }
                }
            }

            item(key = "registry_data_section") {
                SectionHeader("Registry Data")
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Minecraft ${registryInfo.mcVersion}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Registry v${registryInfo.registryVersion} • ${registryInfo.itemCount} items • ${registryInfo.recipeCount} recipes",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            val lastCheckText = prefs.lastRegistryCheckMillis?.let { ts ->
                                DateUtils.getRelativeTimeSpanString(ts).toString()
                            } ?: "Never"
                            Text(
                                text = "${registryInfo.source.label} • Last checked: $lastCheckText",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        val statusMessage = updateState.statusMessage
                        if (statusMessage != null) {
                            Text(
                                text = statusMessage,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (updateState.isError) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = viewModel::checkForRegistryUpdates,
                                enabled = !updateState.isChecking,
                                modifier = Modifier.weight(1f)
                            ) {
                                if (updateState.isChecking) {
                                    CircularProgressIndicator(
                                        strokeWidth = 2.dp,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Checking...")
                                } else {
                                    Text("Check Updates")
                                }
                            }
                            if (registryInfo.source == RegistrySource.OTA_CACHE) {
                                OutlinedButton(
                                    onClick = viewModel::resetToBundledRegistry,
                                    enabled = !updateState.isChecking
                                ) {
                                    Text("Reset")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    when (activeDialog) {
        ActiveSelectorDialog.FUEL -> {
            OptionPickerDialog(
                title = "Preferred Fuel",
                options = PreferredFuel.entries,
                selected = prefs.preferredFuel,
                labelOf = { it.label },
                iconItemIdOf = { it.itemId },
                onSelect = {
                    viewModel.setPreferredFuel(it)
                    activeDialog = null
                },
                onDismiss = { activeDialog = null }
            )
        }

        ActiveSelectorDialog.WOOD -> {
            OptionPickerDialog(
                title = "Preferred Wood",
                options = PreferredWood.entries,
                selected = prefs.preferredWood,
                labelOf = { it.label },
                iconItemIdOf = { "${it.id}_planks" },
                onSelect = {
                    viewModel.setPreferredWood(it)
                    activeDialog = null
                },
                onDismiss = { activeDialog = null }
            )
        }

        ActiveSelectorDialog.STONE -> {
            OptionPickerDialog(
                title = "Preferred Stone",
                options = PreferredStone.entries,
                selected = prefs.preferredStone,
                labelOf = { it.label },
                iconItemIdOf = { it.id },
                onSelect = {
                    viewModel.setPreferredStone(it)
                    activeDialog = null
                },
                onDismiss = { activeDialog = null }
            )
        }

        null -> Unit
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
    )
}

@Composable
private fun SelectorRow(
    title: String,
    subtitle: String,
    iconItemId: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        DrawItem(itemID = iconItemId, iconSize = 32.dp)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
private fun SwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun <T> OptionPickerDialog(
    title: String,
    options: List<T>,
    selected: T,
    labelOf: (T) -> String,
    iconItemIdOf: (T) -> String,
    onSelect: (T) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                ) {
                    items(options) { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(option) }
                                .padding(vertical = 6.dp)
                        ) {
                            DrawItem(itemID = iconItemIdOf(option), iconSize = 32.dp)
                            Text(
                                text = labelOf(option),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 12.dp)
                            )
                            RadioButton(
                                selected = option == selected,
                                onClick = { onSelect(option) }
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
