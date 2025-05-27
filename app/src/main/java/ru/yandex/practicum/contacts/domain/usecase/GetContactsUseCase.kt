package ru.yandex.practicum.contacts.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.contacts.data.models.Contact
import ru.yandex.practicum.contacts.domain.repository.ContactsRepository

class GetContactsUseCase(private val repository: ContactsRepository) {
    operator fun invoke(): Flow<List<Contact>> = repository.getContacts()
} 