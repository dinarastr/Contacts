package ru.yandex.practicum.contacts.data

enum class SortOrder(val displayName: String) {
    NAME_ASC("First Name (A-Z)"),
    NAME_DESC("First Name (Z-A)"),
    LAST_NAME_ASC("Last Name (A-Z)"),
    LAST_NAME_DESC("Last Name (Z-A)")
} 