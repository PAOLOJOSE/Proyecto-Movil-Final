package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main

import pe.edu.ulima.pm.demoextrasapp.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Add : NavDrawerItem("Cafeterias", android.R.drawable.ic_menu_add, "Add")
    object Edit : NavDrawerItem("Eventos", android.R.drawable.ic_menu_edit, "Edit")
    object Search : NavDrawerItem("Promociones", android.R.drawable.ic_menu_search, "Search")
    object Biblioteca : NavDrawerItem("Biblioteca", android.R.drawable.ic_menu_mylocation, "Location")
    object Preferences : NavDrawerItem("Noticias", android.R.drawable.ic_menu_preferences, "Preferences")
}

//DrawerItem(title = "Cafeterias", Icons.Rounded.Coffee)
//DrawerItem(title = "Eventos", Icons.Rounded.CalendarMonth)
//DrawerItem(title = "Promociones", Icons.Rounded.Sell)
//DrawerItem(title = "Biblioteca", Icons.Rounded.LibraryBooks)
//DrawerItem(title = "Noticias", Icons.Rounded.Newspaper)