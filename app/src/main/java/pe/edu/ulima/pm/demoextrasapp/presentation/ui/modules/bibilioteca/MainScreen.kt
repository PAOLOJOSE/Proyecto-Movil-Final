package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen (
    onNotificationClick : () -> Unit,
    onUltimaLocalizacionClick : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { onNotificationClick() }
        ) {
            Text(text = "EnviarNotificacion")
        }
        Button(
            onClick = { onUltimaLocalizacionClick() }
        ) {
            Text(text = "Obtener Ultima Localizacion")
        }
    }
}