package ru.yandex.practicum.contacts.data.repository

import android.content.Context
import android.provider.ContactsContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yandex.practicum.contacts.data.Contact
import ru.yandex.practicum.contacts.data.MessagingApp

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
}

class ContactsRepositoryImpl(private val context: Context) : ContactsRepository {
    override fun getContacts(): Flow<List<Contact>> = flow {
        val contacts = mutableListOf<Contact>()
        
        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val fullName = cursor.getString(nameIndex)
                val phoneNumber = cursor.getString(numberIndex)
                
                // Split the full name into first and last name
                val nameParts = fullName.split(" ", limit = 2)
                val firstName = nameParts.firstOrNull() ?: ""
                val lastName = nameParts.getOrNull(1) ?: ""
                
                // Create a contact with some random messaging apps for demonstration
                val messagingApps = MessagingApp.values().filter { (0..1).random() == 1 }
                
                contacts.add(
                    Contact(
                        id = id,
                        firstName = firstName,
                        lastName = lastName,
                        phoneNumber = phoneNumber,
                        messagingApps = messagingApps
                    )
                )
            }
        }
        
        emit(contacts)
    }.flowOn(Dispatchers.IO)
} 