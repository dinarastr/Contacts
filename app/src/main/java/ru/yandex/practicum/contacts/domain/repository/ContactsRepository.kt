package ru.yandex.practicum.contacts.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.contacts.data.models.Contact

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
}