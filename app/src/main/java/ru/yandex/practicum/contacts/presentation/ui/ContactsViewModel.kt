package ru.yandex.practicum.contacts.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.yandex.practicum.contacts.data.Contact
import ru.yandex.practicum.contacts.data.CountryCode
import ru.yandex.practicum.contacts.data.MessagingApp
import ru.yandex.practicum.contacts.data.SortOrder
import ru.yandex.practicum.contacts.domain.GetContactsUseCase

class ContactsViewModel(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {
    var contacts by mutableStateOf<List<Contact>>(emptyList())
        private set
    
    var searchQuery by mutableStateOf("")
        private set
    
    var sortOrder by mutableStateOf(SortOrder.NAME_ASC)
        private set
    
    var selectedMessagingApps by mutableStateOf<Set<MessagingApp>>(emptySet())
        private set
    
    var selectedCountryCodes by mutableStateOf<Set<CountryCode>>(emptySet())
        private set
    
    var isSearchVisible by mutableStateOf(false)
        private set
    
    var hasContactsPermission by mutableStateOf(false)
        private set
    
    fun onPermissionGranted() {
        hasContactsPermission = true
        loadContacts()
    }
    
    private fun loadContacts() {
        getContactsUseCase()
            .onEach { contactsList ->
                contacts = contactsList
            }
            .launchIn(viewModelScope)
    }
    
    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
    
    fun updateSortOrder(order: SortOrder) {
        sortOrder = order
    }
    
    fun updateSelectedMessagingApps(apps: Set<MessagingApp>) {
        selectedMessagingApps = apps
    }
    
    fun updateSelectedCountryCodes(codes: Set<CountryCode>) {
        selectedCountryCodes = codes
    }
    
    fun toggleSearchVisibility() {
        isSearchVisible = !isSearchVisible
    }
    
    fun getFilteredAndSortedContacts(): List<Contact> {
        return contacts
            .filter { contact ->
                val matchesSearch = contact.firstName.contains(searchQuery, ignoreCase = true) ||
                        contact.lastName.contains(searchQuery, ignoreCase = true)
                val matchesMessagingApps = selectedMessagingApps.isEmpty() ||
                        contact.messagingApps.any { it in selectedMessagingApps }
                val matchesCountryCode = selectedCountryCodes.isEmpty() ||
                        selectedCountryCodes.any { contact.phoneNumber.startsWith(it.code) }
                matchesSearch && matchesMessagingApps && matchesCountryCode
            }
            .sortedWith(
                when (sortOrder) {
                    SortOrder.NAME_ASC -> compareBy { it.firstName.lowercase() }
                    SortOrder.NAME_DESC -> compareByDescending { it.firstName.lowercase() }
                    SortOrder.LAST_NAME_ASC -> compareBy { it.lastName.lowercase() }
                    SortOrder.LAST_NAME_DESC -> compareByDescending { it.lastName.lowercase() }
                }
            )
    }
}