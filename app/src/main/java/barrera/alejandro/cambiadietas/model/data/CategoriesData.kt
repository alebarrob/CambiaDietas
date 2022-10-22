package barrera.alejandro.cambiadietas.model.data

import barrera.alejandro.cambiadietas.R

val categoriesData = listOf(
    R.string.fruits_category,
    R.string.proteins_fats_category,
    R.string.fats_category,
    R.string.carbohydrates_category,
    R.string.dairy_category
).map { Category(it) }