package com.example.medquei.ui.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.medquei.MainViewModel
import com.example.medquei.R
import com.example.medquei.db.fb.FBDatabase
import com.example.medquei.model.Medication
import com.example.medquei.ui.components.IconImage


@Composable
fun MedicationListPage(
    viewModel: MainViewModel,
    context: Context,
    fbDB : FBDatabase
) {
    val medicationList = viewModel.medications
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(medicationList) {medication ->
            MedicationItem(medication = medication,
                viewModel = viewModel,
                onClick = {
                          Toast.makeText(context, medication.name, Toast.LENGTH_LONG).show()
                },
                onClose = {
                    fbDB.remove(medication)
                    Toast.makeText(context, medication.name + " removido", Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}

@Composable
fun MedicationItem(
    medication: Medication,
    onClick : () -> Unit,
    onClose : () -> Unit,
    viewModel: MainViewModel
) {
    val imgUrl by viewModel.getImageUrl(medication.image!!).observeAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        //IconImage()
        AsyncImage(
            model = imgUrl, // URL da imagem do Firebase Storage
            modifier = Modifier.size(75.dp),
            error = painterResource(id = R.drawable.pill), // Placeholder para erros
            contentDescription = "Imagem do Medicamento"
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier,
                text = medication.name,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier,
                text = medication.date.toString(),
                fontSize = 16.sp
            )
        }
        IconButton(onClick =  onClose ) {
            Icon(Icons.Filled.Delete, contentDescription = "Deletar")
        }
    }
}
