package pe.edu.ulima.pm.demoextrasapp.ui.modules

import AppDrawer
import LibraryDirections
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import kotlinx.coroutines.launch

import pe.edu.ulima.pm.demoextrasapp.ui.modules.library.BookCommentaries
import pe.edu.ulima.pm.demoextrasapp.ui.modules.library.BookDetail
import pe.edu.ulima.pm.demoextrasapp.ui.modules.library.BookList
import pe.edu.ulima.pm.demoextrasapp.ui.modules.library.BookReserve

import pe.edu.ulima.pm.demoextrasapp.ui.modules.shared.AppTopBar
import pe.edu.ulima.pm.demoextrasapp.ui.viewModels.LibraryViewModel


@Composable
fun MainPage() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()


    Scaffold(scaffoldState = scaffoldState, drawerContent = { AppDrawer() }, topBar = {
        AppTopBar(title = "Biblioteca", openDrawer = {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        })
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            AppNavigation(
                navController = navController,
                startDestination = LibraryDirections.root.destination
            )


        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
) {


    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            startDestination = LibraryDirections.default.destination,
            route = LibraryDirections.root.destination
        ) {
            composable(
                LibraryDirections.bookList.destination, LibraryDirections.bookList.arguments
            ) {
                val libraryViewModel = hiltViewModel<LibraryViewModel>()
                BookList(libraryViewModel, navController)
            }
            composable(
                "${LibraryDirections.bookCommentaries.destination}/{bookId}",
                LibraryDirections.bookCommentaries.arguments
            ) {
                BookCommentaries(navController)
            }
            composable(
                "${LibraryDirections.bookDetail.destination}/{bookId}",
                LibraryDirections.bookDetail.arguments
            ) {
                BookDetail(navController)
            }
            composable(
                "${LibraryDirections.bookReserve.destination}/{bookId}",
                LibraryDirections.bookReserve.arguments
            ) {
                BookReserve(navController)
            }

        }

    }

}