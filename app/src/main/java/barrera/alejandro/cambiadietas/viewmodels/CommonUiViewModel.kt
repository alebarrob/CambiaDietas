package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.*

class CommonUiViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _food = MutableLiveData<Food>()
    val food: LiveData<Food> get() = _food

    private val _foodCategory = MutableLiveData<String>()
    val foodCategory: LiveData<String> get() = _foodCategory

    private val _foodItems = MutableLiveData<List<Food>>()
    val foodItems: LiveData<List<Food>> get() = _foodItems

    fun onFoodCategoryChange(foodCategory: String) {
        _foodCategory.value = foodCategory
    }

    fun onFoodChange(food: Food) {
        _food.value = food
    }

    fun loadFoodItems(foodCategory: String) {
        _foodItems.value = selectFoodItems(foodCategory)
    }

    fun selectFoodItems(foodCategory: String): List<Food> {
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