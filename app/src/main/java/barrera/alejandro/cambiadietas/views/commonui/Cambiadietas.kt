package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.cambiadietas.models.routes.ScreenNavigation.*
import barrera.alejandro.cambiadietas.viewmodels.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.StartScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.TipsScreenViewModel
import barrera.alejandro.cambiadietas.views.eula.EulaAlertDialog
import barrera.alejandro.cambiadietas.views.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.views.screens.StartScreen
import barrera.alejandro.cambiadietas.views.screens.TipsScreen

@Composable
fun CambiaDietas(
    startScreenViewModel: StartScreenViewModel,
    categoriesScreenViewModel: CategoriesScreenViewModel,
    tipsScreenViewModel: TipsScreenViewModel
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(StartScreen, CategoriesScreen, TipsScreen)
    var showEulaAlertDialog by remember { mutableStateOf(true) }

    if (showEulaAlertDialog) EulaAlertDialog(
        onDismiss = { showEulaAlertDialog = false },
        onExit = {  }
    )

    Box {
        CambiadietasBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                CambiaDietasBottomBar(
                    navController = navController,
                    currentDestination = currentDestination,
                    screens = screens
                )
            },
            content = { paddingValues ->
                NavHost(navController = navController, startDestination = StartScreen.route) {
                    composable(route = StartScreen.route) {
                        StartScreen(
                            paddingValues = paddingValues,
                            configuration = configuration,
                            navController = navController,
                            startScreenViewModel = startScreenViewModel,
                        )
                    }
                    composable(route = SelectedFoodScreen.route) {
                        SelectedFoodScreen(
                            paddingValues = paddingValues,
                            configuration = configuration,
                            context = context
                        )
                    }
                    composable(route = CategoriesScreen.route) {
                        CategoriesScreen(
                            paddingValues = paddingValues,
                            categoriesScreenViewModel = categoriesScreenViewModel
                        )
                    }
                    composable(route = TipsScreen.route) {
                        TipsScreen(
                            paddingValues = paddingValues,
                            configuration = configuration,
                            context = context,
                            tipsScreenViewModel = tipsScreenViewModel
                        )
                    }
                }
            }
        )
    }
}