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
    R.drawable.blueberries to R.string.blueberries_card_text,
    R.drawable.cherries to R.string.cherries_card_text,
    R.drawable.fruits_plums to R.string.plums_card_text,
    R.drawable.fruits_datiles to R.string.datiles_card_text,
    R.drawable.fruits_raspberries to R.string.raspberries_card_text,
    R.drawable.fruits_strawberries to R.string.strawberries_card_text,
    R.drawable.fruits_figs to R.string.figs_card_text,
    R.drawable.fruits_kiwi to R.string.kiwi_card_text,
    R.drawable.fruits_tangerines to R.string.tangerines_card_text,
    R.drawable.fruits_mango to R.string.mango_card_text,
    R.drawable.fruits_apple to R.string.apple_card_text,
    R.drawable.fruits_peach to R.string.peach_card_text,
    R.drawable.fruits_melon to R.string.melon_card_text,
    R.drawable.fruits_blackberries to R.string.blackberries_card_text,
    R.drawable.fruits_orange to R.string.orange_card_text,
    R.drawable.fruits_nectarine to R.string.nectarine_card_text,
    R.drawable.fruits_nisperos to R.string.nisperos_card_text,
    R.drawable.fruits_papaya to R.string.papaya_card_text,
    R.drawable.fruits_pear to R.string.pear_card_text,
    R.drawable.fruits_pinneaple to R.string.pinneaple_card_text,
    R.drawable.fruits_banana to R.string.banana_card_text,
    R.drawable.fruits_watermelon to R.string.watermelon_card_text,
    R.drawable.fruits_grapes to R.string.grapes_card_text,
).map { DrawableStringPair(it.first, it.second) }

val fatsAndProteinsData = listOf(
    R.drawable.tuna to R.string.tuna_card_text,
    R.drawable.seafood to R.string.seafood_card_text,
    R.drawable.fats_proteins_pork to R.string.pork_card_text,
    R.drawable.fats_proteins_eggs_whites to R.string.eggs_whites_card_text,
    R.drawable.fats_proteins_rabbit to R.string.rabbit_card_text,
    R.drawable.fats_proteins_lamb to R.string.lamb_card_text,
    R.drawable.fats_proteins_gluten to R.string.gluten_card_text,
    R.drawable.fats_proteins_egg to R.string.egg_card_text,
    R.drawable.fats_proteins_spanish_jam to R.string.spanish_jam_card_text,
    R.drawable.fats_proteins_pork_loin to R.string.pork_loin_card_text,
    R.drawable.fats_proteins_tenderloin to R.string.tenderloin_card_text,
    R.drawable.fats_proteins_white_fish to R.string.white_fish_card_text,
    R.drawable.fats_proteins_chicken to R.string.chicken_card_text,
    /*R.drawable.fats_proteins_protein_powder to R.string.protein_powder_card_text,
    R.drawable.fats_proteins_cottage_cheese to R.string.cottage_cheese_card_text,
    R.drawable.fats_proteins_salmon to R.string.salmon_card_text,
    R.drawable.fats_proteins_seitan to R.string.seitan_card_text,
    R.drawable.fats_proteins_fat_beef to R.string.fat_beef_card_text,
    R.drawable.fats_proteins_lean_beef to R.string.lean_beef_card_text,
    R.drawable.fats_proteins_tofu to R.string.tofu_card_text,
    R.drawable.fats_proteins_burgos_cheese to R.string.burgos_cheese_card_text,
    R.drawable.fats_proteins_cheese to R.string.cheese_card_text,
    R.drawable.fats_proteins_fresh_cheese to R.string.fresh_cheese_card_text*/
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