package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun MyCameraScreen() {
//    val uri = remember {
//        mutableStateOf(Uri.EMPTY)
//    }
//
//    MyCameraView(
//        onTakePhotoClick = { photoUri ->
//            Log.d("URI", uri.toString())
//            uri.value = photoUri
//        },
//        onTakePhotoErroClick = {},
//        uri = uri.value
//    )
}

@Composable
fun AdminClubMembershipScanScreen(navController: NavHostController) {

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            Log.i("carlos", "scanned code: ${result.contents}")
        }
    )

    Button(onClick = {
        scanLauncher.launch(ScanOptions())
    }) {
        Text(text = "Scan barcode")
    }

//    val context = LocalContext.current
//    var scanFlag by remember {
//        mutableStateOf(false)
//    }
//
//    val compoundBarcodeView = remember {
//        CompoundBarcodeView(context).apply {
//            val capture = CaptureManager(context as Activity, this)
//            capture.initializeFromIntent(context.intent, null)
//            this.setStatusText("")
//            capture.decode()
//            this.decodeContinuous { result ->
//                if(scanFlag){
//                    return@decodeContinuous
//                }
//                scanFlag = true
//                result.text?.let { barCodeOrQr->
//                    //Do something and when you finish this something
//                    //put scanFlag = false to scan another item
//                    scanFlag = false
//                }
//                //If you don't put this scanFlag = false, it will never work again.
//                //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning
//
//            }
//        }
//    }
//
//    AndroidView(
//        modifier = Modifier.fillMaxSize()
//            .background(Color.Blue),
//        factory = { compoundBarcodeView },
//    )
}