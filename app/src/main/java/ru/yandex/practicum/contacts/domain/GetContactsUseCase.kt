package ru.yandex.practicum.contacts.domain

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.contacts.data.Contact
import ru.yandex.practicum.contacts.data.repository.ContactsRepository

class GetContactsUseCase(private val repository: ContactsRepository) {
    operator fun invoke(): Flow<List<Contact>> = repository.getContacts()
} 