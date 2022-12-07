package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SearchScreen(onAccessToLibraryClick: () -> Unit, onAccessCameraClick: () -> Unit) {
    val context = LocalContext.current
    val intent = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.ulima.edu.pe/departamento/biblioteca/reglamento")
        )
    }
    var textValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Utiliza la camara para buscar un libro o accede directamenta a la Bilbioteca",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )

        IconButton(onClick = onAccessCameraClick) {
            Icon(
                Icons.Rounded.PhotoCamera, contentDescription = "Bg Image", Modifier.size(50.dp)
            )
        }

        Column {
            Row(
                modifier = Modifier

            ) {
                TextField(value = textValue,
                    onValueChange = { textValue = it },
                    label = { Text("Ingresa el título o tema") })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Button(onClick = onAccessToLibraryClick) {
                    Text("ACCEDER A LA BIBLIOTECA", textAlign = TextAlign.Center)
                }
            }
        }

        Button(onClick = { context.startActivity(intent) }) {
            Text("REGLAMENTO DE LA BIBLIOTECA")
        }
    }
}