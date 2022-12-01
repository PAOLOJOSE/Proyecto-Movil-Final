package pe.edu.ulima.pm.demoextrasapp.ui.modules.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow

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

    Row(
        Modifier.size(200.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.libro),
            contentDescription = "libro Image",
            modifier = Modifier
                .size(100.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Column(
            Modifier.size(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Titulo del libro")
            Text(text = "Tema")
        }
    }
}
