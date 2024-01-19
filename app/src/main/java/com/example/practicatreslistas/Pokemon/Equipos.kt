package com.example.practicatreslistas.Pokemon

import androidx.compose.ui.graphics.Color
import com.example.practicatreslistas.R

class Datos {
    var Gimnasios = DatosGimnasio()
    var Pokemones = DatosPokemones()
    fun Añadir(
        nombre: String, tipo: String,
        gimnasioJugador: String,


        gimnasioEnemigo: String,
        fecha: String ,victorias: String
    ) {
        var temp = Pokemon(nombre, tipo, gimnasioJugador, gimnasioEnemigo, fecha, victorias)
        ArrayBox.add(temp)
        println("  " + ArrayBox.size + "inside")
    }
    fun DatosGimnasio() : ArrayList<String>{
        var temp = ArrayList<String>()
        temp.add("Gimnasio Roca de Pueblo Plateado");
        temp.add("Gimnasio Agua de Ciudad Celeste");
        temp.add("Gimnasio Eléctrico de Ciudad Parque");
        temp.add("Gimnasio Psíquico de Ciudad Azafrán");
        temp.add("Gimnasio Planta de Ciudad Azulona");
        temp.add("Gimnasio Lucha de Ciudad Azafrán");
        temp.add("Gimnasio Volador de Ciudad Celeste");
        temp.add("Gimnasio Hielo de Ciudad Pardal");
        temp.add("Gimnasio Dragón de Ciudad Algaria");
        temp.add("Gimnasio Siniestro de Ciudad Teja");
        return temp

    }
    fun DatosPokemones() : ArrayList<String>{
        var temp = ArrayList<String>()
        temp.add("Charmander");
        temp.add("Machamp");
        temp.add("Electabuzz");
        temp.add("Rapidash");
        temp.add("Chansey");
        temp.add("Shellder");
        temp.add("Beedrill");
        temp.add("Hypno");
        temp.add("Rhydon");
        temp.add("Exeggutor");
        temp.add("Vileplume");
        temp.add("Tentacruel");

        return temp
    }
    fun obtenerColorEquipo(nombreEquipo: String): Color {
        return when (nombreEquipo) {

            "Gimnasio Roca de Pueblo Plateado" -> Color(0xFF462007) // Granate
            "Gimnasio Agua de Ciudad Celeste" -> Color(0xFF1555A3) // Rojo
            "Gimnasio Eléctrico de Ciudad Parque" -> Color(0xFFFFD100) // Amarillo
            "Gimnasio Psíquico de Ciudad Azafrán" -> Color(0xFF9000A0) // Azul
            "Gimnasio Planta de Ciudad Azulona" -> Color(0xFF37FF00) // Amarillo
            "Gimnasio Lucha de Ciudad Azafrán" -> Color(0xFFF00000) // Verde
            "Gimnasio Volador de Ciudad Celeste" -> Color(0xFF62F5E4) // Rojo
            "Gimnasio Hielo de Ciudad Pardal" -> Color(0xFF4D7EBB) // Naranja
            "Gimnasio Dragón de Ciudad Algaria" -> Color(0xFFCE822F) // Azul
            "Gimnasio Siniestro de Ciudad Teja" -> Color(0xFF96A3A8) // Celeste
             else -> Color.Gray // Color por defecto si el equipo no tiene asignado un color
        }
    }
    fun DatosImagen(nombre : String):Int {
        val Valor = when{
            nombre == "Charmander" -> R.drawable.charmander1600x1200
            nombre == "Machamp" -> R.drawable.machamp1600x1200
            nombre == "Electabuzz" -> R.drawable.electabuzz1600x1200
            nombre == "Rapidash" -> R.drawable.rapidash1600x1200
            nombre == "Chansey" -> R.drawable.chansey1600x1200
            nombre == "Shellder" -> R.drawable.shellder1600x1200
            nombre == "Beedrill" -> R.drawable.beedrill1600x1200
            nombre == "Hypno" -> R.drawable.hypno1600x1200
            nombre == "Rhydon" -> R.drawable.rhydon1600x1200
            nombre == "Exeggutor" -> R.drawable.exeggutor1600x1200
            nombre == "Vileplume" -> R.drawable.vileplume1600x1200
            nombre == "Tentacruel" -> R.drawable.tentacruel1600x1200
            else -> 0
        }
        return Valor
    }
    fun DatosTexto(nombre: String): Int {
        val resourceId = when (nombre) {
            "Charmander" -> R.string.Charmander
            "Machamp" -> R.string.Machamp
            "Electabuzz" -> R.string.Electabuzz
            "Rapidash" -> R.string.Rapidash
            "Chansey" -> R.string.Chansey
            "Shellder" -> R.string.Shellder
            "Beedrill" -> R.string.Beedrill
            "Hypno" -> R.string.Hypno
            "Rhydon" -> R.string.Rhydon
            "Exeggutor" -> R.string.Exeggutor
            "Vileplume" -> R.string.Vileplume
            "Tentacruel" -> R.string.Tentacruel
            else -> 0
        }

        return resourceId
    }

}