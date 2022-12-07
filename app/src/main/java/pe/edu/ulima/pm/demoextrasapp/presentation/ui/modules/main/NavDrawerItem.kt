package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main

import pe.edu.ulima.pm.demoextrasapp.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Coffee : NavDrawerItem("CoffeScreen", R.drawable.ic_coffee, "Cafeterias")
    object Events : NavDrawerItem("EventsScreen", R.drawable.ic_calendar, "Eventos")
    object Promotion : NavDrawerItem("PromotionScreen", R.drawable.ic_tag, "Promociones")
    object Library : NavDrawerItem("LibraryScreen", R.drawable.ic_open_book, "Biblioteca")
    object News : NavDrawerItem("NewsScreen", R.drawable.ic_new, "Noticias")
}