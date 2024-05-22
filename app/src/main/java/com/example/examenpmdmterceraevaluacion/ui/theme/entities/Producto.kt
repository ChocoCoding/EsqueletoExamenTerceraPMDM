package com.example.examenpmdmterceraevaluacion.ui.theme.entities

import androidx.compose.runtime.mutableStateListOf

data class Producto (val nombre: String, val precio: Double ){



}



fun getListaProductos():List<Producto> {
    val listaProductos: MutableList<Producto> = mutableStateListOf(
        Producto("Caja", 500.0),
        Producto("Monitor", 200.0),
        Producto("Teclado", 50.0),
        Producto("Raton", 30.0),
        Producto("placa", 100.0)
    )
    return listaProductos;
}