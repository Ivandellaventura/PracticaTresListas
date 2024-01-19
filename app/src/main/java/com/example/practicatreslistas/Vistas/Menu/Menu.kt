package com.example.practicatreslistas.Vistas.Menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.practicatreslistas.Pokemon.ArrayBox
import com.example.practicatreslistas.Pokemon.Datos
import com.example.practicatreslistas.Pokemon.Pokemon
import java.util.Collections.addAll
import com.example.practicatreslistas.R
import com.example.practicatreslistas.Vistas.Navegacion.Rutas

var num: Int = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavHostController) {
    var textoSearch by remember {
        mutableStateOf("")
    }
    var activeSearch by remember {
        mutableStateOf(false)
    }
    var listaMutable = remember {
        mutableListOf<Pokemon>().apply {
            addAll(ArrayBox)
        }
    }
    var botonCheck by remember { mutableStateOf(false) }
    var borrar by remember { mutableStateOf(false) }
    var Borrar = ArrayList<Int>()


    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Bienvenido a tu Fantasy Pokemon League",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = textoSearch,
            onQueryChange = { textoSearch = it },
            onSearch = { activeSearch = false },
            active = activeSearch,
            onActiveChange = { activeSearch = it }) {
            val EquiposFiltro = Datos().Gimnasios.filter {
                it.contains(textoSearch, true)

            }


            for (i in EquiposFiltro.indices) {
                Button(
                    onClick = { textoSearch = EquiposFiltro[i] },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painterResource(id = R.drawable.ic_star),
                                contentDescription = null,
                                Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Text(
                                text = EquiposFiltro[i],
                                Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Icon(
                            painterResource(id = R.drawable.ic_group),
                            contentDescription = null,
                            Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
                        )
                    }
                }
            }

        }
        LazyColumn() {
            items(listaMutable) { pokemon ->
                if (textoSearch == "") {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Datos().obtenerColorEquipo(pokemon.gimnasioJugador))
                    ) {
                        Column {


                            var CheckPulsado by remember { mutableStateOf(false) }
                            var habilitado by remember { mutableStateOf(true) }
                            Row(modifier = Modifier.fillMaxWidth().clickable {navController?.navigate(Rutas.PantallaInfo.ruta)
                                num = listaMutable.indexOf(pokemon)  }) {
                                Image(
                                    painterResource(id = Datos().DatosImagen(pokemon.nombre)),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(90.dp)
                                )
                                Column(modifier = Modifier.weight(3f)) {
                                    Text(
                                        text = "${pokemon.nombre}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = " Vs ${pokemon.gimnasioEnemigo} , ${pokemon.fecha}",
                                            textAlign = TextAlign.Center
                                        )
                                        if (botonCheck == true) {
                                            Checkbox(
                                                checked = CheckPulsado,
                                                onCheckedChange = {
                                                    CheckPulsado = !CheckPulsado
                                                },
                                                enabled = habilitado,
                                                modifier = Modifier
                                            )

                                            if (CheckPulsado == true) {
                                                Borrar.add(listaMutable.indexOf(pokemon))


                                            }
                                        }
                                    }

                                    Text(
                                        text = "${pokemon.victorias}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                        }
                    }

                } else if (pokemon.gimnasioJugador.contains(textoSearch)) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Datos().obtenerColorEquipo(pokemon.gimnasioJugador))
                    ) {
                        Column {


                            var CheckPulsado by remember { mutableStateOf(false) }
                            var habilitado by remember { mutableStateOf(true) }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painterResource(id = Datos().DatosImagen(pokemon.nombre)),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(90.dp)
                                )
                                Column(modifier = Modifier.weight(3f)) {
                                    Text(
                                        text = "${pokemon.nombre}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = " Vs ${pokemon.gimnasioEnemigo} , ${pokemon.fecha}",
                                            textAlign = TextAlign.Center
                                        )
                                        if (botonCheck == true) {
                                            Checkbox(
                                                checked = CheckPulsado,
                                                onCheckedChange = {
                                                    CheckPulsado = !CheckPulsado
                                                },
                                                enabled = habilitado,
                                                modifier = Modifier
                                            )

                                            if (CheckPulsado == true) {
                                                Borrar.add(listaMutable.indexOf(pokemon))


                                            }
                                        }
                                    }

                                    Text(
                                        text = "${pokemon.victorias}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                        }
                    }


                }


            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExtendedFloatingActionButton(
                onClick = { navController?.navigate(Rutas.PantallaDatos.ruta) },
                modifier = Modifier
            ) {
                Text(text = "Añadir")
                Icon(
                    painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null,
                    modifier = Modifier.size(27.dp)
                )
            }
            ExtendedFloatingActionButton(onClick = {
                if (borrar == false) {
                    botonCheck = !botonCheck
                    borrar = true
                } else {
                    for (i in 0 until Borrar.size - 1) {
                        // i = 0,1,2
                        ArrayBox.removeAt(Borrar[i])
                        println(ArrayBox.size)
                        listaMutable.clear()
                        Borrar.clear()
                        listaMutable.addAll(ArrayBox)
                    }
                    botonCheck = !botonCheck
                }
                println(Borrar.size)
                // borrar -> 2,4,1
                // solo borrar cuando pase de verdadeor a falso

            }) {
                Text(text = "Borrar")
                Icon(painterResource(id = R.drawable.baseline_delete_forever_24), contentDescription = null)
            }
        }



    }

}









