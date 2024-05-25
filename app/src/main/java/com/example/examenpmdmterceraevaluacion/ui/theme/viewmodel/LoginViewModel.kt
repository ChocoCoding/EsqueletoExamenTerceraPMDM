package com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.User
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.getListaUsuarios

class LoginViewModel : ViewModel(){
    var username by mutableStateOf("");
    var password by mutableStateOf("");
    var intentos by mutableIntStateOf(3)

    var isValidCredential by mutableStateOf(false)

    var showDialogIntentos = mutableStateOf(false)

    var users = getListaUsuarios().toMutableList()

    /*
    var users by mutableStateOf(
        listOf(User("u1", "u1"),
            User("u2", "u2"),
            User("u3", "u3"))

    )
*/
    fun addUser(user: User) {
        users = (users + user).toMutableList()
    }
}