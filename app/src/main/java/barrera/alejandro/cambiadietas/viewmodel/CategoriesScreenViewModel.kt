package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.model.repositories.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesScreenViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    private val _fruitItems = MutableStateFlow(listOf<Food>())
    val fruitItems: Flow<List<Food>> get() = _fruitItems

    private val _fatsProteinsItems = MutableStateFlow(listOf<Food>())
    val fatsProteinsItems: Flow<List<Food>> get() = _fatsProteinsItems

    private val _fatsItems = MutableStateFlow(listOf<Food>())
    val fatsItems: Flow<List<Food>> get() = _fatsItems

    private val _carbohydratesItems = MutableStateFlow(listOf<Food>())
    val carbohydratesItems: Flow<List<Food>> get() = _carbohydratesItems

    private val _dairyItems = MutableStateFlow(listOf<Food>())
    val dairyItems: Flow<List<Food>> get() = _dairyItems

    init {
        loadItems("Frutas") { _fruitItems.value = it }
        loadItems("Grasas y Proteínas") { _fatsProteinsItems.value = it }
        loadItems("Grasas") { _fatsItems.value = it }
        loadItems("Carbohidratos") { _carbohydratesItems.value = it }
        loadItems("Lácteos") { _dairyItems.value = it }
    }

    private fun loadItems(category: String, loadOperation: (List<Food>) -> Unit) {
        viewModelScope.launch {
            foodRepository.getFoodByCategory(category).collect { items ->
                loadOperation(items)
            }
        }
    }
}