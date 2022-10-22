package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.model.entities.IntermediateFoodForCalculations
import java.util.*

class SelectedFoodScreenViewModel: ViewModel() {
    private val _foodAmount = MutableLiveData<String>()
    val foodAmount: LiveData<String> get() = _foodAmount

    private val _wrongInput = MutableLiveData<Boolean>()
    val wrongInput: LiveData<Boolean> get() = _wrongInput

    private val _alternativeFood = MutableLiveData<Food>()
    val alternativeFood: LiveData<Food> get() = _alternativeFood

    private val _alternativeFoodAmount = MutableLiveData<String>()
    val alternativeFoodAmount: LiveData<String> get() = _alternativeFoodAmount

    private val _foodUnit = MutableLiveData<String>()
    val foodUnit: LiveData<String> get() = _foodUnit

    private val _alternativeFoodUnit = MutableLiveData<String>()
    val alternativeFoodUnit: LiveData<String> get() = _alternativeFoodUnit

    fun onFoodAmountChange(
        foodCategory: String,
        food: Food,
        alternativeFood: Food,
        foodAmount: String
    ) {
        when {
            foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))")) -> {
                _wrongInput.value = false
                _foodAmount.value = foodAmount
                onAlternativeFoodAmountChange(
                    foodCategory = foodCategory,
                    food = food,
                    alternativeFood = alternativeFood,
                    foodAmount = foodAmount.toDouble()
                )
            }
            foodAmount == "" -> {
                _wrongInput.value = false
                _foodAmount.value = foodAmount
                _alternativeFoodAmount.value = foodAmount
            }
            else -> {
                _wrongInput.value = true
            }
        }
    }

    private fun onAlternativeFoodAmountChange(
        foodCategory: String,
        food: Food,
        alternativeFood: Food,
        foodAmount: Double
    ) {
        _alternativeFoodAmount.value = calculateFoodAmountEquivalence(
            foodCategory = foodCategory,
            food = food,
            foodAmount = foodAmount,
            alternativeFood = alternativeFood
        )
    }

    private fun calculateFoodAmountEquivalence(
        foodCategory: String,
        food: Food,
        foodAmount: Double,
        alternativeFood: Food,
    ): String {
        val intermediateFoodEquivalentAmount = selectIntermediateFoodEquivalentAmount(foodCategory)
        val intermediateFoodAmount = foodAmount * intermediateFoodEquivalentAmount / food.equivalentAmount
        val alternativeFoodAmount = intermediateFoodAmount * alternativeFood.equivalentAmount / intermediateFoodEquivalentAmount

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

    fun onAlternativeFoodChange(alternativeFood: Food) {
        _alternativeFood.value = alternativeFood
    }

    fun updateAlternativeFoodAmount(
        foodCategory: String,
        food: Food,
        alternativeFood: Food,
        foodAmount: String,
    ) {
        if (foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
            onAlternativeFoodAmountChange(
                foodCategory = foodCategory,
                food = food,
                alternativeFood = alternativeFood,
                foodAmount = foodAmount.toDouble()
            )
        }
    }

    fun loadFoodUnit(foodUnit: String) {
        _foodUnit.value = selectMeasurementUnit(foodUnit)
    }

    fun loadAlternativeFoodUnit(alternativeFoodUnit: String) {
        _alternativeFoodUnit.value = selectMeasurementUnit(alternativeFoodUnit)
    }

    private fun selectMeasurementUnit(foodName: String): String {
        return when (foodName) {
            "" -> ""
            "Leche desnatada" -> "ml."
            "Huevo entero XL", "Huevo (Yema)", "Tortitas de arroz o maíz" -> "unidades"
            else -> "gr."
        }
    }
}