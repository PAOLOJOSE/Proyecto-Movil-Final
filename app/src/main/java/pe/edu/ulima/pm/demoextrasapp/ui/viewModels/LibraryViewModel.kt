package pe.edu.ulima.pm.demoextrasapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.core.model.Book
import pe.edu.ulima.pm.demoextrasapp.core.remote.services.LibraryService
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryService: LibraryService
) : ViewModel() {

    private var _books = MutableLiveData<List<Book>>(listOf())
    val books: LiveData<List<Book>> = _books

    private var _book = MutableLiveData<Book>(Book())
    val book: LiveData<Book> = _book;

    fun getBook(bookId: Int) {
        viewModelScope.launch {
            try {
                val listBookResponse = libraryService.listBooks()
                val data = listBookResponse.body()

                if (data != null && viewModelScope.isActive) {
                    val bookResponse = data.books.find { it -> it.id == bookId }
                    val book = if (bookResponse == null) {
                        Book()
                    } else {
                        Book(
                            bookResponse.ISBN,
                            bookResponse.autor,
                            bookResponse.codCla,
                            bookResponse.coment,
                            bookResponse.copias,
                            bookResponse.descr,
                            bookResponse.dispo,
                            bookResponse.id,
                            bookResponse.imprenta,
                            bookResponse.localizacion,
                            bookResponse.tema,
                            bookResponse.titulo,
                            bookResponse.year,
                            bookResponse.url
                        )
                    }
                    _book.postValue(
                        book
                    )
                }
            } catch (exception: Exception) {
                Log.e("getBook", exception.toString())
            }
        }

    }

    fun listBooks() {
        viewModelScope.launch {
            try {
                val listBookResponse = libraryService.listBooks()
                Log.i("ListBook", listBookResponse.body().toString())

                val data = listBookResponse.body()
                if (data != null && viewModelScope.isActive) {
                    val books = data.books.map { it ->
                        Book(
                            it.ISBN,
                            it.autor,
                            it.codCla,
                            it.coment,
                            it.copias,
                            it.descr,
                            it.dispo,
                            it.id,
                            it.imprenta,
                            it.localizacion,
                            it.tema,
                            it.titulo,
                            it.year,
                            it.url
                        )
                    }.filter {
                        // TODO refactorizar este método
                        findText(it.tema,"Basica") || findText(it.titulo,"Basica") || findText(it.autor,"Basica")
                    }
                    _books.postValue(books)
                }
            } catch (exception: Exception) {
                Log.e("ListBooks", exception.toString())
            }

        }
    }

    private fun findText(text: String, textSearched:String): Boolean {
        val regex = """(?i)\b($textSearched)\b""".toRegex()
        return regex.containsMatchIn(text)
    }
}