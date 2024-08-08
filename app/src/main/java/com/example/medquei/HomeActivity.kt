package com.example.medquei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.medquei.ui.nav.BottomNavBar
import com.example.medquei.ui.nav.MainNavHost
import com.example.medquei.ui.theme.MedqueiAPPTheme

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
                                title = { Text(text = "Medquei", color = Color(0xFF125451)) },
                                actions = {
                                    IconButton( onClick = { finish() } ) {
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
                            FloatingActionButton(onClick = { }) {
                                Icon(Icons.Default.Add, contentDescription = "Adicionar")
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