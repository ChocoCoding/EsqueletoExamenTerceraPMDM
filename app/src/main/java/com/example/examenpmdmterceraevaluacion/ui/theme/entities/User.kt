package com.example.examenpmdmterceraevaluacion.ui.theme.entities

import androidx.compose.runtime.mutableStateListOf

data class User(val username: String,val password: String) {




}

fun getListaUsuarios():List<User>{
    val  listaUsuarios : MutableList<User> = mutableStateListOf(
        User("u1", "u1"),
        User("u2", "u2"),
        User("u3", "u3")

    )
    return listaUsuarios;
}