package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main

import AppDrawer
import LibraryScreenRoutes
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera.MyCameraScreen
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.SearchScreen

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookCommentaries
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookDetail
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookList
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera.AdminClubMembershipScanScreen
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookReserve

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.shared.AppTopBar
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels.LibraryViewModel


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
                navController = navController, startDestination = LibraryScreenRoutes.Search.route
            )
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
) {
    val libraryViewModel = hiltViewModel<LibraryViewModel>()

    val selectedBook by libraryViewModel.selectedBook.observeAsState()
    val selectedTitulo by libraryViewModel.selectedTitulo.observeAsState("")
    val booksFound by libraryViewModel.books.observeAsState(emptyList())

    NavHost(navController = navController, startDestination = startDestination) {

        // camera
        composable(LibraryScreenRoutes.Camera.route) {



            AdminClubMembershipScanScreen(navController)
        }

        // search
        composable(LibraryScreenRoutes.Search.route) {
            val scanLauncher = rememberLauncherForActivityResult(
                contract = ScanContract(),
                onResult = { result ->
                    libraryViewModel.setSelectedTitulo(titulo = result.contents)
                }
            )

            SearchScreen(
                onAccessCameraClick = {
                    scanLauncher.launch(ScanOptions())
//                    navController.navigate(LibraryScreenRoutes.Camera.route)
                }, onAccessToLibraryClick = {
                    libraryViewModel.setSelectedTitulo(titulo = it)
                    navController.navigate(LibraryScreenRoutes.BookList.route)
                })
        }


        // lista de libros
        composable(
            LibraryScreenRoutes.BookList.route
        ) {
            // request books
            libraryViewModel.listBooks(selectedTitulo)

            BookList(booksList = booksFound, onBookClick = { book ->
                libraryViewModel.setSelectedBook(book)
                navController.navigate(LibraryScreenRoutes.BookDetail.route)
            })
        }

        // detalle del libro
        composable(
            LibraryScreenRoutes.BookDetail.route
        ) {
            BookDetail(
                selectedBook,
                libraryViewModel,
                onReadCommentsClick = {
                    navController.navigate(LibraryScreenRoutes.Comments.route)
                },
                onReserveClick = {
                    navController.navigate(LibraryScreenRoutes.Reserve.route)
                }
            )
        }

        // comentarios
        composable(
            LibraryScreenRoutes.Comments.route
        ) { backStackEntry ->
            val libraryViewModel = hiltViewModel<LibraryViewModel>()
            BookCommentaries(
                backStackEntry.arguments?.getInt("bookId"), libraryViewModel, navController
            )
        }

        // reserva de libro
        composable(
            LibraryScreenRoutes.Reserve.route
        ) {
            BookReserve(
                navController,
                libraryViewModel
            )
        }

    }
}