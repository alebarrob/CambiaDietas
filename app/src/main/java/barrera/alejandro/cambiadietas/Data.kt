package barrera.alejandro.cambiadietas

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

val categoriesData = listOf(
    R.string.fruit_category,
    R.string.protein_category,
    R.string.lacteos_category
)

val foodData = listOf(
    R.drawable.pinneaple to R.string.pinneaple_card_text,
    R.drawable.orange to R.string.orange_card_text,
    R.drawable.apple to R.string.apple_card_text,
    R.drawable.grapes to R.string.grapes_card_text,
    R.drawable.chocolate to R.string.chocolate_card_text
).map { DrawableStringPair(it.first, it.second) }

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)