package pe.edu.ulima.pm.demoextrasapp.presentation

import androidx.compose.runtime.Composable
import pe.edu.ulima.pm.demoextrasapp.presentation.components.MyCameraView

@Composable
fun MyCameraScreen() {
    MyCameraView(onPhotoTaken = {}, onError = {})
}