package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pe.edu.ulima.pm.demoextrasapp.core.model.Book
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels.LibraryViewModel

@Composable
fun BookCommentaries(
    bookId: Int?, book: Book, navigatorController: NavHostController
) {

    var textValue by remember { mutableStateOf("") }
    var stock by remember {
        mutableStateOf(0)
    }

    var commentaries = remember {
        mutableStateListOf<String>()
    }

    val lifecycleOwner = LocalLifecycleOwner.current


    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    val addCommentary: () -> Unit = {
        if (textValue.isNotEmpty()) {
            commentaries.add(textValue)
            textValue = String()
        }
        focusManager.clearFocus()
    }

    LaunchedEffect(key1 = Unit, block = {
        if (bookId == null) {
            return@LaunchedEffect;
        }
//        libraryViewModel.selectedBook.observe(lifecycleOwner) { it ->
//            stock = it.dispo
//        }
    })
    Box(modifier = Modifier.padding(25.dp, 15.dp, 15.dp, 10.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                Modifier
                    .width(350.dp)
                    .height(200.dp)
                    .background(Color.LightGray)
                    .padding(15.dp, 15.dp, 15.dp, 15.dp),
                horizontalAlignment = Alignment.Start,
            ) {

                Text(text = "Usuario N°1: ", fontSize = 25.sp)

                Text(
                    text = book.coment,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(30.dp))
                        .padding(7.dp)
                )
                Spacer(modifier = Modifier.height(7.dp))
                LazyColumn {
                    items(commentaries) { commentary ->
                        Text(
                            text = commentary,
                            Modifier
                                .background(Color.White, shape = RoundedCornerShape(30.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
            }


            TextField(value = textValue,
                modifier = Modifier
                    .width(350.dp)
                    .height(235.dp)
                    .background(Color.LightGray)
                    .focusRequester(focusRequester),
                onValueChange = { textValue = it },
                label = { Text("Ingresa un comentario") })

            Spacer(modifier = Modifier.width(16.dp))

            Row {
                Column() {
                    Button(onClick = { addCommentary() }) {
                        Text("PUBLICAR")
                    }
                    Button(onClick = { (textValue) }) {
                        Text("REGRESAR")
                    }
                }

            }
        }
    }

}