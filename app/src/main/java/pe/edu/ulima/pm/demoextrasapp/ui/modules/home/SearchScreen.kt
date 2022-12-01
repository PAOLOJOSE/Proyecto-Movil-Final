package pe.edu.ulima.pm.demoextrasapp.ui.modules.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.ulima.pm.demoextrasapp.R

@Composable
fun SearchScreen(){
    var textValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.camara),
                contentDescription = "Bg Image",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(

        ) {
            Row(
                modifier = Modifier

            ){
                TextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    label = { Text("Ingresa el título o tema") }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Button(onClick = { (textValue) }
                ) {

                    Text("BUSCAR")
                }

            }
        }

        Button(onClick = { (textValue) }) {
            Text("REGLAMENTO DE LA BIBLIOTECA")
        }
    }
}