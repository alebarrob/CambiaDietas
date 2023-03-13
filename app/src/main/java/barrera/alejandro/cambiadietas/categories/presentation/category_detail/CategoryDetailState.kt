package barrera.alejandro.cambiadietas.categories.presentation.category_detail

import barrera.alejandro.cambiadietas.core.domain.model.Food

data class CategoryDetailState(
    val category: String,
    val foods: List<Food> = emptyList()
)
