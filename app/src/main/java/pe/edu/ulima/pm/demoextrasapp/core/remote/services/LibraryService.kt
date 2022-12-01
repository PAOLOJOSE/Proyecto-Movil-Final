package pe.edu.ulima.pm.demoextrasapp.core.remote.services


import pe.edu.ulima.pm.demoextrasapp.core.remote.resources.response.ListBooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface LibraryService {
    @GET("libros")
    suspend fun listBooks(): Response<ListBooksResponse>
}