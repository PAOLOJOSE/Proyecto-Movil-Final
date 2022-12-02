package pe.edu.ulima.pm.demoextrasapp.core.model

data class Book(
    val ISBN: String = "",
    val autor: String = "",
    val codCla: String = "",
    val coment: String = "",
    val copias: Int = 0,
    val descr: String = "",
    val dispo: Int = 0,
    val id: Int = 0,
    val imprenta: String = "",
    val localizacion: String = "",
    val tema: String = "",
    val titulo: String = "",
    val year: String = "",
    val url: String = ""
)