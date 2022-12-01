import pe.edu.ulima.pm.demoextrasapp.core.navigation.NavigationGroup
import pe.edu.ulima.pm.demoextrasapp.core.navigation.NavigationRoute

object HomeDirections : NavigationGroup(
    NavigationRoute(
        "home",
        emptyList()
    )
) {
    val home = NavigationRoute(
        "${root.destination}/home",
        emptyList()
    )
    override var default = home
}


object LibraryDirections : NavigationGroup(
    NavigationRoute(
        "driver",
        emptyList()
    )
) {
    val bookList = NavigationRoute(
        "${root.destination}/book-list",
        emptyList()
    )
    val bookDetail = NavigationRoute(
        "${root.destination}/book-detail",
        emptyList()
    )
    val bookCommentaries = NavigationRoute(
        "${root.destination}/book-commentaries",
        emptyList()
    )

    val bookReserve = NavigationRoute(
        "${root.destination}/book-reserve",
        emptyList()
    )
    override var default = bookList
}