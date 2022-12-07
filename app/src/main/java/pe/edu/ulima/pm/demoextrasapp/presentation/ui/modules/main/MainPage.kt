package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.main

import DrawerItem
import LibraryScreenRoutes
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.demoextrasapp.R
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera.MyCameraScreen
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.SearchScreen

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookCommentaries
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookDetail
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.BookList
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera.AdminClubMembershipScanScreen
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.library.BookReserve

import pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.shared.AppTopBar
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
        drawerBackgroundColor = colorResource(id = R.color.purple_700),
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
fun DrawerLayout(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Add,
        NavDrawerItem.Edit,
        NavDrawerItem.Search,
        NavDrawerItem.Biblioteca,
        NavDrawerItem.Preferences
    )
    Column {
// Header
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = R.drawable.ic_launcher_foreground.toString(),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    }
}

@Composable
fun ComposeNavigation(navController: NavHostController) {

    val context = LocalContext.current
    val libraryViewModel = hiltViewModel<LibraryViewModel>()

    val selectedBook by libraryViewModel.selectedBook.observeAsState()
    val selectedTitulo by libraryViewModel.selectedTitulo.observeAsState("")
    val booksFound by libraryViewModel.books.observeAsState(emptyList())

    NavHost(navController, startDestination = NavDrawerItem.Add.route) {
        composable(NavDrawerItem.Add.route) {

        }
        composable(NavDrawerItem.Edit.route) {

        }
        composable(NavDrawerItem.Search.route) {
        }

        // camera
        composable(LibraryScreenRoutes.Camera.route) {
            AdminClubMembershipScanScreen(navController)
        }

        // search
        composable(NavDrawerItem.Biblioteca.route) {
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


        composable(NavDrawerItem.Preferences.route) {
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    libraryViewModel: LibraryViewModel,
    context: Context
) {



    NavHost(navController = navController, startDestination = startDestination) {



    }
}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    )
}

fun linkToWebpage(context: Context) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse("https://www.ulima.edu.pe/departamento/biblioteca/reglamento")
    startActivity(context, openURL, null)
}