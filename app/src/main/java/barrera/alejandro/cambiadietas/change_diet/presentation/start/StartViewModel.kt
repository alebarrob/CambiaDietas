package barrera.alejandro.cambiadietas.change_diet.presentation.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(

) : ViewModel() {
    var state by mutableStateOf(StartState())
        private set

    fun onEvent(event: StartEvent) {
        when (event) {
            is StartEvent.OnSelectedCategoryChange -> {

            }
        }
    }

    /*val categories: Flow<List<String>> = foodRepositoryImpl.categories

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
    }*/
}