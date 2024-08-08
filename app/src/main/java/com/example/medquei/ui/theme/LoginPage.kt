package com.example.medquei.ui.theme

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medquei.HomeActivity
import com.example.medquei.R
import com.example.medquei.RegisterActivity

@Preview(showBackground = true)
@Composable
fun LoginPage() {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.medquei_logo),
            contentDescription = "Logo Medquei",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu E-mail") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.padding(12.dp))
        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite a Senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.padding(12.dp))
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF125451),
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                activity?.startActivity(
                    Intent(activity, HomeActivity::class.java).setFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
        ) {
            Text(
                text = "Entrar",
                color = Color(0xFFE9F6FE),
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = "Não tem uma conta?")
        Spacer(modifier = Modifier.padding(12.dp))
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE9F6FE),
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                activity?.startActivity(
                    Intent(activity, RegisterActivity::class.java).setFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
        ) {
            Text(
                text = "Crie sua Conta",
                color = Color(0xFF125451),
                fontSize = 16.sp
            )
        }
    }

}