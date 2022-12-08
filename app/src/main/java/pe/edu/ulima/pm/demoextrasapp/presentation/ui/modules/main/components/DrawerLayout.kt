package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main.components

import DrawerItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.R
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main.NavDrawerItem

@Composable
fun DrawerLayout(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    userNameLogged: String
) {
    val items = listOf(
        NavDrawerItem.Intranet,
        NavDrawerItem.Coffee,
        NavDrawerItem.Events,
        NavDrawerItem.Promotion,
        NavDrawerItem.Library,
        NavDrawerItem.News
    )
    Column {
        // Header
        Column(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.primary
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 5.dp)
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.man),
                    contentDescription = "carlos",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(35.dp)
                )
                Text(
                    text = userNameLogged,
                    fontSize = 15.sp,
                    color = Color.Blue
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        // Body - Drawer Items
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    }
}