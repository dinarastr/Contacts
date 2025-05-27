package ru.yandex.practicum.contacts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.data.MessagingApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    selectedApps: Set<MessagingApp>,
    onAppsSelected: (Set<MessagingApp>) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Filter by messaging app",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            MessagingApp.entries.forEach { app ->
                FilterOption(
                    app = app,
                    selectedApps = selectedApps,
                    onAppsSelected = onAppsSelected
                )
            }
        }
    }
}

@Composable
private fun FilterOption(
    app: MessagingApp,
    selectedApps: Set<MessagingApp>,
    onAppsSelected: (Set<MessagingApp>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(app.name)
        Checkbox(
            checked = app in selectedApps,
            onCheckedChange = { checked ->
                val newSelection = selectedApps.toMutableSet()
                if (checked) {
                    newSelection.add(app)
                } else {
                    newSelection.remove(app)
                }
                onAppsSelected(newSelection)
            }
        )
    }
}