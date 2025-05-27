package ru.yandex.practicum.contacts.data.models

enum class SortOrder(val displayName: String) {
    NAME_ASC("имени (А-Я / A-Z)"),
    NAME_DESC("имени (Я-А / Z-A)"),
    LAST_NAME_ASC("фамилии (А-Я / A-Z"),
    LAST_NAME_DESC("фамилии (Я-А / Z-A")
} 