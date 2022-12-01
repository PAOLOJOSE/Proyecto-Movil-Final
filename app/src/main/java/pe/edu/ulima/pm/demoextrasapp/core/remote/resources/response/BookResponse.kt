package pe.edu.ulima.pm.demoextrasapp.core.remote.resources.response

data class BooksResponse(
    val ISBN: String,
    val autor: String,
    val año: String,
    val codCla: String,
    val coment: String,
    val copias: Int,
    val descr: String,
    val dispo: Int,
    val id: Int,
    val imprenta: String,
    val localizacion: String,
    val tema: String,
    val titulo: String,
    val year: String
)