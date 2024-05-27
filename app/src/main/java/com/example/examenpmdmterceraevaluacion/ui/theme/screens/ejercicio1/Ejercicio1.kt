package com.example.examenpmdmterceraevaluacion.ui.theme.screens.ejercicio1

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examenpmdmterceraevaluacion.ui.theme.entities.Producto
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.Ejercicio1ViewModel
import com.example.examenpmdmterceraevaluacion.ui.theme.viewmodel.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppPpalEj1(navController: NavController,viewModelLogin: LoginViewModel,viewModelEjercicio1:Ejercicio1ViewModel){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuario: ${viewModelLogin.username}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {viewModelEjercicio1.showAddDialog.value = true}) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir")
                    }
                    IconButton(onClick = {
                        if (viewModelEjercicio1.productosSeleccionados.value.size == 0){
                            viewModelEjercicio1.showNoHayProductosSeleccionados.value = true
                        }else  viewModelEjercicio1.showSeleccionadosDialog.value = true


                    }) {
                        Icon(Icons.Default.ShoppingCart,contentDescription = "Productos Seleccionados")
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Objetos seleccionados:  ${viewModelEjercicio1.contadorProductos.value}")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {

            //Mostramos EDITAR2
            if(viewModelEjercicio1.showEditar2.value){
                editar2(viewModel = viewModelEjercicio1)
            }

            if (viewModelEjercicio1.showNoHayProductosSeleccionados.value){
                NohayProductosDialog(
                    viewModel = viewModelEjercicio1,
                    onDismiss = {viewModelEjercicio1.showNoHayProductosSeleccionados.value = false})
            }


            Lista(
                navController = navController,
                viewModel = viewModelEjercicio1,
                productos = viewModelEjercicio1.listaProductos,
                productosSeleccionados = viewModelEjercicio1.productosSeleccionados.value,
                onProductoSeleccionadoChange = { producto,seleccionado ->
                    viewModelEjercicio1.onProductoSeleccionadoChange(producto,seleccionado)
                }
            )

            //EDITAR CON ALERT DIALOG
            if (viewModelEjercicio1.showDialog.value) {
                /*EditarProductoDialog(
                    viewModel = viewModelEjercicio1,
                    onDismiss = { viewModelEjercicio1.showDialog.value = false }
                )*/

            }

            if (viewModelEjercicio1.showAddDialog.value) {
                AnadirProductoDialog(
                    viewModel = viewModelEjercicio1,
                    onDismiss = { viewModelEjercicio1.showAddDialog.value = false }
                )
            }

            if(viewModelEjercicio1.showSeleccionadosDialog.value){
                MostrarProductosSeleccionados(
                    viewModel = viewModelEjercicio1,
                    onDismiss = {viewModelEjercicio1.showSeleccionadosDialog.value = false}
                )
            }


        }

    }

}



@Composable
fun MostrarProductosSeleccionados(
    viewModel: Ejercicio1ViewModel,
    onDismiss: () -> Unit,
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Productos Seleccionados") },
        text = {
            Column {
                viewModel.productosSeleccionados.value.forEach { producto ->
                    Text(text = "${producto.nombre} - ${producto.precio}€")
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

@Composable
fun Lista(
    navController: NavController,
    viewModel: Ejercicio1ViewModel,
    productos: List<Producto>,
    productosSeleccionados : List<Producto>,
    onProductoSeleccionadoChange: (Producto,Boolean) -> Unit,
){
    LazyColumn(){
        items(productos) { producto ->
            val isSelected = productosSeleccionados.contains(producto)
            Componente(
                navController = navController,
                viewModel = viewModel,
                producto = producto,
                isSelected = isSelected,
            ) { seleccionado ->
                onProductoSeleccionadoChange(producto, seleccionado)
            }
        }
    }

}


@Composable
fun Componente(
    navController: NavController,
    viewModel: Ejercicio1ViewModel,
    producto: Producto,
    isSelected: Boolean,
    onCheckedChange: (Boolean) ->Unit,

){
   Row(
       Modifier
           .fillMaxWidth()
           .padding(10.dp)
           .border(BorderStroke(2.dp, Color.Black))
   ) {

       Column(
           Modifier
               .width(100.dp)
               .padding(10.dp)
       ) {
           Text(
               text = producto.nombre,
               modifier = Modifier
                   .padding(start = 16.dp))
           Text(
               text = "${producto.precio}",
               modifier = Modifier.padding(start = 16.dp))
       }
       
       Column(
           Modifier
               .padding(10.dp)
       ) {
           Row {
               Checkbox(
                   checked = isSelected,
                   onCheckedChange = onCheckedChange,
                   modifier = Modifier
                       .padding(start = 16.dp)
               )
               IconButton(onClick = {
                   viewModel.productoAEditar.value = producto
                   viewModel.showDialog.value = true
                   viewModel.eliminarProductoSeleccionado(producto)

                   //Editar2
                   viewModel.showEditar2.value = true
               }) {
                   Icon(Icons.Default.Edit, contentDescription = "Editar")
               }

               IconButton(onClick = {
                   viewModel.eliminarProducto(producto)
               }) {
                   Icon(Icons.Default.Delete, contentDescription = "Eliminar")
               }
           }
       }
    }

}

@Composable
fun editar2(
    viewModel: Ejercicio1ViewModel) {
    val producto = viewModel.productoAEditar.value
    if (producto != null) {
        val nombre by viewModel.nombre
        val precio by viewModel.precio

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nombre,
                onValueChange = { viewModel.nombre.value = it },
                label = { Text("Nombre") }
            )
            TextField(
                value = precio,
                onValueChange = { viewModel.precio.value = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Button(onClick = {

                val precioDouble = precio.toDoubleOrNull() ?: producto.precio
                viewModel.actualizarProducto(nombre, precioDouble)
                viewModel.nombre.value = "";
                viewModel.precio.value = "";
            }) {
                Text("Editar")
            }
        }
    }
}


@Composable
fun EditarProductoDialog(
    viewModel: Ejercicio1ViewModel,
    onDismiss: () -> Unit) {
    val producto = viewModel.productoAEditar.value
    if (producto != null) {
        val nombre by viewModel.nombre
        val precio by viewModel.precio
        //var nombre by remember { mutableStateOf(producto.nombre) }
        //var precio by remember { mutableStateOf(producto.precio.toString()) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Editar Producto") },
            text = {
                Column {
                    TextField(
                        value = nombre,
                        onValueChange = { viewModel.nombre.value = it },
                        label = { Text("Nombre") }
                    )
                    TextField(
                        value = precio,
                        onValueChange = { viewModel.precio.value = it },
                        label = { Text("Precio") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val precioDouble = precio.toDoubleOrNull() ?: producto.precio
                    viewModel.actualizarProducto(nombre, precioDouble)
                    onDismiss()
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun AnadirProductoDialog(viewModel: Ejercicio1ViewModel, onDismiss: () -> Unit) {
    viewModel.nombre.value = ""
    var precio by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Añadir Producto") },
        text = {
            Column {
                TextField(
                    value = viewModel.nombre.value,
                    onValueChange = { viewModel.nombre.value = it },
                    label = { Text("Nombre") }
                )
                TextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val precioDouble = precio.toDoubleOrNull()
                if (precioDouble != null) {
                    viewModel.anadirProducto(viewModel.nombre.value, precioDouble)

                }
                onDismiss()
            }) {
                Text("Añadir")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun NohayProductosDialog(
    viewModel: Ejercicio1ViewModel,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "No hay productos seleccionados, selecciona algun producto") },
        text = {},
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}
