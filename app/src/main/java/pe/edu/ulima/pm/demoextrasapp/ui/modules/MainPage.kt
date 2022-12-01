package pe.edu.ulima.pm.demoextrasapp.ui.modules

import LibraryDirections
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.R

import pe.edu.ulima.pm.demoextrasapp.ui.modules.shared.AppTopBar

@Composable
fun DrawerItem(title: String, imageVector: ImageVector) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Icon(imageVector, contentDescription = "Icon")
        Text(text = title)
    }
}

@Composable
fun AppDrawer() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.estrella),
            contentDescription = "Logo",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        )
        Text(text = "Universidad de Lima", fontSize = 20.sp)
    }
    DrawerItem(title = "Test", Icons.Filled.Menu)
}

@Composable
fun MainPage() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, drawerContent = { AppDrawer() }, topBar = {
        AppTopBar(title = "Biblioteca", openDrawer = {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        })
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding))
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            startDestination = LibraryDirections.default.destination,
            route = LibraryDirections.root.destination
        ) {


        }

    }

}