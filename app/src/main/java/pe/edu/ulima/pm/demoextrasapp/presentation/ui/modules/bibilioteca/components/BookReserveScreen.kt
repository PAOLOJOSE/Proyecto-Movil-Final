package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BookReserve(navigatorController: NavHostController) {

    Column(
        Modifier
            .width(300.dp)
            .height(150.dp)
            .border(width = 2.dp, Color.Black),
    ) {
        Text(text = "SE HA RESERVADO SATISFACTORIAMENTE", fontSize = 50.sp)

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            Modifier
                .width(300.dp)
                .height(150.dp)
                .border(width = 2.dp, Color.Black),
        ) {
            Text(text = "<Titulo Libro>", fontSize = 30.sp)
            Text(text = "Autor: ")
            Text(text = "Pie de imprenta: ")
            Text(text = "Descripcion fisica: ")
            Text(text = "ISBN: ")
        }

    }


}