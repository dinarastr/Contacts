package ru.yandex.practicum.contacts.presentation

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
import androidx.core.view.WindowCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import ru.yandex.practicum.contacts.presentation.ui.theme.ContactsTheme
import ru.yandex.practicum.contacts.ui.ContactsScreen

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            ContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
                    
                    LaunchedEffect(Unit) {
                        contactsPermissionState.launchPermissionRequest()
                    }
                    ContactsScreen(permissionState = contactsPermissionState)
                }
            }
        }
    }
} 