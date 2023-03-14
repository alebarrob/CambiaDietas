package barrera.alejandro.cambiadietas.change_diet.presentation.start

sealed class StartEvent {
    object LoadCategories: StartEvent()
    object OnFoodCategoryButtonClick: StartEvent()
    data class OnSelectedCategoryChange(val selectedCategory: String): StartEvent()
    object OnFoodCategoryMenuDismissRequest: StartEvent()
    data class OnSelectedFoodChange(val selectedFoodName: String): StartEvent()
}
