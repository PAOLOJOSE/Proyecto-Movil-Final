package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels.LibraryViewModel

@Composable
fun BookReserve(
    navigatorController: NavHostController,
    libraryViewModel: LibraryViewModel,
) {

    val reservedBook = libraryViewModel.selectedBook.observeAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
    )
    {
        Column(
            modifier = Modifier
                .border(width = 2.dp, Color.LightGray)
                .padding(20.dp, 20.dp, 20.dp, 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

            Text(
                text = reservedBook.value!!.titulo,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 25.dp),
            )
            Text(text = "Autor:   ${reservedBook.value!!.autor}", color = Color.LightGray)
            Text(
                text = "Pie de imprenta:   ${reservedBook.value!!.imprenta} ",
                color = Color.LightGray,
            )
            Text(
                text = "Descripcion fisica:   ${reservedBook.value!!.descr} paginas. ",
                color = Color.LightGray,
            )
            Text(
                text = "ISBN: ${reservedBook.value!!.ISBN}",
                modifier = Modifier.padding(bottom = 45.dp),
                color = Color.LightGray,
            )

            Text(text = "Se debe devolver en 12 dias", color = Color.LightGray)
            Text(text = "Fecha de reserva: ${libraryViewModel.obtenerFecha()}")
            Text(text = "Hora de reserva: ${libraryViewModel.obtenerTiempo()}")
        }
    }
}
