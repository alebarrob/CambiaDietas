package barrera.alejandro.cambiadietas.change_diet.presentation.selected_food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedFoodScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    foodRepositoryImpl: FoodRepository
): ViewModel() {
    /*private val selectedFoodName = savedStateHandle.get<String>("selectedFoodName")!!
    private val selectedFoodCategory = savedStateHandle.get<String>("selectedCategory")!!

    val foodByCategory = foodRepositoryImpl.getFoodByCategory(selectedFoodCategory)

    val selectedFood = foodRepositoryImpl.getFoodByName(selectedFoodName)

    private val _alternativeFood = MutableStateFlow(
        Food(
            id = 100,
            drawableName = "food_image_placeholder",
            name = "",
            equivalentAmountForCalculations = 0.0,
            category = "",
            unit = ""
        )
    )
    val alternativeFood: Flow<Food> get() = _alternativeFood

    private val _selectedFoodAmount = MutableStateFlow("")
    val selectedFoodAmount: Flow<String> get() = _selectedFoodAmount

    private val _alternativeFoodAmount = MutableStateFlow("")
    val alternativeFoodAmount: Flow<String> get() = _alternativeFoodAmount

    private val _wrongInput = MutableStateFlow(false)
    val wrongInput: Flow<Boolean> get() = _wrongInput

    fun onAlternativeFoodChange(
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: String,
    ) {
        when {
            selectedFoodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))")) -> {
                _alternativeFood.value = alternativeFood
                onAlternativeFoodAmountChange(
                    selectedFood = selectedFood,
                    alternativeFood = alternativeFood,
                    selectedFoodAmount = selectedFoodAmount.toDouble()
                )
            } else -> _alternativeFood.value = alternativeFood
        }
    }

    fun onSelectedFoodAmountChange(
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: String
    ) {
        when {
            selectedFoodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))")) -> {
                _wrongInput.value = false
                _selectedFoodAmount.value = selectedFoodAmount
                onAlternativeFoodAmountChange(
                    selectedFood = selectedFood,
                    alternativeFood = alternativeFood,
                    selectedFoodAmount = selectedFoodAmount.toDouble()
                )
            }
            selectedFoodAmount == "" -> {
                _wrongInput.value = false
                _selectedFoodAmount.value = selectedFoodAmount
                _alternativeFoodAmount.value = selectedFoodAmount
            }
            else -> _wrongInput.value = true
        }
    }

    private fun onAlternativeFoodAmountChange(
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: Double
    ) {
        _alternativeFoodAmount.value = calculateFoodAmountEquivalence(
            selectedFood = selectedFood,
            alternativeFood = alternativeFood,
            selectedFoodAmount = selectedFoodAmount
        )
    }

    *//*
    * This function, together with selectIntermediateFoodEquivalentAmount, handles the main
    * logical operations of the application. They take the amount of food the user should eat in
    * their selected food and calculate the equivalent amount in the alternative food with a rule
    * of three. The operation is performed in two steps: first the amount is converted to an
    * intermediate food unit and then it is converted to the desired alternative food unit.
    *//*
    fun calculateFoodAmountEquivalence(
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: Double
    ): String {
        val intermediateFoodEquivalentAmount = selectIntermediateFoodEquivalentAmount()
        val intermediateFoodAmount = selectedFoodAmount * intermediateFoodEquivalentAmount / selectedFood.equivalentAmountForCalculations
        val alternativeFoodAmount = intermediateFoodAmount * alternativeFood.equivalentAmountForCalculations / intermediateFoodEquivalentAmount

        return String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
    }

    private fun selectIntermediateFoodEquivalentAmount(): Double {
        return when (selectedFoodCategory) {
            "Frutas" -> IntermediateFoodForCalculations.APPLE.equivalentAmount
            "Grasas y Proteínas" -> IntermediateFoodForCalculations.RABBIT.equivalentAmount
            "Grasas" -> IntermediateFoodForCalculations.AVOCADO.equivalentAmount
            "Carbohidratos" -> IntermediateFoodForCalculations.BREAD.equivalentAmount
            "Lácteos" -> IntermediateFoodForCalculations.GREEK_YOGURT.equivalentAmount
            else -> 0.00
        }
    }

    fun onWrongInputChange(wrongInput: Boolean) {
        _wrongInput.value = wrongInput
    }*/
}