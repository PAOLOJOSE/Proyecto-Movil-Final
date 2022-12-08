package pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels

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


    private var _selectedBook = MutableLiveData<Book>(Book())
    val selectedBook: LiveData<Book> = _selectedBook;

    private var _selectedTitulo = MutableLiveData<String>("")
    val selectedTitulo: LiveData<String> = _selectedTitulo;

    private var _loggedUserName = MutableLiveData<String>("No inicio sesion")
    val loggedUserName: LiveData<String> = _loggedUserName;

    fun setSelectedTitulo(titulo: String) {
        _selectedTitulo.value = titulo
    }

    fun setSelectedBook(book: Book) {
        _selectedBook.value = book
    }

    fun setLoggedUserName(userName: String) {
        _loggedUserName.value = userName
    }

    fun listBooks(textToBeSearched: String) {
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
                        findText(it.tema, textToBeSearched) ||
                                findText(it.titulo, textToBeSearched) ||
                                findText(it.autor, textToBeSearched)
                    }
                    _books.postValue(books)
                }
            } catch (exception: Exception) {
                Log.e("ListBooks", exception.toString())
            }

        }
    }

    private fun findText(originalText: String, textToBeSearched: String): Boolean {
        return originalText.contains(textToBeSearched, ignoreCase = true)
    }
}