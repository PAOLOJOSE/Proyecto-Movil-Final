package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.R

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, pageTitle: String) {
    TopAppBar(
        title = { Text(text = pageTitle, fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = colorResource(id = R.color.universidad_de_lima),
        contentColor = Color.White
    )
}