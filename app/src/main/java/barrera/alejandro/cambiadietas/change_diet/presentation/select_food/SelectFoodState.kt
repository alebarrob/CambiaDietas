package barrera.alejandro.cambiadietas.change_diet.presentation.select_food

import barrera.alejandro.cambiadietas.core.domain.model.Food

data class SelectFoodState(
    val menuIsExpanded: Boolean = false,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "Elige una categoría",
    val foods: List<Food> = emptyList(),
    val selectedFoodName: String = ""
)
