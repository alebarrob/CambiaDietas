package barrera.alejandro.cambiadietas.view.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import barrera.alejandro.cambiadietas.model.routes.Screen.*
import barrera.alejandro.cambiadietas.view.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.view.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.view.screens.StartScreen
import barrera.alejandro.cambiadietas.view.screens.TipsScreen
import barrera.alejandro.cambiadietas.viewmodel.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.viewmodel.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodel.StartScreenViewModel
import barrera.alejandro.cambiadietas.viewmodel.TipsScreenViewModel

@Composable
fun CambiaDietas(
    commonUiViewModel: CommonUiViewModel,
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

    val foodByCategory by commonUiViewModel.foodByCategory.collectAsState(initial = listOf())



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
                            navController = navController,
                            startScreenViewModel = startScreenViewModel,
                            configuration = configuration,
                            foodByCategory = foodByCategory,
                            onFoodByCategoryChange = { commonUiViewModel.onFoodByCategoryChange(it) },
                        )
                    }
                    composable(route = SelectedFoodScreen.route) {
                        SelectedFoodScreen(
                            paddingValues = paddingValues,
                            foodByCategory = foodByCategory,
                            context = context,
                            configuration = configuration,
                        )
                    }
                    composable(route = CategoriesScreen.route) {
                        CategoriesScreen(
                            paddingValues = paddingValues,
                            categoriesScreenViewModel = categoriesScreenViewModel                        )
                    }
                    composable(route = TipsScreen.route) {
                        TipsScreen(
                            tipsScreenViewModel = tipsScreenViewModel,
                            paddingValues = paddingValues,
                            configuration = configuration,
                            context = context
                        )
                    }
                }
            }
        )
    }
}