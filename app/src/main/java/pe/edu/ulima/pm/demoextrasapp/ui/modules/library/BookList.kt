package pe.edu.ulima.pm.demoextrasapp.ui.modules.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm.demoextrasapp.R

@Composable
fun BookList() {
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
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = "PANTALLA 1",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text("Ingresa el título o tema") }
        )

        Button(onClick = { (textValue) }) {
            Text("REGLAMENTO DE LA BIBLIOTECA")
        }
    }
}

@Preview
@Composable
fun PreviewLibrary() {
    BookList()
}
