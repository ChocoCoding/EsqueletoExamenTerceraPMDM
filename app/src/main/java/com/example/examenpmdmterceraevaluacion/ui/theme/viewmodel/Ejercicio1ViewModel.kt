package com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.Producto
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.getListaProductos

class Ejercicio1ViewModel: ViewModel() {

    var listaProductos = getListaProductos().toMutableStateList()
    var productosSeleccionados = mutableStateOf(listOf<Producto>())
    var contadorProductos = mutableIntStateOf(0)
    var seleccionarTodos = mutableStateOf(false)



    //EDITAR
    var productoAEditar = mutableStateOf<Producto?>(null)
    var showDialog = mutableStateOf(false)
    fun actualizarProducto(nombre: String, precio: Double) {
        val productoActual = productoAEditar.value
        productoActual?.let {
            val nuevosProductos = listaProductos
            val index = nuevosProductos.indexOf(it)
            if (index != -1) {
                nuevosProductos[index] = Producto(nombre, precio)
                // Update the state with the new product list
                listaProductos = nuevosProductos
            }
        }
        productoAEditar.value = null // Reset the editing product
    }

    //ELIMINACION
    fun eliminarProducto(producto: Producto) {
        val nuevosProductos = listaProductos
        nuevosProductos.remove(producto)
        listaProductos = nuevosProductos

        val productosSeleccionadosActual = productosSeleccionados.value.toMutableStateList()
        productosSeleccionadosActual.remove(producto)
        productosSeleccionados.value = productosSeleccionadosActual
        contadorProductos.value = productosSeleccionadosActual.size
    }



    //AÑADIR
    var showAddDialog = mutableStateOf(false)

    fun anadirProducto(nombre: String, precio: Double) {
        val nuevoProducto = Producto(nombre, precio)
        listaProductos.add(nuevoProducto)
    }



    fun onProductoSeleccionadoChange(producto: Producto,seleccionado : Boolean){
        val productoSeleccionadoActual = productosSeleccionados.value.toMutableStateList()
        if (seleccionado){
            productoSeleccionadoActual.add(producto)
            contadorProductos.value++;
        }else{
            productoSeleccionadoActual.remove(producto)
            contadorProductos.value--;
        }
            productosSeleccionados.value = productoSeleccionadoActual
    }
    }

