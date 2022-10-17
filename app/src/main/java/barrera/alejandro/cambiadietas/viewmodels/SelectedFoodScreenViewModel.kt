package barrera.alejandro.cambiadietas.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.model.data.IntermediateFood
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

    fun updateAlternativeFoodAmount(
        foodAmount: String,
        foodCategory: String,
        food: FoodDrawableStringAmountTriple,
    ) {
        if (foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
            onAlternativeFoodAmountChange(
                foodCategory = foodCategory,
                food = food
            )
        }
    }

    fun onFoodAmountChange(
        foodAmount: String,
        foodCategory: String,
        food: FoodDrawableStringAmountTriple,
        context: Context
    ) {
        when {
            foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))")) -> {
                _wrongInput.value = false
                _foodAmount.value = foodAmount
                onAlternativeFoodAmountChange(
                    foodCategory = foodCategory,
                    food = food
                )
            }
            foodAmount == "" -> {
                _wrongInput.value = false
                _foodAmount.value = foodAmount
                _alternativeFoodAmount.value = foodAmount
            }
            else -> {
                _wrongInput.value = true
                Toast.makeText(context, "Has introducido un valor incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onAlternativeFoodAmountChange(
        foodCategory: String,
        food: FoodDrawableStringAmountTriple
    ) {
        _alternativeFoodAmount.value = calculateFoodAmountEquivalence(
            foodCategory = foodCategory,
            food = food,
            foodAmount = _foodAmount.value!!.toDouble(),
            alternativeFood = _alternativeFood.value ?: FoodDrawableStringAmountTriple(
                drawable = R.drawable.food_image_placeholder,
                text = R.string.food_text_placeholder,
                equivalentAmount = 0.00
            )
        )
    }

    private fun calculateFoodAmountEquivalence(
        foodCategory: String,
        food: FoodDrawableStringAmountTriple,
        foodAmount: Double,
        alternativeFood: FoodDrawableStringAmountTriple,
    ): String {
        val intermediateFoodEquivalentAmount = loadIntermediateFoodEquivalentAmount(foodCategory)
        val intermediateFoodAmount = foodAmount * intermediateFoodEquivalentAmount / food.equivalentAmount
        val alternativeFoodAmount = intermediateFoodAmount * alternativeFood.equivalentAmount / intermediateFoodEquivalentAmount

        return String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
    }

    private fun loadIntermediateFoodEquivalentAmount(foodCategory: String): Double {
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

    fun onFoodUnitChange(foodUnit: String) {
        _foodUnit.value = foodUnit
    }

    fun onAlternativeFoodUnitChange(alternativeFoodUnit: String) {
        _alternativeFoodUnit.value = alternativeFoodUnit
    }

    /*fun copyFoodAmount() {
        if (alternativeFoodAmount.value!!.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
            _alternativeFoodAmount.value = _foodAmount.value
        }
    }*/
}