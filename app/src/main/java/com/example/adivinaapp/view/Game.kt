package com.example.adivinaapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adivinaapp.Routes


@Composable
fun GameScreen(navController: NavController) {
    var secretNumber = createRandomNumber()
    var playerLife by rememberSaveable {mutableStateOf(1f)}
    var playerInput by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf(true) }
    var playerList by remember {mutableStateOf<List<Int>>(emptyList())}
    var isCorrectNumber by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(color = LightGray),
        contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            textSecretNumber(isCorrectNumber, secretNumber)

            LinearProgressIndicator (
                progress = {playerLife},
                color = Red,
                modifier = Modifier.height(10.dp).width(275.dp)
                )

            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
            value = playerInput,
            onValueChange = {
                if (it.isEmpty() ||it.toInt() in 1..10) playerInput = it
            },
            label = { Text(text = "Introdueix un número (1-10)") }
            )

            Button(onClick = {if(playerInput.isEmpty()){
               isCorrect = false
            }
            else if(playerInput.toInt() == secretNumber) {
                isCorrectNumber = true
                isCorrect = true
                playerLife -= 0.112f
            }
            else{
                isCorrect = true
                playerLife -= 0.112f
            }

            }) {
                Text("Comprovar")
            }

            textError(secretNumber, isCorrect, playerInput, playerLife)

            Text("Números erronis: ")
            for(numbers: Int in playerList){
                print(numbers)
            }
            Spacer(modifier = Modifier.padding(30.dp))

            Row{
                Button(onClick = {
                    secretNumber = createRandomNumber()
                    playerLife = 1f
                    playerList = emptyList()
                    playerInput = ""
                }) {
                    Text("Torna a jugar")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = {navController.navigate(Routes.ScreenMenu.routes)}) {
                    Text("Menú principal")
                }
            }
        }
    }
}


fun createRandomNumber(): Int {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    return numbers.shuffled().get(0)
}
@Composable
fun textError(secretNumber: Int, isCorrect: Boolean, playerInput: String, playerLife: Float){
    if(!isCorrect){
        Text("Has d'escriure un número", color = Red)
    }
    else if(playerLife <= 0f){
        Text("Se t'han acabat les oportunitats... :(")
    }
    else if(playerInput == secretNumber.toString()){
        Text("Enhorabona! Has acertat el número!", color = Green)
    }
}
@Composable
fun textSecretNumber(isCorrectNumber: Boolean, secretNumber: Int){
    if(!isCorrectNumber) Text("?",
            fontSize = 25.sp)
    else Text("$secretNumber")
}