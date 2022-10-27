package barrera.alejandro.cambiadietas.models.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.cambiadietas.R

sealed class ScreenNavigation(
    val route: String,
    @StringRes val iconLabelId: Int?,
    @DrawableRes val iconImageId: Int?
) {
    object StartScreen : ScreenNavigation(
        "startScreen",
        R.string.bottom_navigation_start,
        R.drawable.ic_start
    )
    object SelectedFoodScreen : ScreenNavigation(
        "selectedFoodScreen/{selectedFoodName}/{selectedCategory}",
        null,
        null
    )
    object CategoriesScreen : ScreenNavigation(
        "categoriesScreen",
        R.string.bottom_navigation_food_categories,
        R.drawable.ic_food_categories
    )
    object TipsScreen : ScreenNavigation(
        "tipsScreen",
        R.string.bottom_navigation_nutrition_advices,
        R.drawable.ic_nutrition_advices
    )
}

