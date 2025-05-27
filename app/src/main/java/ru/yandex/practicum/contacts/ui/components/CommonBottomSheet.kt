package ru.yandex.practicum.contacts.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CommonBottomSheet(
    title: String,
    items: List<T>,
    selectedItems: Set<T>,
    onDismiss: () -> Unit,
    onItemsSelected: (Set<T>) -> Unit,
    itemContent: @Composable (T, Boolean, (Boolean) -> Unit) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(items) { item ->
                    itemContent(
                        item,
                        selectedItems.contains(item)
                    ) { isSelected ->
                        val newSelection = selectedItems.toMutableSet()
                        if (isSelected) {
                            newSelection.add(item)
                        } else {
                            newSelection.remove(item)
                        }
                        onItemsSelected(newSelection)
                    }
                }
            }
        }
    }
}

@Composable
fun CommonBottomSheetItem(
    isSelected: Boolean,
    onSelectionChanged: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = { onSelectionChanged(!isSelected) },
        color = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = onSelectionChanged
            )
            Spacer(modifier = Modifier.width(16.dp))
            content()
        }
    }
}

@Composable
fun CommonBottomSheetRadioItem(
    isSelected: Boolean,
    onSelectionChanged: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = { onSelectionChanged(!isSelected) },
        color = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = { onSelectionChanged(!isSelected) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            content()
        }
    }
} 