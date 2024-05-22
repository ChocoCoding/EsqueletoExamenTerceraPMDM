package com.example.examenpmdmterceraevaluacion.ui.theme.screens

sealed class Screens(val route: String) {

    object Menu: Screens("initial_screen")
    object Login: Screens("login")
    object Ejercicio1: Screens("ejExamen1")
    object Ejercicio2: Screens("ejExamen2")
    object Registrar: Screens("registrar")
    object Editar: Screens("editar")
}