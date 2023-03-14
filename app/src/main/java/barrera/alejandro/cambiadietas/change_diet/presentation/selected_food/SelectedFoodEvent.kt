package barrera.alejandro.cambiadietas.change_diet.presentation.selected_food

sealed class SelectedFoodEvent {
    object LoadSelectedFood: SelectedFoodEvent()
    object LoadFoodsByCategory: SelectedFoodEvent()
    data class OnAlternativeFoodChange(val selectedFoodName: String): SelectedFoodEvent()
    data class OnSelectedFoodAmountChange(val alternativeFoodAmount: Int): SelectedFoodEvent()
}
