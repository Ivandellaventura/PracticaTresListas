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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    var borrar by remember { mutableStateOf(false) }
    var selectedForDeletion by remember { mutableStateOf(mutableSetOf<Pokemon>()) }



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

                                    }

                                    Text(
                                        text = "${pokemon.victorias}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (borrar) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    var selecionado by remember {
                                        mutableStateOf(pokemon.seleccionado)
                                    }
                                    Checkbox(
                                        checked = selecionado,
                                        onCheckedChange = { isChecked ->
                                            pokemon.seleccionado = isChecked
                                            selecionado = isChecked
                                            if(isChecked){
                                                selectedForDeletion.add(pokemon)
                                            }else{
                                                selectedForDeletion.remove(pokemon)
                                            }


                                        },
                                        modifier = Modifier.padding(8.dp)
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

                                    }

                                    Text(
                                        text = "${pokemon.victorias}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (borrar) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    var selecionado by remember {
                                        mutableStateOf(pokemon.seleccionado)
                                    }
                                    Checkbox(
                                        checked = selecionado,
                                        onCheckedChange = { isChecked ->
                                            pokemon.seleccionado = isChecked
                                            selecionado = isChecked
                                            if(isChecked){
                                                selectedForDeletion.add(pokemon)
                                            }else{
                                                selectedForDeletion.remove(pokemon)
                                            }


                                        },
                                        modifier = Modifier.padding(8.dp)
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
                borrar = !borrar

                if (!borrar) {
                    // Eliminar elementos marcados

                    if (selectedForDeletion.isNotEmpty()) {
                        ArrayBox.removeAll(selectedForDeletion)
                        // Eliminar elementos de ArrayBox


                        selectedForDeletion.clear()
                        navController?.navigate(Rutas.PantallaMenu.ruta)
                    }
                }
            }) {
                Text(
                    text = if (borrar && selectedForDeletion.isNotEmpty()) "Confirmar" else if (borrar) "Cancelar" else "Borrar"
                )

                Icon(
                    painterResource(id = if (borrar) R.drawable.baseline_delete_forever_24 else R.drawable.baseline_delete_forever_24),
                    contentDescription = null
                )
            }
        }



    }

}









