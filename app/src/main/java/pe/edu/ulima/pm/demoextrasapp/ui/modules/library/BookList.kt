package pe.edu.ulima.pm.demoextrasapp.ui.modules.library


import LibraryDirections
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

import pe.edu.ulima.pm.demoextrasapp.core.model.Book
import pe.edu.ulima.pm.demoextrasapp.ui.viewModels.LibraryViewModel

@Composable
fun BookItem(
    title: String, description: String, imageUrl: String, navigate: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .clickable(onClick = { navigate() }),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        AsyncImage(
            model = imageUrl, contentDescription = null, modifier = Modifier.size(80.dp)
        )
        Column(
        ) {
            Text(text = title)
            Text(text = description)
        }
    }
}


@Composable
fun BookList(
    libraryViewModel: LibraryViewModel = viewModel(), navigatorController: NavHostController
) {

    val books = libraryViewModel.books.observeAsState()
    val booksList = if (books.value == null) listOf<Book>() else {
        books.value!!
    }
    LaunchedEffect(key1 = Unit, block = {
        libraryViewModel.listBooks()
    })

    LazyColumn {
        items(booksList) { book ->
            BookItem(book.titulo, book.tema, book.url) {
                navigatorController.navigate("${LibraryDirections.bookDetail.destination}/${book.id}")
            }
        }
    }
//bookCommentaries

}