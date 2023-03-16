package barrera.alejandro.cambiadietas.change_diet.presentation.select_alternative_food

sealed class SelectAlternativeFoodEvent {
    object LoadSelectedFood: SelectAlternativeFoodEvent()
    object LoadFoodsByCategory: SelectAlternativeFoodEvent()
    data class OnSelectedFoodAmountChange(val selectedFoodAmount: String): SelectAlternativeFoodEvent()
    data class OnFoodClick(
        val foodName: String,
        val foodCategory: String
    ): SelectAlternativeFoodEvent()
}
