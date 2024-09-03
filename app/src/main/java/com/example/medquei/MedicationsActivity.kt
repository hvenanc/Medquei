package com.example.medquei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.medquei.ui.theme.MedicationListPage
import com.example.medquei.ui.theme.MedqueiAPPTheme
import com.example.medquei.ui.topbar.AddAppBar

class MedicationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val viewModel: MainViewModel by viewModels()
            MedqueiAPPTheme {
               Scaffold(
                   topBar = { AddAppBar("Meus Medicamentos", HomeActivity::class.java)},
                   content = {padding ->
                       Surface(
                           modifier = Modifier.padding(padding),
                       ) {
                            MedicationListPage(viewModel = viewModel, context = context)
                       }
                   }
               )
            }
        }
    }
}