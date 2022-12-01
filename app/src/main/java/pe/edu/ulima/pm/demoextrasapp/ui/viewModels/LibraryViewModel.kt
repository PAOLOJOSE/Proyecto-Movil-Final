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

    private var _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

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
                            it.año,
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
                            it.year
                        )
                    }
                    _books.postValue(books)
                }
            } catch (exception: Exception) {
                Log.e("ListBooks", exception.toString())
            }

        }
    }
}