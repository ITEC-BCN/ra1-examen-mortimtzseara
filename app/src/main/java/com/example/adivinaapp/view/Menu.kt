package com.example.adivinaapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adivinaapp.R
import com.example.adivinaapp.Routes

@Composable
fun MenuScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(color = LightGray),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Adivina el número by Morti Martínez-Seara",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(15.dp))
            Image(
                painter = painterResource (id = R.drawable .ic_game),
                contentDescription = "Image" ,
                modifier = Modifier.size(80.dp)
            )
            Button(onClick = {navController.navigate(Routes.ScreenGame.routes)},
                modifier = Modifier.fillMaxWidth()) {
                Text("Iniciar partida")
            }
        }
    }
}