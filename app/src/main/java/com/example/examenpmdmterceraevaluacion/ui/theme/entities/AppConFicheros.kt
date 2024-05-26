package com.example.examenpmdmterceraevaluacion.ui.theme.entities

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable


data class ItemSer(val nombre: String, val descr: String) : Serializable

fun serializarObjetoFich(objeto: Any, archivo: File) {
    ObjectOutputStream(FileOutputStream(archivo)).use { it.writeObject(objeto) }
}

fun deserializarObjetoFich(archivo: File): Any? {
    return ObjectInputStream(FileInputStream(archivo)).use { it.readObject() }
}


data class Item(val nombre: String, val descr: String)

val items = mutableStateListOf<Item>()//usando .txt

val itemsSer = mutableStateListOf<ItemSer>()//usando .dat

fun grabarCambiosFich(context: Context) {
    val file = File(context.filesDir, "items.txt")
    file.delete()
    for (item in items) {
        file.appendText("${item.nombre}\n${item.descr}\n")
    }
}

fun leerDatosFich(context: Context) {
    val file = File(context.filesDir, "items.txt")
    if (file.exists()) {
        //fichero en AVD en ubicación-> View-> Tool Windows -> Device Explorer /data/user/0/com.example.NombreApp/files/items.txt->a ver en Divice Explorer
//        Toast.makeText(context,"el fichero está en->"+file.path, Toast.LENGTH_SHORT).show()
        val lista = file.readLines()
        var indice = 0
        while (indice < lista.size) {
            val itemNew = Item(lista.get(indice), lista.get(indice + 1))
            val itemNewSer = ItemSer(lista.get(indice), lista.get(indice + 1))

            if (!items.contains(itemNew)) {
                items.add(itemNew)

                itemsSer.add(itemNewSer)
            }
            indice += 2
        }
    }
    else {
        Toast.makeText(context,"el fichero NO existe->"+file.path, Toast.LENGTH_SHORT).show()
    }
}

fun leerDatos_Fichdat(context: Context){
    val archivo = File(context.filesDir, "items.dat")

    val input = ObjectInputStream(archivo.inputStream())

    input.use {
        // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección
//        val itemsLeidos = it.readObject() as List<ItemSer>
//        itemsSer.addAll(itemsLeidos)
        // podría leer uno a uno
         while (it.available() > 0) {
             val item = it.readObject() as ItemSer
             itemsSer.add(item)
         }
    }
}

fun grabarCambios_Fichdat(context: Context) {
    val archivo = File(context.filesDir, "items.dat")
    archivo.delete()
    for (item in itemsSer) {
        // Serializar objeto
        serializarObjetoFich(item, archivo)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreenAppFich() {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Items a Fichero .tx y .dat") })
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                androidx.compose.material3.Text(text = "Listado-numEltos-")
                androidx.compose.material3.Text(text = items.size.toString())
            }
        }
    ) {// (1)
        Column(
            modifier = Modifier.fillMaxSize().padding(it) // (3)
            ,
            verticalArrangement = Arrangement.Top
        ) {

            AdministrarItems()
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun AppConFichsScreen() {

    ScaffoldScreenAppFich()
}


@Composable
fun AdministrarItems() {
    val activity = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var descr by remember { mutableStateOf("") }

    Column(

    ) {
        OutlinedTextField(value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de item") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        OutlinedTextField(value = descr,
            onValueChange = { descr = it },
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Button(onClick = {
            val nuevoItem = Item(nombre, descr)
            items.add(nuevoItem)

            val nuevoItemSer = ItemSer(nombre, descr)
            itemsSer.add(nuevoItemSer)

            val path = activity.getFilesDir()

            //.txt
            val file = File(path, "items.txt")
            file.appendText("${nombre}\n${descr}\n")

            //.dat
            val archivo = File(path,"items.dat")
            // Serializar objeto
            serializarObjetoFich(nuevoItemSer, archivo)

            nombre = ""
            descr = ""
        }, modifier = Modifier.padding(5.dp)) {
            Text(text = "Agregar", /*modifier = Modifier.fillMaxWidth()*/)
        }

        val file = File(activity.filesDir, "items.txt")
        if (file.exists()) {

            leerDatosFich(activity)//uso de archivos .txt,.dat para persistir datos
            leerDatos_Fichdat(activity)//uso .dat

            LazyColumn() {
                itemsIndexed(items) { indice, item ->
                    MostrarItem(
                        indice,
                        item,
                        editNombre = { nombre = it },
                        editDescr = { descr = it })
                }
            }
        }
    }
}

@Composable
fun MostrarItem(indice: Int, item: Item, editNombre:(String)->Unit, editDescr:(String)->Unit) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,//hace que ocupen todo el espacio horizontal
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(

        ){
            Text(text = "Nombre-> "+item.nombre)
            Text(text = "Descr->"+item.descr)
        }
        Column(

        ) {
            Image(painter = painterResource(id = android.R.drawable.ic_delete),
                contentDescription = "",
                modifier = Modifier.clickable {
                    items.removeAt(indice)

                    itemsSer.removeAt(indice)

                    grabarCambiosFich(context)//usando .txt
                    grabarCambios_Fichdat(context)
                })
        }
        Column(

        ){
            Image(painter = painterResource(id = android.R.drawable.ic_menu_edit),
                contentDescription = "",
                modifier = Modifier.clickable {

                    Toast.makeText(context, "Modifica Datos Contacto seleccionado.", Toast.LENGTH_SHORT).show()

                    items.removeAt(indice)
                    itemsSer.removeAt(indice)

                    grabarCambiosFich(context)//usando .txt
                    grabarCambios_Fichdat(context)//usando .dat

                    editNombre(item.nombre)
                    editDescr(item.descr)

                })
        }

    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .width(4.dp), color = Color.Black
    )
}

