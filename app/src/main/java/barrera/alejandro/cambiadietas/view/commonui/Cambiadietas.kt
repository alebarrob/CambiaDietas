package barrera.alejandro.cambiadietas.view.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
    val selectedFoodName by commonUiViewModel.selectedFoodName.collectAsState(initial = "")
    val selectedCategory by commonUiViewModel.selectedCategory.collectAsState(initial = "Elige una categoría")

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
                    composable(
                        route = StartScreen.route
                    ) {
                        StartScreen(
                            paddingValues = paddingValues,
                            onNavigateToSelectedFoodScreen = {
                                navController.navigate(route = SelectedFoodScreen.route) {
                                    popUpTo(StartScreen.route)
                                }
                            },
                            startScreenViewModel = startScreenViewModel,
                            configuration = configuration,
                            foodByCategory = foodByCategory,
                            selectedCategory = selectedCategory,
                            onFoodByCategoryChange = { commonUiViewModel.onFoodByCategoryChange(it) },
                            onSelectedCategoryChange = { commonUiViewModel.onSelectedCategoryChange(it) },
                            onSelectedFoodNameChange = { commonUiViewModel.onSelectedFoodNameChange(it) }
                        )
                    }
                    composable(route = SelectedFoodScreen.route) {
                        SelectedFoodScreen(
                            paddingValues = paddingValues,
                            selectedCategory = selectedCategory,
                            foodByCategory = foodByCategory,
                            context = context,
                            configuration = configuration,
                            selectedFoodName = selectedFoodName
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