import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm.demoextrasapp.R

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
    Spacer(modifier = Modifier.height(10.dp))
    DrawerItem(title = "Cafeterias", Icons.Rounded.Coffee)
    DrawerItem(title = "Eventos", Icons.Rounded.CalendarMonth)
    DrawerItem(title = "Promociones", Icons.Rounded.Sell)
    DrawerItem(title = "Biblioteca", Icons.Rounded.LibraryBooks)
    DrawerItem(title = "Noticias", Icons.Rounded.Newspaper)
}