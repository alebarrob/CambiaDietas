package barrera.alejandro.cambiadietas.model.routes

sealed class Routes(val route: String) {
    object StartScreen : Routes("startScreen")
    object SelectedFoodScreen : Routes("selectedFoodScreen")
    object CategoriesScreen : Routes("categoriesScreen")
    object TipsScreen : Routes("tipsScreen")
}