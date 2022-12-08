sealed class LibraryScreenRoutes(val route:String) {
    object Camera: LibraryScreenRoutes("CamaraPantalla")
    object BookList: LibraryScreenRoutes("BookListPantalla")
    object BookDetail: LibraryScreenRoutes("BookDetailPantalla")
    object Comments: LibraryScreenRoutes("CommentsPantalla")
    object Reserve: LibraryScreenRoutes("ReservePantalla")
}
