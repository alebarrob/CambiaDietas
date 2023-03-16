package barrera.alejandro.cambiadietas.change_diet.presentation.select_alternative_food

import barrera.alejandro.cambiadietas.core.domain.model.Food

data class SelectAlternativeFoodState(
    val selectedFoodName: String,
    val selectedFoodCategory: String,
    val foodsByCategory: List<Food> = emptyList(),
    val selectedFood: Food = Food(
        id = 100,
        name = "",
        equivalentAmount = 0.0,
        category = "",
        unit = ""
    ),
    val selectedFoodAmount: String = "",
    val alternativeFood: Food = Food(
        id = 100,
        name = "",
        equivalentAmount = 0.0,
        category = "",
        unit = ""
    ),
    val alternativeFoodAmount: String = ""
)
