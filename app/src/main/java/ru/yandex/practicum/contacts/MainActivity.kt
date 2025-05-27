package ru.yandex.practicum.contacts

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import ru.yandex.practicum.contacts.ui.ContactsScreen
import ru.yandex.practicum.contacts.ui.theme.ContactsTheme

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
                    
                    // Request permission immediately when the app launches
                    LaunchedEffect(Unit) {
                        contactsPermissionState.launchPermissionRequest()
                    }
                    
                    ContactsScreen(permissionState = contactsPermissionState)
                }
            }
        }
    }
} 