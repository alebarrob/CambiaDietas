package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.models.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.models.Screen.*
import barrera.alejandro.cambiadietas.viewmodels.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodels.StartScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.TipsScreenViewModel
import barrera.alejandro.cambiadietas.views.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.views.screens.StartScreen
import barrera.alejandro.cambiadietas.views.screens.TipsScreen

@Composable
fun CambiaDietasApp(
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

    val foodCategory by commonUiViewModel.foodCategory.observeAsState(initial = "Elige una categoría")
    val food by commonUiViewModel.food.observeAsState(initial = FoodDrawableStringAmountTriple(
            drawable = R.drawable.food_image_placeholder,
            text = R.string.food_text_placeholder,
            equivalentAmount = 0.00
        )
    )
    val foodItems by commonUiViewModel.foodItems.observeAsState(initial = listOf())

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
                            onNavigateToSelectedFoodScreen = {
                                navController.navigate(route = SelectedFoodScreen.route) {
                                    popUpTo(StartScreen.route)
                                }
                            },
                            foodCategory = foodCategory,
                            onFoodCategoryChange = { commonUiViewModel.onFoodCategoryChange(it) },
                            onFoodChange = { commonUiViewModel.onFoodChange(it) },
                            foodItems = foodItems,
                            commonUiViewModel = commonUiViewModel,
                            startScreenViewModel = startScreenViewModel
                        )
                    }
                    composable(route = SelectedFoodScreen.route) {
                        SelectedFoodScreen(
                            paddingValues = paddingValues,
                            food = food,
                            foodCategory = foodCategory,
                            foodItems = foodItems,
                            commonUiViewModel = commonUiViewModel,
                            context = context
                        )
                    }
                    composable(route = CategoriesScreen.route) {
                        CategoriesScreen(
                            paddingValues = paddingValues,
                            commonUiViewModel = commonUiViewModel,
                            categoriesScreenViewModel = categoriesScreenViewModel
                        )
                    }
                    composable(route = TipsScreen.route) {
                        TipsScreen(
                            paddingValues = paddingValues,
                            configuration = configuration,
                            tipsScreenViewModel = tipsScreenViewModel,
                            context = context
                        )
                    }
                }
            }
        )
    }
}