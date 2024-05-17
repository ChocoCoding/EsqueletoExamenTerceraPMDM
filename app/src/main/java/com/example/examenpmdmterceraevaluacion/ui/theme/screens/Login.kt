package com.example.examenpmdmterceraevaluacion.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio1ViewModel

@Composable
fun AppLogin(navController: NavController,viewModel: Ejercicio1ViewModel){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.username,
            onValueChange ={viewModel.username = it},
            label = { Text("Username") },
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = {viewModel.password = it},
            label = {Text("Password")},
            modifier = Modifier.width(200.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val isValidCredential = viewModel.users.any{it.username == viewModel.username && it.password == viewModel.password}
                if (isValidCredential){
                    navController.navigate(Screens.Ejercicio1.route)
                }else{
                    viewModel.intentos--
                    if(viewModel.intentos <= 0){
                        Toast.makeText(context, "Te has quedado sin intentos", Toast.LENGTH_SHORT).show()
                        navController.navigate(route = Screens.Menu.route)
                        viewModel.intentos = 3;
                    }else{
                        Toast.makeText(context, "Intentos restantes: ${viewModel.intentos}", Toast.LENGTH_SHORT).show()
                        viewModel.username = ""
                        viewModel.password = ""
                    }
                }
            }) {
            Text("Log in")
        }


    }

}