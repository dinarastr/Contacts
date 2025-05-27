package ru.yandex.practicum.contacts.data

data class Contact(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val messagingApps: List<MessagingApp> = emptyList()
)

enum class MessagingApp {
    TELEGRAM,
    WHATS_APP,
    VIBER,
    SIGNAL,
    THREEMA,
    PHONE,
    EMAIL
}