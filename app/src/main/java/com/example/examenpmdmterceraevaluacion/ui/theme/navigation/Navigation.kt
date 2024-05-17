package com.example.examenpmdmterceraevaluacion.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.AppLogin
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.AppPpalEj1
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.AppPpalEj2
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.Menu
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.Screens
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio1ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio2ViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val ejercicio1ViewModel = viewModel<Ejercicio1ViewModel>()
    val ejercicio2ViewModel = viewModel<Ejercicio2ViewModel>()


    NavHost(navController, startDestination = Screens.Menu.route){
        composable(route=Screens.Menu.route){ Menu(navController = navController) }
        composable(route= Screens.Login.route){
            AppLogin(navController = navController, viewModel = ejercicio1ViewModel)
        }
        composable(route = Screens.Ejercicio1.route){
            AppPpalEj1(navController = navController, viewModel = ejercicio1ViewModel)
        }
        composable(route = Screens.Ejercicio2.route){
            AppPpalEj2(navController = navController, viewModel = ejercicio2ViewModel)
        }
    }


}