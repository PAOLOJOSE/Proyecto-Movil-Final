package pe.edu.ulima.pm.demoextrasapp.ui.modules.home

import HomeDirections
import LibraryDirections
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Camera
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.ulima.pm.demoextrasapp.R

@Composable
fun SearchScreen(navController: NavController) {
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
        IconButton(onClick = { navController.navigate(HomeDirections.camera.destination) }) {
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
                Button(onClick = { navController.navigate(LibraryDirections.bookList.destination) }) {
                    Text("BUSCAR")
                }
            }
        }

        Button(onClick = { context.startActivity(intent) }) {
            Text("REGLAMENTO DE LA BIBLIOTECA")
        }
    }
}