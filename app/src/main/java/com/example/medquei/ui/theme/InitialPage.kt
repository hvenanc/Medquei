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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medquei.LoginActivity
import com.example.medquei.R
import com.example.medquei.RegisterUserActivity

@Preview(showBackground = true)
@Composable
fun InitialPage(modifier: Modifier = Modifier) {

    val activity = LocalContext.current as? Activity

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.medquei_logo),
            contentDescription = "Logo Medquei",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.size(48.dp))
        Text(
            text = "Mantenha o controle de seus medicamentos!\n" +
                    "Crie lembretes para tomar seus medicamentos e reabastecer prescrições médicas. Acompanhe o histórico de uso de medicações e receba notificações para consultas médicas.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(48.dp))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF125451),
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                activity?.startActivity(
                    Intent(activity, LoginActivity::class.java).setFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
        ) {
            Text(
                text = "Login",
                color = Color(0xFFE9F6FE),
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE9F6FE)

            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                activity?.startActivity(
                    Intent(activity, RegisterUserActivity::class.java).setFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
        ) {
            Text(
                text = "Criar Conta",
                color = Color(0xFF125451),
                fontSize = 16.sp,
            )
        }
    }
}