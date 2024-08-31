package com.example.medquei.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
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
