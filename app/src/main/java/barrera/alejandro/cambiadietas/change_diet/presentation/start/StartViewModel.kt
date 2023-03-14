package barrera.alejandro.cambiadietas.change_diet.presentation.start

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
class StartViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    var state by mutableStateOf(StartState())
        private set

    fun onEvent(event: StartEvent) {
        when (event) {
            is StartEvent.LoadCategories -> {
                viewModelScope.launch {
                    coreUseCases.getAllFoodCategories().collect { loadedCategories ->
                        state = state.copy(categories = loadedCategories)
                    }
                }
            }
            is StartEvent.OnFoodCategoryButtonClick -> {
                state = state.copy(menuIsExpanded = true)
            }
            is StartEvent.OnSelectedCategoryChange -> {
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
            is StartEvent.OnFoodCategoryMenuDismissRequest -> {
                state = state.copy(menuIsExpanded = false)
            }
            is StartEvent.OnSelectedFoodChange -> {
                state = state.copy(selectedFoodName = event.selectedFoodName)
            }
        }
    }
}