package com.example.medquei.ui.pages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.medquei.MainViewModel
import com.example.medquei.db.fb.FBDatabase

@Composable
fun CalendarPage(
    viewModel: MainViewModel,
    context: Context,
    fbDB : FBDatabase
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Meus Próximos Medicamentos",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF125451),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        MedicationListPage(viewModel = viewModel, context = context, fbDB = fbDB)
    }
}