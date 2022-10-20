package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.models.IntermediateFood
import java.util.*

class SelectedFoodScreenViewModel: ViewModel() {
    private val _foodAmount = MutableLiveData<String>()
    val foodAmount: LiveData<String> get() = _foodAmount

    private val _wrongInput = MutableLiveData<Boolean>()
    val wrongInput: LiveData<Boolean> get() = _wrongInput

    private val _alternativeFood = MutableLiveData<FoodDrawableStringAmountTriple>()
    val alternativeFood: LiveData<FoodDrawableStringAmountTriple> get() = _alternativeFood

    private val _alternativeFoodAmount = MutableLiveData<String>()
    val alternativeFoodAmount: LiveData<String> get() = _alternativeFoodAmount

    private val _foodUnit = MutableLiveData<String>()
    val foodUnit: LiveData<String> get() = _foodUnit

    private val _alternativeFoodUnit = MutableLiveData<String>()
    val alternativeFoodUnit: LiveData<String> get() = _alternativeFoodUnit

    fun onFoodAmountChange(
        foodCategory: String,
        food: FoodDrawableStringAmountTriple,
        alternativeFood: FoodDrawableStringAmountTriple,
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
        food: FoodDrawableStringAmountTriple,
        alternativeFood: FoodDrawableStringAmountTriple,
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
        food: FoodDrawableStringAmountTriple,
        foodAmount: Double,
        alternativeFood: FoodDrawableStringAmountTriple,
    ): String {
        val intermediateFoodEquivalentAmount = selectIntermediateFoodEquivalentAmount(foodCategory)
        val intermediateFoodAmount = foodAmount * intermediateFoodEquivalentAmount / food.equivalentAmount
        val alternativeFoodAmount = intermediateFoodAmount * alternativeFood.equivalentAmount / intermediateFoodEquivalentAmount

        return String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
    }

    private fun selectIntermediateFoodEquivalentAmount(foodCategory: String): Double {
        return when (foodCategory) {
            "Frutas" -> IntermediateFood.APPLE.equivalentAmount
            "Grasas y Proteínas" -> IntermediateFood.RABBIT.equivalentAmount
            "Grasas" -> IntermediateFood.AVOCADO.equivalentAmount
            "Carbohidratos" -> IntermediateFood.BREAD.equivalentAmount
            "Lácteos" -> IntermediateFood.GREEK_YOGURT.equivalentAmount
            else -> 0.00
        }
    }

    fun onAlternativeFoodChange(alternativeFood: FoodDrawableStringAmountTriple) {
        _alternativeFood.value = alternativeFood
    }

    fun updateAlternativeFoodAmount(
        foodCategory: String,
        food: FoodDrawableStringAmountTriple,
        alternativeFood: FoodDrawableStringAmountTriple,
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