package barrera.alejandro.cambiadietas.model.routes

import androidx.annotation.StringRes
import barrera.alejandro.cambiadietas.R

sealed class Screen(val route: String, @StringRes val resourceId: Int?) {
    object StartScreen : Screen("startScreen", R.string.bottom_navigation_start)
    object SelectedFoodScreen : Screen("selectedFoodScreen", null)
    object CategoriesScreen : Screen("categoriesScreen", R.string.bottom_navigation_food_categories)
    object TipsScreen : Screen("tipsScreen", R.string.bottom_navigation_nutrition_advices)
}

