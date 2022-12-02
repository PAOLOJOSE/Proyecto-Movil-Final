package pe.edu.ulima.pm.demoextrasapp.ui.modules.library

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlin.math.round

@Composable
fun BookDetail(navigatorController: NavHostController) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    )
    {
        Column(
            Modifier
                .width(300.dp)
                .height(150.dp)
                .border(width = 2.dp, Color.Black),
        ){
            Text(text = "Titulo: ", fontSize = 30.sp)
            Text(text = "Autor: ")
            Text(text = "Pie de imprenta: ")
            Text(text = "Descripcion fisica: ")
            Text(text = "ISBN: ")
        }
        Column(
            Modifier
                .width(300.dp)
                .height(50.dp)
                .border(width = 2.dp, Color.Black),
        ){
            Text(text = "Codigo de clasificacion: ")
            Text(text = "Localización: ")
            Text(text = "Copias: ")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            Modifier
                .width(300.dp)
                .height(50.dp)
                .border(width = 2.dp, Color.Black),
        ) {
            Text(text = "Disponibilidad: 1")
            Button(
                onClick = { },
            ){
                Text("RESERVAR")
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("NOTIFICAR DE DISPONIBILIDAD")
        }

        Spacer(modifier = Modifier.width(30.dp))

        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Leer comentarios")
        }
    }
}
