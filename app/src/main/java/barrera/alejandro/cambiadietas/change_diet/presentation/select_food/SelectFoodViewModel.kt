package barrera.alejandro.cambiadietas.change_diet.presentation.select_food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.core.domain.use_case.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectFoodViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    var state by mutableStateOf(SelectFoodState())
        private set

    fun onEvent(event: SelectFoodEvent) {
        when (event) {
            is SelectFoodEvent.LoadCategories -> {
                viewModelScope.launch {
                    coreUseCases.getAllFoodCategories().collect { loadedCategories ->
                        state = state.copy(categories = loadedCategories)
                    }
                }
            }
            is SelectFoodEvent.OnFoodCategoryButtonClick -> {
                state = state.copy(menuIsExpanded = true)
            }
            is SelectFoodEvent.OnSelectedCategoryChange -> {
                state = state.copy(
                    selectedCategory = event.selectedCategory,
                    menuIsExpanded = false
                )
                viewModelScope.launch {
                    coreUseCases.getFoodByCategory(state.selectedCategory).collect { foods ->
                        state = state.copy(foods = foods)
                    }
                }
            }
            is SelectFoodEvent.OnFoodCategoryMenuDismissRequest -> {
                state = state.copy(menuIsExpanded = false)
            }
            is SelectFoodEvent.OnSelectedFoodChange -> {
                state = state.copy(selectedFoodName = event.selectedFoodName)
            }
        }
    }
}