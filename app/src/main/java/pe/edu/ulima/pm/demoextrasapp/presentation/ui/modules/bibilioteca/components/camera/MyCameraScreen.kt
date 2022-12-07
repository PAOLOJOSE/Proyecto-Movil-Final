package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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