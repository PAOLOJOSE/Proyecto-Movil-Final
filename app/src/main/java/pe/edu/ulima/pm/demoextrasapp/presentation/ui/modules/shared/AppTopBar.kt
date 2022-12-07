package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.shared

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppTopBar(title: String, openDrawer: () -> Unit = {}) {
    TopAppBar(title = { Text(title) },
        elevation = 0.dp,
        modifier = Modifier.statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.Menu, contentDescription = "Drawer")
            }
        })
}