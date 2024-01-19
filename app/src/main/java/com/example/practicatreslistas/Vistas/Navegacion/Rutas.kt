package com.example.practicatreslistas.Vistas.Navegacion

sealed class Rutas(val ruta: String) {
    /* TODO
        Crear e identificar las rutas (diferentes pantallas) de nuestra app
     */
    object PantallaMenu: Rutas("Menu")
    object PantallaDatos: Rutas("Datos")
    object PantallaInfo: Rutas("Info")


}