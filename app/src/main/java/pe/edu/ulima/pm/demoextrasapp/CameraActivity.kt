package pe.edu.ulima.pm.demoextrasapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import pe.edu.ulima.pm.demoextrasapp.presentation.MyCameraScreen

class CameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestCameraPermission()
        setContent {
            MyCameraScreen()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if ( isGranted) {
            Log.i("CameraActivity", "Permisos Otorgados")
        } else {
            Log.i("CameraActivity", "Permisos Denegados")
        }
    }

    private fun requestCameraPermission() {
        when {
            // 1. El usuario ya dio los permisos antes (para todas las veces)
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Location", "El usuario ya dio permisos")
            }

            // 2. No dio los permisos y se necesitan
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> {
                finish()
            }

            // 3. Aun no le ha dado los permisos
            else -> {
                // Lanzar pantalla para pedir permisos
                requestPermissionLauncher.launch(
                    android.Manifest.permission.CAMERA
                )
            }
        }
    }
}