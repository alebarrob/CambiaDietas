package barrera.alejandro.cambiadietas.model.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.cambiadietas.R

sealed class Screen(
    val route: String,
    @StringRes val iconLabelId: Int?,
    @DrawableRes val iconImageId: Int?
) {
    object StartScreen : Screen("startScreen", R.string.bottom_navigation_start, R.drawable.ic_start)
    object SelectedFoodScreen : Screen("selectedFoodScreen", null, null)
    object CategoriesScreen : Screen("categoriesScreen", R.string.bottom_navigation_food_categories, R.drawable.ic_food_categories)
    object TipsScreen : Screen("tipsScreen", R.string.bottom_navigation_nutrition_advices, R.drawable.ic_nutrition_advices)
}

