package barrera.alejandro.cambiadietas.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import barrera.alejandro.cambiadietas.R
import kotlinx.android.parcel.Parcelize

val categoriesData = listOf(
    R.string.fruits_category,
    R.string.proteins_fats_category,
    R.string.fats_category,
    R.string.carbohydrates_category,
    R.string.dairy_category
)

val fruitsData = listOf(
    R.drawable.fruits_blueberries to R.string.blueberries_text,
    R.drawable.fruits_cherries to R.string.cherries_text,
    R.drawable.fruits_plums to R.string.plums_text,
    R.drawable.fruits_dates to R.string.dates_text,
    R.drawable.fruits_raspberries to R.string.raspberries_text,
    R.drawable.fruits_strawberries to R.string.strawberries_text,
    R.drawable.fruits_figs to R.string.figs_text,
    R.drawable.fruits_kiwi to R.string.kiwi_text,
    R.drawable.fruits_tangerines to R.string.tangerines_text,
    R.drawable.fruits_mango to R.string.mango_text,
    R.drawable.fruits_apple to R.string.apple_text,
    R.drawable.fruits_peach to R.string.peach_text,
    R.drawable.fruits_melon to R.string.melon_text,
    R.drawable.fruits_blackberries to R.string.blackberries_text,
    R.drawable.fruits_orange to R.string.orange_text,
    R.drawable.fruits_nectarine to R.string.nectarine_text,
    R.drawable.fruits_loquat to R.string.loquat_text,
    R.drawable.fruits_papaya to R.string.papaya_text,
    R.drawable.fruits_pear to R.string.pear_text,
    R.drawable.fruits_pinneaple to R.string.pinneaple_text,
    R.drawable.fruits_banana to R.string.banana_text,
    R.drawable.fruits_watermelon to R.string.watermelon_text,
    R.drawable.fruits_grapes to R.string.grapes_text,
).map { DrawableStringPair(it.first, it.second) }

val fatsAndProteinsData = listOf(
    R.drawable.fats_proteins_tuna to R.string.tuna_text,
    R.drawable.fats_proteins_seafood to R.string.seafood_text,
    R.drawable.fats_proteins_pork to R.string.pork_text,
    R.drawable.fats_proteins_rabbit to R.string.rabbit_text,
    R.drawable.fats_proteins_lamb to R.string.lamb_text,
    R.drawable.fats_proteins_gluten to R.string.gluten_text,
    R.drawable.fats_proteins_eggs_whites to R.string.eggs_whites_text,
    R.drawable.fats_proteins_egg to R.string.egg_text,
    R.drawable.fats_proteins_spanish_jam to R.string.spanish_jam_text,
    R.drawable.fats_proteins_pork_loin to R.string.pork_loin_text,
    R.drawable.fats_proteins_tenderloin to R.string.tenderloin_text,
    R.drawable.fats_proteins_white_fish to R.string.white_fish_text,
    R.drawable.fats_proteins_chicken to R.string.chicken_text,
    R.drawable.fats_proteins_protein_powder to R.string.protein_powder_text,
    R.drawable.fats_proteins_cottage_cheese to R.string.cottage_cheese_text,
    R.drawable.fats_proteins_salmon to R.string.salmon_text,
    R.drawable.fats_proteins_seitan to R.string.seitan_text,
    R.drawable.fats_proteins_fat_beef to R.string.fat_beef_text,
    R.drawable.fats_proteins_lean_beef to R.string.lean_beef_text,
    R.drawable.fats_proteins_tofu to R.string.tofu_text,
    R.drawable.fats_proteins_burgos_cheese to R.string.burgos_cheese_text,
    R.drawable.fats_proteins_cheese to R.string.cheese_text,
    R.drawable.fats_proteins_fresh_cheese to R.string.fresh_cheese_text
).map { DrawableStringPair(it.first, it.second) }

val fatsData = listOf(
    R.drawable.fats_oil to R.string.oil_text,
    R.drawable.fats_olives to R.string.olives_text,
    R.drawable.fats_avocado to R.string.avocado_text,
    R.drawable.fats_cocoa to R.string.cocoa_text,
    R.drawable.fats_coconut to R.string.coconut_text,
    R.drawable.fats_chocolate to R.string.chocolate_text,
    R.drawable.fats_nuts to R.string.nuts_text,
    R.drawable.fats_peanut_butter to R.string.peanut_butter_text,
    R.drawable.fats_egg_yolk to R.string.egg_yolk_text,
).map { DrawableStringPair(it.first, it.second) }

val carbohydratesData = listOf(
    R.drawable.carbohydrates_rice_pasta to R.string.rice_pasta_text,
    R.drawable.carbohydrates_cereals to R.string.cereals_text,
    R.drawable.carbohydrates_oat_flour to R.string.oat_fleur_text,
    R.drawable.carbohydrates_corn_flour to R.string.corn_fleur_text,
    R.drawable.carbohydrates_beans to R.string.beans_text,
    R.drawable.carbohydrates_honey to R.string.honey_text,
    R.drawable.carbohydrates_bread to R.string.bread_text,
    R.drawable.carbohydrates_toasted_bread to R.string.toasted_bread_text,
    R.drawable.carbohydrates_potato to R.string.potato_text,
    R.drawable.carbohydrates_potato_pure to R.string.potato_pure_text,
    R.drawable.carbohydrates_quinoa to R.string.quinoa_text,
    R.drawable.carbohydrates_rice_pancakes to R.string.rice_pancakes_text,
).map { DrawableStringPair(it.first, it.second) }

val dairyData = listOf(
    R.drawable.dairy_skimmed_milk to R.string.skimmed_milk_text,
    R.drawable.dairy_skimmed_yogurt to R.string.skimmed_yogurt_text,
    R.drawable.dairy_greek_yogurt to R.string.greek_yogurt_text,
    R.drawable.dairy_protein_yogurt to R.string.protein_yogurt_text,
).map { DrawableStringPair(it.first, it.second) }

@Parcelize
data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
) : Parcelable