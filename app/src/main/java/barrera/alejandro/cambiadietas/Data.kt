package barrera.alejandro.cambiadietas

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

val categoriesData = listOf(
    R.string.fruits_category,
    R.string.proteins_fats_category,
    R.string.fats_category,
    R.string.carbohydrates_category,
    R.string.dairy_category
)

val fruitsData = listOf(
    R.drawable.fruits_blueberries to R.string.blueberries_card_text,
    R.drawable.fruits_cherries to R.string.cherries_card_text,
    R.drawable.fruits_plums to R.string.plums_card_text,
    R.drawable.fruits_datiles to R.string.datiles_card_text,
    R.drawable.fruits_raspberries to R.string.raspberries_card_text,
    /*R.drawable.fruits_strawberries to R.string.strawberries_card_text,
    R.drawable.fruits_figs to R.string.figs_card_text,
    R.drawable.fruits_kiwi to R.string.kiwi_card_text,
    R.drawable.fruits_tangerines to R.string.tangerines_card_text,
    R.drawable.fruits_mango to R.string.mango_card_text,
    R.drawable.fruits_apple to R.string.apple_card_text,
    R.drawable.fruits_peach to R.string.peach_card_text,
    R.drawable.fruits_blackberries to R.string.blackberries_card_text,
    R.drawable.fruits_orange to R.string.orange_card_text,
    R.drawable.fruits_nectarine to R.string.nectarine_card_text,
    R.drawable.fruits_nisperos to R.string.nisperos_card_text,
    R.drawable.fruits_papaya to R.string.papaya_card_text,
    R.drawable.fruits_pear to R.string.pear_card_text,
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text,
    R.drawable.fruits_banana to R.string.banana_card_text,
    R.drawable.fruits_grapes to R.string.grapes_card_text,
    R.drawable.fruits_melon to R.string.melon_card_text,
    R.drawable.fruits_watermelon to R.string.watermelon_card_text*/
).map { DrawableStringPair(it.first, it.second) }

val fatsAndProteinsData = listOf(
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text,
).map { DrawableStringPair(it.first, it.second) }

val fatsData = listOf(
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text
).map { DrawableStringPair(it.first, it.second) }

val carbohydratesData = listOf(
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text
).map { DrawableStringPair(it.first, it.second) }

val dairyData = listOf(
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text
).map { DrawableStringPair(it.first, it.second) }

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)