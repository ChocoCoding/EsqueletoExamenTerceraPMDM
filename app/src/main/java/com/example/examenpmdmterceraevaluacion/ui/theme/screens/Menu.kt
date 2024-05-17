package com.example.examenpmdmterceraevaluacion.ui.theme.screens

import android.view.Menu
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {navController.navigate(route = Screens.Login.route)}) {
                Text(text = "Login")
        }
        Button(onClick = {navController.navigate(route = Screens.Ejercicio2.route)}) {
            Text(text = "Ejercicio2")
        }
    }



}