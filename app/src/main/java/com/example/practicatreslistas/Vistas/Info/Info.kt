package com.example.practicatreslistas.Vistas.Info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.practicatreslistas.Pokemon.ArrayBox
import com.example.practicatreslistas.Pokemon.Datos
import com.example.practicatreslistas.Vistas.Menu.num


@Composable
fun Info(navController: NavHostController) {
    var pokemon = ArrayBox[num]
    Column {
        Text(text = "${pokemon.nombre}  que juega en ${pokemon.gimnasioJugador}")
        Image(painterResource(id = Datos().DatosImagen(pokemon.nombre)), contentDescription = null)
        Text(stringResource(id = Datos().DatosTexto(pokemon.nombre)))
        Text(text = "Nombre ${pokemon.nombre} \nGimnasio ${pokemon.gimnasioJugador}\nTipos ${pokemon.tipo}\nVictorias ${pokemon.victorias}\nContra ${pokemon.gimnasioEnemigo}\nDia ${pokemon.fecha}")
    }

}