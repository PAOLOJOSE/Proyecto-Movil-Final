package pe.edu.ulima.pm.demoextrasapp.ui.modules.library

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BookCommentaries(navigatorController: NavHostController) {
    var textValue by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            Modifier
                .width(260.dp)
                .height(150.dp)
                .border(width = 2.dp, Color.Black),
        ){
            Text(text = "Usuario N°1: ", fontSize = 40.sp)

        }
        Column(
            Modifier
                .width(260.dp)
                .height(50.dp)
                .border(width = 2.dp, Color.Black),
        ){
            TextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Ingresa el título o tema") }
            )
        }

        Row(
            Modifier
                .width(260.dp)
                .height(50.dp)
        ){
            Button(onClick = { (textValue) }) {

                Text("PUBLICAR")
            }
            Button(onClick = { (textValue) }) {
                Text("REGRESAR")
            }
        }
    }

}