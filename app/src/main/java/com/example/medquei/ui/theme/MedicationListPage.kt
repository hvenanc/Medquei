package com.example.medquei.ui.theme

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medquei.RegisterMedicationActivity
import com.example.medquei.ui.nav.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationListPage() {
    // Dados de exemplo, substitua pelos seus dados reais
    val sampleMedications = listOf("Paracetamol", "Ibuprofeno", "Amoxicilina")

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(sampleMedications.size) { index ->
            Text(
                text = sampleMedications[index],
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
