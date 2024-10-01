package com.example.medquei

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.medquei.db.fb.FBDatabase
import com.example.medquei.ui.nav.BottomNavBar
import com.example.medquei.ui.nav.MainNavHost
import com.example.medquei.ui.theme.MedqueiAPPTheme
import com.example.medquei.ui.topbar.principalTopBar

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val fbDB = remember { FBDatabase(viewModel) }
            MedqueiAPPTheme {
                Scaffold(
                    topBar = {
                             principalTopBar()
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
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(
                            navController = navController,
                            viewModel = viewModel, context = context, fbDB = fbDB
                        )
                    }
                }
            }
        }
    }
}