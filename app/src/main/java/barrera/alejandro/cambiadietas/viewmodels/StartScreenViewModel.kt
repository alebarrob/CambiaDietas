package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.models.repositories.FoodRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val foodRepositoryImpl: FoodRepositoryImpl
) : ViewModel() {
    val categories: Flow<List<String>> = foodRepositoryImpl.categories

    private val _foodByCategory = MutableStateFlow<List<Food>>(listOf())
    val foodByCategory: Flow<List<Food>> get() = _foodByCategory

    private val _selectedCategory = MutableStateFlow("Elige una categoría")
    val selectedCategory: Flow<String> get() = _selectedCategory

    fun onFoodByCategoryChange(category: String) {
        viewModelScope.launch {
            foodRepositoryImpl.getFoodByCategory(category).collect { foodList ->
                _foodByCategory.value = foodList
            }
        }
    }
    fun onSelectedCategoryChange(selectedCategory: String) {
        _selectedCategory.value = selectedCategory
    }
}