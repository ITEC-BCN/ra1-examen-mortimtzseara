package com.example.adivinaapp

sealed class Routes (val routes: String){
    object ScreenMenu: Routes("ScreenMenu")
    object ScreenGame: Routes("ScreenGame")
}