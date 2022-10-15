package barrera.alejandro.cambiadietas.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.model.routes.Routes.*
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasAppViewModel
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasBackgroundViewModel
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasBottomBarViewModel
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.FoodColumnViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasBottomBar
import barrera.alejandro.cambiadietas.views.commonui.CambiadietasBackground
import barrera.alejandro.cambiadietas.views.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.views.screens.StartScreen
import barrera.alejandro.cambiadietas.views.screens.TipsScreen


@Composable
fun CambiaDietasApp(
    cambiaDietasAppViewModel: CambiaDietasAppViewModel,
    cambiaDietasBackgroundViewModel: CambiaDietasBackgroundViewModel,
    cambiaDietasBottomBarViewModel: CambiaDietasBottomBarViewModel,
    foodColumnViewModel: FoodColumnViewModel
) {
    val foodCategory by cambiaDietasAppViewModel.foodCategory.observeAsState(initial = "Elige una categoría")
    val food by cambiaDietasAppViewModel.food.observeAsState(initial = FoodDrawableStringAmountTriple(
            drawable = R.drawable.food_image_placeholder,
            text = R.string.food_text_placeholder,
            equivalentAmount = 0.00
        ))
    val configuration = LocalConfiguration.current
    val navigationController = rememberNavController()

    Box {
        CambiadietasBackground(cambiaDietasBackgroundViewModel)
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                CambiaDietasBottomBar(
                    navigationController = navigationController,
                    cambiaDietasBottomBarViewModel = cambiaDietasBottomBarViewModel
                )
            },
            content = { paddingValues ->
                NavHost(navController = navigationController, startDestination = StartScreen.route) {
                    composable(route = StartScreen.route) { navBackStackEntry ->
                        navBackStackEntry.arguments?.getString("Elige una categoría")

                        StartScreen(
                            paddingValues = paddingValues,
                            navigationController = navigationController,
                            foodCategory = foodCategory,
                            onFoodCategoryChange = { cambiaDietasAppViewModel.onFoodCategoryChange(it) },
                            onFoodChange = { cambiaDietasAppViewModel.onFoodChange(it) },
                            configuration = configuration,
                            foodColumnViewModel = foodColumnViewModel
                        )
                    }
                    composable(route = SelectedFoodScreen.route) {
                        SelectedFoodScreen(
                            paddingValues = paddingValues,
                            food = food,
                            foodCategory = foodCategory,
                            foodColumnViewModel = foodColumnViewModel
                        )
                    }
                    composable(route = CategoriesScreen.route) {
                        CategoriesScreen(paddingValues = paddingValues)
                    }
                    composable(route = TipsScreen.route) {
                        TipsScreen(
                            paddingValues = paddingValues,
                            configuration = configuration
                        )
                    }
                }
            }
        )
    }
}