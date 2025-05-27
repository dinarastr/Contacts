package ru.yandex.practicum.contacts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.yandex.practicum.contacts.data.CountryCode
import ru.yandex.practicum.contacts.data.MessagingApp
import ru.yandex.practicum.contacts.data.SortOrder

@Composable
fun CountryCodeBottomSheet(
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = "Filter by country code",
        items = CountryCode.COMMON_CODES,
        selectedItems = selectedCodes,
        onDismiss = onDismiss,
        onItemsSelected = onCodesSelected
    ) { countryCode, isSelected, onSelectionChanged ->
        CommonBottomSheetItem(
            isSelected = isSelected,
            onSelectionChanged = onSelectionChanged
        ) {
            Column {
                Text(countryCode.code)
                Text(
                    countryCode.country,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FilterBottomSheet(
    selectedApps: Set<MessagingApp>,
    onAppsSelected: (Set<MessagingApp>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = "Filter by messaging app",
        items = MessagingApp.entries,
        selectedItems = selectedApps,
        onDismiss = onDismiss,
        onItemsSelected = onAppsSelected
    ) { app, isSelected, onSelectionChanged ->
        CommonBottomSheetItem(
            isSelected = isSelected,
            onSelectionChanged = onSelectionChanged
        ) {
            Text(app.name)
        }
    }
}

@Composable
fun SortBottomSheet(
    currentSortOrder: SortOrder,
    onSortOrderSelected: (SortOrder) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = "Sort by",
        items = SortOrder.entries,
        selectedItems = setOf(currentSortOrder),
        onDismiss = onDismiss,
        onItemsSelected = { selected ->
            selected.firstOrNull()?.let { onSortOrderSelected(it) }
        }
    ) { sortOrder, isSelected, _ ->
        CommonBottomSheetItem(
            isSelected = isSelected,
            onSelectionChanged = { onSortOrderSelected(sortOrder) }
        ) {
            Text(
                text = sortOrder.displayName
            )
        }
    }
} 