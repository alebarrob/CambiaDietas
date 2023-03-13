package barrera.alejandro.cambiadietas.change_diet.presentation.start

import barrera.alejandro.cambiadietas.core.domain.model.Food

data class StartState(
    val menuIsExpanded: Boolean = false,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "Elige una categoría",
    val foods: List<Food> = emptyList(),
)
