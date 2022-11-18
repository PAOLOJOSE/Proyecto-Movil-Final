package pe.edu.ulima.pm.demoextrasapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import pe.edu.ulima.pm.demoextrasapp.presentation.MainScreen

val CHANNEL_ID = "1"

class MainActivity : ComponentActivity() {
    private var channel : NotificationChannel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestLocationPermission()

        createNotificationChannel()

        setContent {
            MainScreen(
                onNotificationClick = sendNotification
            )
        }
    }

    val sendNotification = {
        val notif = createNotification()
        val notifManager = NotificationManagerCompat.from(this)
        notifManager.notify(1, notif)
    }

    fun createNotification() : Notification {

        val intent = Intent(
            this,
            DestinoActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                0
            )
        }

        val notif = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notificacion PM")
            .setContentText("Notificación de Prueba!!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        return notif
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(
                CHANNEL_ID,
                "Canal de notificacion 1",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Es una descripcion"
            }

            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel!!)
        }
    }

    // Localizacion

    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { seOtorgaronPermisos ->
        if (seOtorgaronPermisos) {
            Log.i("Location", "Se otorgaron los permisos")
        } else {
            Log.i("Location", "NO se otorgaron los permisos")
        }
    }

    private fun requestLocationPermission() {
        when {
            // 1. El usuario ya dio los permisos antes (para todas las veces)
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Location", "El usuario ya dio permisos")
            }

            // 2. No dio los permisos y se necesitan
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                finish()
            }

            // 3. Aun no le ha dado los permisos
            else -> {
                // Lanzar pantalla para pedir permisos
                requestPermissionsLauncher.launch(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }
}







