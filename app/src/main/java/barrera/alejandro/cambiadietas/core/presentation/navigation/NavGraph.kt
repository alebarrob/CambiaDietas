package barrera.alejandro.cambiadietas.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.cambiadietas.core.presentation.navigation.NavigationScreen.*
import barrera.alejandro.cambiadietas.categories.presentation.categories_overview.CategoriesOverviewScreen
import barrera.alejandro.cambiadietas.categories.presentation.category_detail.CategoryDetailScreen
import barrera.alejandro.cambiadietas.change_diet.presentation.select_alternative_food.SelectedFoodScreen
import barrera.alejandro.cambiadietas.change_diet.presentation.select_food.SelectFoodScreen
import barrera.alejandro.cambiadietas.tips.presentation.TipsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = SelectFoodScreen.route
    ) {
        composable(route = SelectFoodScreen.route) {
            SelectFoodScreen(
                paddingValues = paddingValues,
                onNavigateToSelectedFood = { foodName, foodCategory ->
                    navController.navigate(
                        route = SelectAlternativeFoodScreen.route +
                                "/$foodName" +
                                "/$foodCategory"
                    )
                }
            )
        }
        composable(route = SelectAlternativeFoodScreen.route + "/{foodName}/{foodCategory}") {
            SelectedFoodScreen(paddingValues = paddingValues)
        }
        composable(route = CategoriesOverviewScreen.route) {
            CategoriesOverviewScreen(
                paddingValues = paddingValues,
                onNavigateToCategoryDetail = { foodCategory ->
                    navController.navigate(CategoryDetailScreen.route + "/$foodCategory")
                }
            )
        }
        composable(route = CategoryDetailScreen.route + "/{foodCategory}") {
            CategoryDetailScreen(paddingValues = paddingValues)
        }
        composable(route = TipsScreen.route) {
            TipsScreen(paddingValues = paddingValues)
        }
    }
}