package com.example.medquei.ui.theme

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medquei.HomeActivity
import com.example.medquei.RegisterActivity


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar(title: String, activityClass: Class<out Activity>) {
    val activity = LocalContext.current as? Activity
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, color = Color(0xFFE9F6FE))
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                activity?.startActivity(
                    Intent(activity, activityClass).setFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color(0xFFE9F6FE),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF125451)),
    )
}
