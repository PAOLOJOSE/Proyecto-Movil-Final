package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main

import LibraryScreenRoutes
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import pe.edu.ulima.pm.demoextrasapp.R
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.SearchScreen

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookCommentaries
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookDetail
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookList
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera.AdminClubMembershipScanScreen
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookReserve
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.shared.DrawerLayout
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.shared.TopBar

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels.LibraryViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerBackgroundColor = colorResource(id = R.color.white),
        drawerContent = {
            DrawerLayout(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
    ) {
        ComposeNavigation(
            navController = navController,
        )
    }
}

@Composable
fun ComposeNavigation(navController: NavHostController) {

    val context = LocalContext.current
    val libraryViewModel = hiltViewModel<LibraryViewModel>()

    val selectedBook by libraryViewModel.selectedBook.observeAsState()
    val selectedTitulo by libraryViewModel.selectedTitulo.observeAsState("")
    val booksFound by libraryViewModel.books.observeAsState(emptyList())

    NavHost(navController, startDestination = NavDrawerItem.Coffee.route) {
        composable(NavDrawerItem.Coffee.route) {

        }
        composable(NavDrawerItem.Events.route) {

        }
        composable(NavDrawerItem.Promotion.route) {
        }

        composable(LibraryScreenRoutes.Camera.route) {
            AdminClubMembershipScanScreen(navController)
        }

        composable(NavDrawerItem.Library.route) {
            val scanLauncher = rememberLauncherForActivityResult(
                contract = ScanContract(),
                onResult = { result ->
                    libraryViewModel.setSelectedTitulo(titulo = result.contents)
                }
            )

            SearchScreen(
                onAccessCameraClick = {
                    scanLauncher.launch(ScanOptions())
                }, onAccessToLibraryClick = {
                    libraryViewModel.setSelectedTitulo(titulo = it)
                    navController.navigate(LibraryScreenRoutes.BookList.route)
                },
                onRulesClick = {
                    linkToWebpage(context = context)
                }
            )
        }

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

        composable(
            LibraryScreenRoutes.Comments.route
        ) { backStackEntry ->
            val libraryViewModel = hiltViewModel<LibraryViewModel>()
            BookCommentaries(
                backStackEntry.arguments?.getInt("bookId"), libraryViewModel, navController
            )
        }

        composable(
            LibraryScreenRoutes.Reserve.route
        ) {
            BookReserve(
                navController,
                libraryViewModel
            )
        }

        composable(NavDrawerItem.News.route) {
        }
    }
}

fun linkToWebpage(context: Context) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse("https://www.ulima.edu.pe/departamento/biblioteca/reglamento")
    startActivity(context, openURL, null)
}