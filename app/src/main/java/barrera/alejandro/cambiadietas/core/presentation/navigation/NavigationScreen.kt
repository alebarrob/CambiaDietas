package barrera.alejandro.cambiadietas.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.cambiadietas.R

sealed class NavigationScreen(
    val route: String,
    @StringRes val iconLabelId: Int? = null,
    @DrawableRes val iconImageId: Int? = null,
    @StringRes val iconImageDescription: Int? = null
) {
    object SelectFoodScreen: NavigationScreen(
        route = "selectFood",
        iconLabelId = R.string.select_food_label_text,
        iconImageId = R.drawable.ic_select_food,
        iconImageDescription = R.string.select_food_icon_description
    )
    object SelectAlternativeFoodScreen: NavigationScreen(route = "selectAlternativeFood")
    object CategoriesOverviewScreen: NavigationScreen(
        route = "categoriesOverview",
        iconLabelId = R.string.categories_label_text,
        iconImageId = R.drawable.ic_food_categories,
        iconImageDescription = R.string.categories_icon_description
    )
    object CategoryDetailScreen: NavigationScreen(route = "categoryDetail")
    object TipsScreen: NavigationScreen(
        route = "tips",
        iconLabelId = R.string.tips_label_text,
        iconImageId = R.drawable.ic_nutrition_advices,
        iconImageDescription = R.string.tips_icon_description
    )
}
