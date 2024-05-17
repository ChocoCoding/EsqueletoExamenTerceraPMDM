package com.example.examenpmdmterceraevaluacion.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio1ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio2ViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppPpalEj2(navController: NavController, viewModel: Ejercicio2ViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {}
            )
        },
        bottomBar = {
            BottomAppBar {}
        }
    ) {

    }

}