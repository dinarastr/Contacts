package ru.yandex.practicum.contacts.presentation.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.yandex.practicum.contacts.data.repository.ContactsRepositoryImpl
import ru.yandex.practicum.contacts.domain.GetContactsUseCase

class ContactsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            val repository = ContactsRepositoryImpl(context)
            val getContactsUseCase = GetContactsUseCase(repository)
            return ContactsViewModel(getContactsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 