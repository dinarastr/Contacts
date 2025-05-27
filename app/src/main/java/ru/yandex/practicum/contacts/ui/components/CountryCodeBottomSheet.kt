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
import ru.yandex.practicum.contacts.data.CountryCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeBottomSheet(
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Filter by country code",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            CountryCode.COMMON_CODES.forEach { countryCode ->
                CountryCodeOption(
                    countryCode = countryCode,
                    selectedCodes = selectedCodes,
                    onCodesSelected = onCodesSelected
                )
            }
        }
    }
}

@Composable
fun CountryCodeOption(
    countryCode: CountryCode,
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(countryCode.code)
            Text(
                countryCode.country,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Checkbox(
            checked = countryCode in selectedCodes,
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
    }
}