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
class StartScreenViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    val categories: Flow<List<String>> = foodRepository.categories

    private val _selectedCategory = MutableStateFlow("Elige una categoría")
    val selectedCategory: Flow<String> get() = _selectedCategory

    fun onSelectedCategoryChange(selectedCategory: String) {
        _selectedCategory.value = selectedCategory
    }


}