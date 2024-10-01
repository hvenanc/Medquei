package com.example.medquei.ui.components

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.medquei.R
import com.google.firebase.storage.FirebaseStorage

@Composable
fun ImageUpload() {
    var showProgessDialog by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? ->
        imageUri = uri
    }
    val context = LocalContext.current
    val painter: Painter = if(imageUri != null) {
        rememberAsyncImagePainter(imageUri)
    } else {
        painterResource(id = R.drawable.pill)
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                launcher.launch("image/*")
            }
    )
    Button(onClick = {
        if(imageUri != null) {
            imageUri.let { uri ->
                showProgessDialog = true
                uploadToFirebase(imageUri, context) {
                    showProgessDialog = false
                }
            }
        }
        else {
            Toast.makeText(context, "Insira uma Imagem", Toast.LENGTH_SHORT).show()
        }
    })
    {
        Text(text = "")
    }
    if (showProgessDialog == true) {
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 4.dp,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .size(50.dp)
        )
    }
}

fun uploadToFirebase(uri: Uri?, context: Context, onComplete: () -> Unit) {
    val storage = FirebaseStorage.getInstance()
    val storageReference = storage.reference
    val imageReference = storageReference.child("images/" + uri!!.lastPathSegment)

    val uploadTask = uri.let {imageReference.putFile(it)}

    uploadTask.addOnSuccessListener {
        onComplete()
        Toast.makeText(
            context,
            "Imagem carregada com sucesso",
            Toast.LENGTH_LONG
        ).show()
    }.addOnFailureListener{
        onComplete()
        Toast.makeText(
            context,
            "Falha ao carregar imagem",
            Toast.LENGTH_LONG
        ).show()
    }
}
