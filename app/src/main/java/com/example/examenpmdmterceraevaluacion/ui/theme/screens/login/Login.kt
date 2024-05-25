package com.example.examenpmdmterceraevaluacion.ui.theme.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
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
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.Screens
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.ejercicio1.MostrarProductosSeleccionados
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.LoginViewModel

@Composable
fun AppLogin(navController: NavController,viewModel: LoginViewModel){
    val context = LocalContext.current

    if(viewModel.showDialogIntentos.value){
        NoHayMasIntentosDialog(
            viewModel = viewModel,
            onDismiss = {viewModel.showDialogIntentos.value = false}
        )
    }

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
            label = { Text("Usuario") },
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = {viewModel.password = it},
            label = {Text("Contraseña")},
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
                        //Toast.makeText(context, "Te has quedado sin intentos", Toast.LENGTH_SHORT).show()
                        viewModel.showDialogIntentos.value = true
                        if(!viewModel.showDialogIntentos.value){
                            navController.navigate(route = Screens.Menu.route)
                            viewModel.intentos = 3;
                        }

                    }else{
                        Toast.makeText(context, "Intentos restantes: ${viewModel.intentos}", Toast.LENGTH_SHORT).show()
                        viewModel.username = ""
                        viewModel.password = ""
                    }
                }
            }) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screens.Registrar.route)
        }) {
            Text("Registrarse")
        }
    }

}

@Composable
fun NoHayMasIntentosDialog(
    viewModel: LoginViewModel,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Te has quedado sin intentos") },
        text = {},
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )


}