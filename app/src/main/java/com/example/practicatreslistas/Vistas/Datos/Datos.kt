package com.example.practicatreslistas.Vistas.Datos

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.practicatreslistas.Pokemon.Datos
import com.example.practicatreslistas.Pokemon.Pokemon
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatosPokemon(navController: NavHostController){
    var texto by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var gimnasioJugador by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var gimnasioEnemigo by remember { mutableStateOf("") }
    var victorias by remember { mutableStateOf("") }
    var movimientoSlider by remember { mutableFloatStateOf(0f) }
    var DisminuirPiker by remember { mutableStateOf(false) }
    val opcionesEquipo = Datos().DatosGimnasio()
    val opcionesJugador = Datos().DatosPokemones()
    var expandir by remember { mutableStateOf(false) }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Introduce el nombre del Pokemon:")
        lista(lista = opcionesJugador, recogerTexto = {nuevoValor -> nombre = nuevoValor})
        Text(text = "Introduce el equipo en el que juega:")
        lista(lista = opcionesEquipo, recogerTexto = {nuevoValor -> gimnasioJugador = nuevoValor})
        Text(text = "Selecciona una o mas posiciones")
        radioBoton(Posicion = "Fuego", { nuevoValor -> tipo = nuevoValor } , textoTotalPos = tipo)
        radioBoton(Posicion = "Agua", { nuevoValor -> tipo = nuevoValor } , textoTotalPos = tipo)
        radioBoton(Posicion = "Lucha", { nuevoValor -> tipo = nuevoValor } , textoTotalPos = tipo)
        radioBoton(Posicion = "Planta", { nuevoValor -> tipo = nuevoValor } , textoTotalPos = tipo)
        tipo = tipo.trim()
        Text(text = "El numero de victorias contra otros Pokemones")
        Slider(value = movimientoSlider,
            onValueChange = {movimientoSlider = it},
            valueRange = 0f..5f, steps = 4)
        Text("${movimientoSlider.toInt()}")
        Text(text = "El equipo contra el que jugo:")
        lista(lista = opcionesEquipo, recogerTexto = {nuevoValor -> gimnasioEnemigo = nuevoValor})
        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        DatePicker(state = state,
            modifier = Modifier,
            title = {
                Row {
                    Text(text = "Seleccionar la fecha del combate")
                }
            })
        BackHandler {

            fecha = state.selectedDateMillis?.let { convertirMillisAFecha(it) }.toString()
            victorias = movimientoSlider.toInt().toString()

            Datos().Añadir(nombre = nombre,tipo = tipo,gimnasioJugador= gimnasioJugador,gimnasioEnemigo = gimnasioEnemigo,fecha= fecha,victorias=victorias)
            navController.popBackStack()
        }


    }


}
fun convertirMillisAFecha(millis: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val fecha = Date(millis)
    return sdf.format(fecha)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun lista( lista : ArrayList<String>,recogerTexto : (String)-> Unit){
    var texto by remember { mutableStateOf("") }
    val opciones = lista
    var expandir by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expandir, onExpandedChange = {expandir = !expandir} , modifier = Modifier.fillMaxWidth()) {
        TextField(value = texto,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            label = { Text("Seleccionar opcion") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandir) }
        )
        DropdownMenu(expanded = expandir , onDismissRequest = { expandir = false }) {
            opciones.forEach { elemento ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(),
                    text = { Text(elemento) },
                    onClick = { texto = elemento;recogerTexto(
                        elemento
                    )}

                )
            }

        }
    }


}

@SuppressLint("ComposableNaming")
@Composable
fun radioBoton(Posicion : String,valorPosicion : (String) -> Unit , textoTotalPos : String){
    var Pulsado by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(selected = Pulsado, onClick = { Pulsado = !Pulsado;
            if (Pulsado){
                if (textoTotalPos.contains("$Posicion")){
                    valorPosicion("$textoTotalPos")
                }else{valorPosicion("$textoTotalPos $Posicion")}
            }
            else{
                if (textoTotalPos.contains("$Posicion")){
                    val textoTotalPos = textoTotalPos.replace("$Posicion","")
                    valorPosicion("$textoTotalPos")
                }
            }})
        Text(text = Posicion)
    }
}
