package barrera.alejandro.cambiadietas.categories.presentation.categories_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.categories.domain.categories_overview.use_case.CategoriesOverviewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesOverviewViewModel @Inject constructor(
    private val categoriesOverviewUseCases: CategoriesOverviewUseCases
) : ViewModel() {
    var categories by mutableStateOf(emptyList<String>())
        private set

    fun loadCategories() {
        viewModelScope.launch {
            categoriesOverviewUseCases.getAllCategories().collect { loadedCategories ->
                categories = loadedCategories
            }
        }
    }
}