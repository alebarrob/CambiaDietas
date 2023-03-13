package barrera.alejandro.cambiadietas.categories.presentation.category_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.categories.domain.category_detail.use_case.CategoryDetailUseCases
import barrera.alejandro.cambiadietas.core.domain.use_case.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coreUseCases: CoreUseCases,
    private val categoryDetailUseCases: CategoryDetailUseCases
) : ViewModel() {
    var state by mutableStateOf(
        CategoryDetailState(category = savedStateHandle.get<String>("category")!!)
    )
        private set

    fun loadFoodByCategory() {
        viewModelScope.launch {
            categoryDetailUseCases.getFoodByCategory(state.category).collect { food ->
                state = state.copy(
                    foods = food
                )
            }
        }
    }

    fun getDrawableId(
        foodCategory: String,
        foodName: String
    ): Int {
        return coreUseCases.getDrawableId(
            foodCategory = foodCategory,
            foodName = foodName
        )
    }
}