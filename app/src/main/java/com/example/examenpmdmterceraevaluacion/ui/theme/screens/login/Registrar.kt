package com.example.examenpmdmterceraevaluacion.ui.theme.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.User
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.Screens
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.LoginViewModel

@Composable
fun AppRegistrar(navController: NavController,viewModel: LoginViewModel){
    val  context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.username,
            onValueChange = {viewModel.username = it},
            label = { Text("Usuario")},
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.width(200.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (viewModel.username.contains("@") && viewModel.password.length >= 7) {
                    viewModel.addUser(User(viewModel.username, viewModel.password))
                    Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screens.Login.route)
                } else {
                    Toast.makeText(context, "Usuario debe contener '@' y contraseña debe tener mínimo 7 caracteres", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Register")
        }
    }


}