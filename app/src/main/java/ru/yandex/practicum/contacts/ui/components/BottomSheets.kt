package ru.yandex.practicum.contacts.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.R
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
        title = stringResource(R.string.filter_by_country_code),
        items = CountryCode.COMMON_CODES,
        selectedItems = selectedCodes,
        onItemsSelected = onCodesSelected,
        onDismiss = onDismiss
    ) { countryCode, isSelected ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { checked ->
                    val newSelection = selectedCodes.toMutableSet()
                    if (checked) {
                        newSelection.add(countryCode)
                    } else {
                        newSelection.remove(countryCode)
                    }
                    onCodesSelected(newSelection)
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
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
        title = stringResource(R.string.filter_by_messaging_app),
        items = MessagingApp.entries,
        selectedItems = selectedApps,
        onItemsSelected = onAppsSelected,
        onDismiss = onDismiss
    ) { app, isSelected ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
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
            Spacer(modifier = Modifier.width(16.dp))
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
        title = stringResource(R.string.sort_by_default),
        items = SortOrder.entries,
        selectedItems = setOf(currentSortOrder),
        onItemsSelected = { selected ->
            selected.firstOrNull()?.let { onSortOrderSelected(it) }
        },
        onDismiss = onDismiss
    ) { sortOrder, isSelected ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onSortOrderSelected(sortOrder) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(sortOrder.displayName)
        }
    }
} 