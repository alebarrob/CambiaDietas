package barrera.alejandro.cambiadietas.change_diet.presentation.select_food

sealed class SelectFoodEvent {
    object LoadCategories: SelectFoodEvent()
    object OnFoodCategoryButtonClick: SelectFoodEvent()
    data class OnSelectedCategoryChange(val selectedCategory: String): SelectFoodEvent()
    object OnFoodCategoryMenuDismissRequest: SelectFoodEvent()
    data class OnSelectedFoodChange(val selectedFoodName: String): SelectFoodEvent()
}
