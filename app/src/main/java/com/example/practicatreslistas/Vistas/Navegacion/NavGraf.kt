package com.example.practicatreslistas.Vistas.Navegacion

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.practicatreslistas.Vistas.Datos.DatosPokemon
import com.example.practicatreslistas.Vistas.Info.Info
import com.example.practicatreslistas.Vistas.Menu.Menu

@Composable
fun GrafoNav(context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Menu"){

        composable(Rutas.PantallaMenu.ruta){
            Menu(navController = navController)

        }
        composable(Rutas.PantallaDatos.ruta){
            DatosPokemon(navController = navController)

        }
        composable(Rutas.PantallaInfo.ruta){
            Info(navController = navController)

        }


    }

}