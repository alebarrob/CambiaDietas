package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.*

class CommonUiViewModel : ViewModel() {
    private val _foodCategory = MutableLiveData<String>()
    val foodCategory: LiveData<String> get() = _foodCategory

    private val _food = MutableLiveData<FoodDrawableStringAmountTriple>()
    val food: LiveData<FoodDrawableStringAmountTriple> get() = _food

    private val _foodItems = MutableLiveData<List<FoodDrawableStringAmountTriple>>()
    val foodItems: LiveData<List<FoodDrawableStringAmountTriple>> get() = _foodItems

    fun onFoodCategoryChange(foodCategory: String) {
        _foodCategory.value = foodCategory
    }

    fun onFoodChange(food: FoodDrawableStringAmountTriple) {
        _food.value = food
    }

    fun loadFoodItems(foodCategory: String) {
        _foodItems.value = selectFoodItems(foodCategory)
    }

    fun selectFoodItems(foodCategory: String): List<FoodDrawableStringAmountTriple> {
        return when (foodCategory) {
            "Frutas" -> fruitsData
            "Grasas y Proteínas" -> fatsAndProteinsData
            "Grasas" -> fatsData
            "Carbohidratos" -> carbohydratesData
            "Lácteos" -> dairyData
            else -> listOf()
        }
    }
}