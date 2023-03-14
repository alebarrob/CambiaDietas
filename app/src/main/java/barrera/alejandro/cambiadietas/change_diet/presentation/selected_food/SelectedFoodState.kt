package barrera.alejandro.cambiadietas.change_diet.presentation.selected_food

import barrera.alejandro.cambiadietas.core.domain.model.Food

data class SelectedFoodState(
    val selectedFoodName: String,
    val selectedFood: Food = Food(
        id = 100,
        name = "",
        equivalentAmount = 0.0,
        category = "",
        unit = ""
    ),
    val selectedFoodCategory: String,
    val selectedFoodAmount: String = "",
    val alternativeFood: Food = Food(
        id = 100,
        name = "",
        equivalentAmount = 0.0,
        category = "",
        unit = ""
    ),
    val alternativeFoodAmount: String = "",
    val foods: List<Food> = emptyList()
)
