package ru.yandex.practicum.contacts.domain

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.contacts.data.Contact

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
}