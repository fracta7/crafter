package com.fracta7.crafter.ui.projects_screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fracta7.crafter.domain.usecases.ExportFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportMaterialListDialog(
    projectName: String,
    formatContent: (ExportFormat) -> String,
    onDismissRequest: () -> Unit
) {
    var selectedFormat by remember { mutableStateOf(ExportFormat.MARKDOWN) }
    var copiedNotice by remember { mutableStateOf(false) }
    val content = remember(selectedFormat, projectName) { formatContent(selectedFormat) }
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Export Material List",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    ExportFormat.entries.forEachIndexed { index, format ->
                        SegmentedButton(
                            selected = selectedFormat == format,
                            onClick = {
                                selectedFormat = format
                                copiedNotice = false
                            },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = ExportFormat.entries.size
                            ),
                            label = { Text(format.label) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp, max = 280.dp)
                ) {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(12.dp)
                    )
                }
                if (copiedNotice) {
                    Text(
                        text = "Copied to clipboard",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Close")
                    }
                    TextButton(
                        onClick = {
                            clipboard.setText(AnnotatedString(content))
                            copiedNotice = true
                        }
                    ) {
                        Text("Copy")
                    }
                    TextButton(
                        onClick = {
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, projectName)
                                putExtra(Intent.EXTRA_TEXT, content)
                            }
                            context.startActivity(
                                Intent.createChooser(sendIntent, "Share Material List")
                            )
                        }
                    ) {
                        Text("Share")
                    }
                }
            }
        }
    }
}
