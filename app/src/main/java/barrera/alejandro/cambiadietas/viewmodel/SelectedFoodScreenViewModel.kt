package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.model.IntermediateFoodForCalculations
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.model.repositories.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SelectedFoodScreenViewModel @Inject constructor(
    private val foodRepository: FoodRepository
): ViewModel() {
    private val _selectedFood = MutableStateFlow(
        Food(
            id = 100,
            drawableName = "food_image_placeholder",
            name = "",
            equivalentAmountForCalculations = 0.0,
            category = "",
            unit = ""
        )
    )
    val selectedFood: Flow<Food> get() = _selectedFood

    private val _selectedFoodAmount = MutableStateFlow("")
    val selectedFoodAmount: Flow<String> get() = _selectedFoodAmount

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

    private val _alternativeFoodAmount = MutableStateFlow("")
    val alternativeFoodAmount: Flow<String> get() = _alternativeFoodAmount

    private val _wrongInput = MutableStateFlow(false)
    val wrongInput: Flow<Boolean> get() = _wrongInput

    fun onSelectedFoodChange(name: String) {
        viewModelScope.launch {
            foodRepository.getFoodByName(name).collect { food ->
                _selectedFood.value = food
            }
        }
    }

    fun onAlternativeFoodChange(
        selectedCategory: String,
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: String,
    ) {
        if (selectedFoodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
            _alternativeFood.value = alternativeFood
            onAlternativeFoodAmountChange(
                selectedCategory = selectedCategory,
                selectedFood = selectedFood,
                alternativeFood = alternativeFood,
                selectedFoodAmount = selectedFoodAmount.toDouble()
            )
        }

    }

    fun onSelectedFoodAmountChange(
        selectedCategory: String,
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: String
    ) {
        when {
            selectedFoodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))")) -> {
                _wrongInput.value = false
                _selectedFoodAmount.value = selectedFoodAmount
                onAlternativeFoodAmountChange(
                    selectedCategory = selectedCategory,
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
            else -> {
                _wrongInput.value = true
            }
        }
    }

    private fun onAlternativeFoodAmountChange(
        selectedCategory: String,
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: Double
    ) {
        _alternativeFoodAmount.value = calculateFoodAmountEquivalence(
            selectedCategory = selectedCategory,
            selectedFood = selectedFood,
            selectedFoodAmount = selectedFoodAmount,
            alternativeFood = alternativeFood
        )
    }

    private fun calculateFoodAmountEquivalence(
        selectedCategory: String,
        selectedFood: Food,
        selectedFoodAmount: Double,
        alternativeFood: Food,
    ): String {
        val intermediateFoodEquivalentAmount = selectIntermediateFoodEquivalentAmount(selectedCategory)
        val intermediateFoodAmount = selectedFoodAmount * intermediateFoodEquivalentAmount / selectedFood.equivalentAmountForCalculations
        val alternativeFoodAmount = intermediateFoodAmount * alternativeFood.equivalentAmountForCalculations / intermediateFoodEquivalentAmount

        return String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
    }

    private fun selectIntermediateFoodEquivalentAmount(foodCategory: String): Double {
        return when (foodCategory) {
            "Frutas" -> IntermediateFoodForCalculations.APPLE.equivalentAmount
            "Grasas y Proteínas" -> IntermediateFoodForCalculations.RABBIT.equivalentAmount
            "Grasas" -> IntermediateFoodForCalculations.AVOCADO.equivalentAmount
            "Carbohidratos" -> IntermediateFoodForCalculations.BREAD.equivalentAmount
            "Lácteos" -> IntermediateFoodForCalculations.GREEK_YOGURT.equivalentAmount
            else -> 0.00
        }
    }
}