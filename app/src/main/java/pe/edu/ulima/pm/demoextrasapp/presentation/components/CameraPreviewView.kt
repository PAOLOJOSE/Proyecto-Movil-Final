package pe.edu.ulima.pm.demoextrasapp.presentation.components

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraPreviewView(
    imageCapture: ImageCapture,
    lensFacing : Int = CameraSelector.LENS_FACING_BACK,
    cameraUIActionCallback : (CameraUIAction) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    val previewView = remember {
        PreviewView(context)
    }

    val preview = Preview.Builder().build()

    LaunchedEffect(key1 = lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

}

// Extensions
private suspend fun Context.getCameraProvider() : ProcessCameraProvider
    = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also {
        it.addListener({
            continuation.resume(it.get())
        }, ContextCompat.getMainExecutor(this))
    }
}