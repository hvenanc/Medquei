package com.example.medquei

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.medquei.ui.nav.BottomNavBar
import com.example.medquei.ui.nav.MainNavHost
import com.example.medquei.ui.theme.MedqueiAPPTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedqueiAPPTheme {
                val navController = rememberNavController()
                MedqueiAPPTheme {
                    Scaffold(
                        topBar = {
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
                                            finish()
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
                        },
                        bottomBar = {
                            BottomNavBar(navController = navController)
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    val intent = Intent(this@HomeActivity, RegisterMedicationActivity::class.java)
                                    startActivity(intent)
                                },
                                containerColor = Color(0xFF125451)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Adicionar", tint = Color.White)
                            }
                        }
                    ) {
                            innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MainNavHost(navController = navController)
                        }
                    }

                }
            }
        }
    }
}