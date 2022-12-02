package pe.edu.ulima.pm.demoextrasapp.ui.modules.home.components

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FlipCameraAndroid
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = {
                cameraUIActionCallback(CameraUIAction.OnCameraClick)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(64.dp)
                .padding(1.dp)
                .border(1.dp, Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Sharp.Lens,
                contentDescription = "",
                tint = Color.White
            )
        }
        IconButton(
            onClick = {
                cameraUIActionCallback(CameraUIAction.OnSwitchCameraClick)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(64.dp)
                .padding(1.dp)
                .border(1.dp, Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Sharp.FlipCameraAndroid,
                contentDescription = "",
                tint = Color.White
            )
        }

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