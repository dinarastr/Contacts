package ru.yandex.practicum.contacts.data

data class CountryCode(
    val code: String,
    val country: String
) {
    companion object {
        val COMMON_CODES = listOf(
            CountryCode("+1", "United States/Canada"),
            CountryCode("+44", "United Kingdom"),
            CountryCode("+49", "Germany"),
            CountryCode("+33", "France"),
            CountryCode("+7", "Russia"),
            CountryCode("+86", "China"),
            CountryCode("+81", "Japan"),
            CountryCode("+91", "India"),
            CountryCode("+61", "Australia"),
            CountryCode("+55", "Brazil")
        )
    }
} 