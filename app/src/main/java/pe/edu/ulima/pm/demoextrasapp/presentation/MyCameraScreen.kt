package pe.edu.ulima.pm.demoextrasapp.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import pe.edu.ulima.pm.demoextrasapp.presentation.components.MyCameraView

@Composable
fun MyCameraScreen() {
    val uri = remember {
        mutableStateOf(Uri.EMPTY)
    }

    MyCameraView(
        onPhotoTaken = {photoUri ->
            Log.d("URI", uri.toString())
            uri.value = photoUri
        },
        onError = {},
        uri = uri.value
    )
}