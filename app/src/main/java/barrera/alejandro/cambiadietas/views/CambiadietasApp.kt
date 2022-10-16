package barrera.alejandro.cambiadietas.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.model.data.Screen.*
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasAppViewModel
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasBackgroundViewModel
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.FoodColumnViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiadietasBackground
import barrera.alejandro.cambiadietas.views.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.views.screens.StartScreen
import barrera.alejandro.cambiadietas.views.screens.TipsScreen
import barrera.alejandro.cambiadietas.views.theme.Cadet
import barrera.alejandro.cambiadietas.views.theme.RaisinBlack

@Composable
fun CambiaDietasApp(
    cambiaDietasAppViewModel: CambiaDietasAppViewModel,
    cambiaDietasBackgroundViewModel: CambiaDietasBackgroundViewModel,
    foodColumnViewModel: FoodColumnViewModel
) {
    val foodCategory by cambiaDietasAppViewModel.foodCategory.observeAsState(initial = "Elige una categoría")
    val food by cambiaDietasAppViewModel.food.observeAsState(initial = FoodDrawableStringAmountTriple(
            drawable = R.drawable.food_image_placeholder,
            text = R.string.food_text_placeholder,
            equivalentAmount = 0.00
        ))
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(StartScreen, CategoriesScreen, TipsScreen)

    Box {
        CambiadietasBackground(cambiaDietasBackgroundViewModel)
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                BottomNavigation {
                    screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconImageId!!),
                                    contentDescription = null
                                )
                            },
                            label = { Text(stringResource(screen.iconLabelId!!)) },
                            selectedContentColor = RaisinBlack,
                            unselectedContentColor = Cadet,
                            selected = if (screen.route == "startScreen") {
                                true
                            } else {
                                currentDestination?.hierarchy?.any { (it.route == screen.route) } == true
                            },
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            },
            content = { paddingValues ->
                NavHost(navController = navController, startDestination = StartScreen.route) {
                    composable(route = StartScreen.route) { navBackStackEntry ->
                        navBackStackEntry.arguments?.getString("Elige una categoría")

                        StartScreen(
                            paddingValues = paddingValues,
                            onNavigateToSelectedFoodScreen = {
                                navController.navigate(route = SelectedFoodScreen.route) {
                                    popUpTo(StartScreen.route)
                                }
                            },
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