package barrera.alejandro.cambiadietas.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.cambiadietas.core.presentation.navigation.NavigationScreen.*
import barrera.alejandro.cambiadietas.categories.presentation.categories_overview.CategoriesOverviewScreen
import barrera.alejandro.cambiadietas.categories.presentation.category_detail.CategoryDetailScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.change_diet.presentation.start.StartScreen
import barrera.alejandro.cambiadietas.tips.presentation.TipsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = StartScreen.route
    ) {
        composable(route = StartScreen.route) {
            StartScreen(
                paddingValues = paddingValues,
                onNavigateToSelectedFood = { food, category ->
                    navController.navigate(
                        route = SelectedFoodScreen.route +
                                "/$food" +
                                "/$category"
                    )
                }
            )
        }
        composable(route = SelectedFoodScreen.route) {
            SelectedFoodScreen(paddingValues = paddingValues)
        }
        composable(route = CategoriesOverviewScreen.route) {
            CategoriesOverviewScreen(
                paddingValues = paddingValues,
                onNavigateToCategoryDetail = { category ->
                    navController.navigate(CategoryDetailScreen.route + "/$category")
                }
            )
        }
        composable(route = CategoryDetailScreen.route + "/{category}") {
            CategoryDetailScreen(paddingValues = paddingValues)
        }
        composable(route = TipsScreen.route) {
            TipsScreen(paddingValues = paddingValues)
        }
    }
}