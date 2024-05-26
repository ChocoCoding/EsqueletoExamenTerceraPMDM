package com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.User
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.getListaUsuarios
import java.io.File

class LoginViewModel : ViewModel(){
    var username by mutableStateOf("");
    var password by mutableStateOf("");
    var intentos by mutableIntStateOf(3)

    var showDialogIntentos = mutableStateOf(false)

    var users = getListaUsuarios().toMutableList()

    fun addUser(user: User) {
        users = (users + user).toMutableList()
    }


    //GUARDAR USUARIOS
    private val FILENAME = "contactos.txt"
    //fichero en AVD en ubicación-> View-> Tool Windows -> Device Explorer /data/user/0/com.example.NombreApp/files/items.txt-
    fun guardarUsuario(context: Context, nombre: String, contrasena: String) {
        val file = File(context.filesDir, FILENAME)
        file.appendText("$nombre,$contrasena\n")
    }

}