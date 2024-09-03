package com.example.medquei.ui.theme

import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medquei.HomeActivity
import com.example.medquei.MainViewModel
import com.example.medquei.MedicationsActivity
import com.example.medquei.db.fb.FBDatabase
import com.example.medquei.model.Medication
import com.example.medquei.ui.topbar.AddAppBar
import java.util.Calendar

@Preview(showBackground = true)
@Composable
fun RegisterMedicationPage() {

    Scaffold(
        topBar = { AddAppBar("Registre seu Medicamento", HomeActivity::class.java)},
        content = {padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                Register()
            }
        }
    )
}

@Composable
fun Register() {
    var medicationImage by remember { mutableStateOf<Painter?>(null) }
    var medicationName by rememberSaveable { mutableStateOf("") }
    var dosage by rememberSaveable { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("") }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel = MainViewModel()
    val fbDB = remember { FBDatabase (viewModel) }


    if (showDatePickerDialog) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                showDatePickerDialog = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Image Picker
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .clickable { /* Handle image selection */ }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (medicationImage != null) {
                Image(painter = medicationImage!!, contentDescription = "Imagem da Medicação")
            } else {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Selecionar Imagem",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        // Medication Name
        OutlinedTextField(
            value = medicationName,
            label = { Text(text = "Nome") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { medicationName = it }
        )

        Spacer(modifier = Modifier.padding(12.dp))

        // Dosage
        OutlinedTextField(
            value = dosage,
            label = { Text(text = "Dosagem") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { dosage = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(12.dp))

        // Date Picker
        OutlinedButton(
            onClick = { showDatePickerDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (selectedDate.isEmpty()) "Dias previstos para o Tratamento" else selectedDate)
        }

        Spacer(modifier = Modifier.padding(12.dp))

        ElevatedButton(
            onClick = {
                      val medication = Medication(
                          name = medicationName,
                          dosage = dosage.toDouble(),
                          image = null,
                          hour = null,
                          date = selectedDate
                      )
                fbDB.add(medication)
                Toast.makeText(context, "Medicação Registrada", Toast.LENGTH_LONG).show()
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125451)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Salvar",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Salvar",
                    tint = Color.White
                )
            }
        }

    }
}
