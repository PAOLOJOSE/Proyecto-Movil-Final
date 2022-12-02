package pe.edu.ulima.pm.demoextrasapp.ui.modules.library

import LibraryDirections
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import pe.edu.ulima.pm.demoextrasapp.ui.viewModels.LibraryViewModel

@Composable
fun DialogContent(message: String) {
    Card() {
        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message, fontSize = 24.sp)
            Icon(Icons.Rounded.Check, contentDescription = "Check")
        }
    }
}

@Composable
fun BookDetail(
    bookId: Int?, libraryViewModel: LibraryViewModel, navigatorController: NavHostController
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val book = libraryViewModel.book.observeAsState()

    var stock by remember {
        mutableStateOf(0)
    }
    var dialogState = remember {
        mutableStateOf(false)
    }
    var dialogMessage by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit, block = {
        if (bookId == null) {
            return@LaunchedEffect;
        }
        libraryViewModel.getBook(bookId)
        libraryViewModel.book.observe(lifecycleOwner) { it ->
            stock = it.dispo
        }
    })

    if (dialogState.value) {
        Dialog(onDismissRequest = { dialogState.value = false }, content = {
            DialogContent(dialogMessage)
        })
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Column(
            Modifier
                .width(300.dp)
                .height(150.dp)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .border(width = 2.dp, Color.Black),
        ) {
            Text(text = book.value!!.titulo, fontSize = 30.sp)
            Text(text = "Autor: ${book.value!!.autor}")
            Text(text = "Pie de imprenta: ${book.value!!.imprenta}")
            Text(text = "Descripcion fisica: ${book.value!!.descr}")
            Text(text = "ISBN: ${book.value!!.ISBN}")
        }
        Column(
            Modifier
                .width(300.dp)
                .border(
                    width = 2.dp, Color.Black
                ),
        ) {
            Text(text = "Codigo de clasificacion: ${book.value!!.codCla}")
            Text(text = "Localización: ${book.value!!.localizacion}")
            Text(text = "Copias: ${book.value!!.copias}")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            Modifier
                .width(300.dp)
                .height(50.dp)
                .border(width = 2.dp, Color.Black),
        ) {
            Text(text = "Disponibilidad: $stock")
            Button(
                onClick = {
                    if (stock == 0) {
                        return@Button
                    }
                    --stock
                    dialogMessage = "Reserva exitosa"
                    dialogState.value = true
                },
            ) {
                Text("RESERVAR")
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                dialogMessage = "Quedan $stock disponibles"
                dialogState.value = true
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("NOTIFICAR DE DISPONIBILIDAD")
        }

        Spacer(modifier = Modifier.width(30.dp))

        Button(
            onClick = {
                navigatorController.navigate(
                    "${LibraryDirections.bookCommentaries.destination}/$bookId"
                )
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Leer comentarios")
        }
    }
}
