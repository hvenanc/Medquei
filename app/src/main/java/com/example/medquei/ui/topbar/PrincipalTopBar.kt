package com.example.medquei.ui.topbar

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.medquei.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun principalTopBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            Row {
                IconButton(
                    onClick = {
                        // Ação para o botão de notificação
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificações",
                        tint = Color(0xFF125451)
                    )
                }
                IconButton(
                    onClick = {
                        // Ação para o botão de configurações
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configurações",
                        tint = Color(0xFF125451)
                    )
                }
            }
        },
        actions = {
            val context = LocalContext.current
            IconButton(
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    Firebase.auth.signOut()
                    context.startActivity(intent)
                    (context as? Activity)?.finish()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Localized description",
                    tint = Color(0xFF125451)
                )
            }
        }
    )
}