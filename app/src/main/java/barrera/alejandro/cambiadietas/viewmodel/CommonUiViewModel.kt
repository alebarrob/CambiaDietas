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
class CommonUiViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    private val _foodByCategory = MutableStateFlow<List<Food>>(listOf())
    val foodByCategory: Flow<List<Food>> get() = _foodByCategory

    fun onFoodByCategoryChange(category: String) {
        viewModelScope.launch {
            foodRepository.getFoodByCategory(category).collect { foodList ->
                _foodByCategory.value = foodList
            }
        }
    }






}