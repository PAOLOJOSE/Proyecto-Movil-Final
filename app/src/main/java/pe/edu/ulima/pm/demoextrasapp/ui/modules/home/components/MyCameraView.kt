package pe.edu.ulima.pm.demoextrasapp.ui.modules.home.components

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Composable
fun MyCameraView(
    onPhotoTaken : (Uri) -> Unit,
    onError : (String) -> Unit,
    uri : Uri
) {
    if (uri == Uri.EMPTY) {
        // 1. Configuracion
        val lensFacing = remember {
            mutableStateOf(CameraSelector.LENS_FACING_BACK)
        }

        val imageCapture = remember {
            ImageCapture.Builder()
                .setTargetResolution(Size(1080, 1920 ))
                .build()
        }

        val context = LocalContext.current

        // Preview
        CameraPreviewView(
            imageCapture = imageCapture,
            lensFacing = lensFacing.value
        ) { action ->
            when {
                action is CameraUIAction.OnCameraClick -> {
                    // Se ha hecho click para tomar la foto
                    takePhoto(
                        filenameFormat = "dd-M-yyyy_hh.mm.ss",
                        imageCapture = imageCapture,
                        outputDirectory = context.getOutputDirectory(),
                        executor = Executors.newSingleThreadExecutor(),
                        onPhotoTaken = onPhotoTaken,
                        onError =  onError
                    )
                }

                action is CameraUIAction.OnSwitchCameraClick -> {
                    // Caso de cambio de camara (frontal <-> back)
                    lensFacing.value =
                        if (lensFacing.value == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                        else CameraSelector.LENS_FACING_BACK
                }
            }
        }
    }else {
        AsyncImage(
            model = uri,
            contentDescription = "Foto tomada",
            modifier = Modifier.fillMaxSize()
        )
    }



}

private fun takePhoto(
    filenameFormat : String,
    imageCapture: ImageCapture,
    outputDirectory : File,
    executor: Executor,
    onPhotoTaken : (Uri) -> Unit,
    onError : (String) -> Unit
) {
    // Nombre del archivo donde se guardara la foto
    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis())
    )

    val outputOptions = ImageCapture
        .OutputFileOptions
        .Builder(photoFile)
        .build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onPhotoTaken(outputFileResults.savedUri!!)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception.message!!)
            }
        }
    )
}

// Extension
private fun Context.getOutputDirectory() : File {
    val mediaDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        File(
            MediaStore.Images.Media.RELATIVE_PATH, "pe.edu.ulima.pm.demoextrasapp"
        ).apply {
            mkdirs()
        }
    } else {
        this.externalMediaDirs.firstOrNull()?.let {
            File(it, "pe.edu.ulima.pm.demoextrasapp").apply {
                mkdirs()
            }
        }
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else this.filesDir
}









