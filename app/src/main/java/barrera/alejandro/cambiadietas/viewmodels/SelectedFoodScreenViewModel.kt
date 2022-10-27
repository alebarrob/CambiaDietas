package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.enums.IntermediateFoodForCalculations
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.models.repositories.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SelectedFoodScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    foodRepository: FoodRepository
): ViewModel() {
    private val selectedFoodName = savedStateHandle.get<String>("selectedFoodName")!!
    private val selectedFoodCategory = savedStateHandle.get<String>("selectedCategory")!!

    val foodByCategory = foodRepository.getFoodByCategory(selectedFoodCategory)

    val selectedFood = foodRepository.getFoodByName(selectedFoodName)

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

    private fun calculateFoodAmountEquivalence(
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
}