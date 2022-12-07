sealed class LibraryScreenRoutes(val route:String) {
    object Camera: LibraryScreenRoutes("CamaraPantalla")
    object Search: LibraryScreenRoutes("SearchPantalla")
    object BookList: LibraryScreenRoutes("BookListPantalla")
    object BookDetail: LibraryScreenRoutes("BookDetailPantalla")
    object Comments: LibraryScreenRoutes("CommentsPantalla")
    object Reserve: LibraryScreenRoutes("ReservePantalla")
}
//object HomeDirections : NavigationGroup(
//    NavigationRoute(
//        "home",
//        emptyList()
//    )
//) {
//    val home = NavigationRoute(
//        "${root.destination}/home",
//        emptyList()
//    )
//
//
//    override var default = home
//}
//
//
//object LibraryDirections : NavigationGroup(
//    NavigationRoute(
//        "library",
//        emptyList()
//    )
//) {
//    val bookList = NavigationRoute(
//        "${root.destination}/book-list",
//        emptyList()
//    )
//    val bookDetail = NavigationRoute(
//        "${root.destination}/book-detail",
//        listOf(navArgument("bookId") { type = NavType.IntType })
//    )
//    val bookCommentaries = NavigationRoute(
//        "${root.destination}/book-commentaries",
//        listOf(navArgument("bookId") { type = NavType.IntType })
//    )
//
//    val bookReserve = NavigationRoute(
//        "${root.destination}/book-reserve",
//        emptyList()
//    )
//
//    override var default = bookList
//}