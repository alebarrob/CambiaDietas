package barrera.alejandro.cambiadietas.models

import barrera.alejandro.cambiadietas.R

val fruitsData = listOf(
    Triple(R.drawable.fruits_blueberries, R.string.blueberries_text, 120.00),
    Triple(R.drawable.fruits_cherries, R.string.cherries_text, 145.00),
    Triple(R.drawable.fruits_plums, R.string.plums_text, 145.00),
    Triple(R.drawable.fruits_dates, R.string.dates_text, 20.00),
    Triple(R.drawable.fruits_raspberries, R.string.raspberries_text, 200.00),
    Triple(R.drawable.fruits_strawberries, R.string.strawberries_text, 250.00),
    Triple(R.drawable.fruits_figs, R.string.figs_text, 160.00),
    Triple(R.drawable.fruits_kiwi, R.string.kiwi_text, 140.00),
    Triple(R.drawable.fruits_tangerines, R.string.tangerines_text, 170.00),
    Triple(R.drawable.fruits_mango, R.string.mango_text, 120.00),
    Triple(R.drawable.fruits_apple, R.string.apple_text, 130.00),
    Triple(R.drawable.fruits_peach, R.string.peach_text, 320.00),
    Triple(R.drawable.fruits_melon, R.string.melon_text, 445.00),
    Triple(R.drawable.fruits_blackberries, R.string.blackberries_text, 250.00),
    Triple(R.drawable.fruits_orange, R.string.orange_text, 290.00),
    Triple(R.drawable.fruits_nectarine, R.string.nectarine_text, 135.00),
    Triple(R.drawable.fruits_loquat, R.string.loquat_text, 320.00),
    Triple(R.drawable.fruits_papaya, R.string.papaya_text, 200.00),
    Triple(R.drawable.fruits_pear, R.string.pear_text, 160.00),
    Triple(R.drawable.fruits_pinneaple, R.string.pinneaple_text, 120.00),
    Triple(R.drawable.fruits_banana, R.string.banana_text, 165.00),
    Triple(R.drawable.fruits_watermelon, R.string.watermelon_text, 395.00),
    Triple(R.drawable.fruits_grapes, R.string.grapes_text, 125.00)
).map { Food(it.first, it.second, it.third) }

val fatsAndProteinsData = listOf(
    Triple(R.drawable.fats_proteins_tuna, R.string.tuna_text, 100.00),
    Triple(R.drawable.fats_proteins_seafood, R.string.seafood_text, 120.00),
    Triple(R.drawable.fats_proteins_pork, R.string.pork_text, 60.00),
    Triple(R.drawable.fats_proteins_rabbit, R.string.rabbit_text, 110.00),
    Triple(R.drawable.fats_proteins_lamb, R.string.lamb_text, 50.00),
    Triple(R.drawable.fats_proteins_gluten, R.string.gluten_text, 30.00),
    Triple(R.drawable.fats_proteins_eggs_whites, R.string.eggs_whites_text, 200.00),
    Triple(R.drawable.fats_proteins_egg, R.string.egg_text, 1.00),
    Triple(R.drawable.fats_proteins_spanish_jam, R.string.spanish_jam_text, 45.00),
    Triple(R.drawable.fats_proteins_pork_loin, R.string.pork_loin_text, 85.00),
    Triple(R.drawable.fats_proteins_tenderloin, R.string.tenderloin_text, 50.00),
    Triple(R.drawable.fats_proteins_white_fish, R.string.white_fish_text, 120.00),
    Triple(R.drawable.fats_proteins_chicken, R.string.chicken_text, 95.00),
    Triple(R.drawable.fats_proteins_protein_powder, R.string.protein_powder_text, 25.00),
    Triple(R.drawable.fats_proteins_cottage_cheese, R.string.cottage_cheese_text, 150.00),
    Triple(R.drawable.fats_proteins_salmon, R.string.salmon_text, 50.00),
    Triple(R.drawable.fats_proteins_seitan, R.string.seitan_text, 90.00),
    Triple(R.drawable.fats_proteins_fat_beef, R.string.fat_beef_text, 70.00),
    Triple(R.drawable.fats_proteins_lean_beef, R.string.lean_beef_text, 80.00),
    Triple(R.drawable.fats_proteins_tofu, R.string.tofu_text, 100.00),
    Triple(R.drawable.fats_proteins_burgos_cheese, R.string.burgos_cheese_text, 150.00),
    Triple(R.drawable.fats_proteins_cheese, R.string.cheese_text, 28.00),
    Triple(R.drawable.fats_proteins_fresh_cheese, R.string.fresh_cheese_text, 250.00)
).map { Food(it.first, it.second, it.third) }

val fatsData = listOf(
    Triple(R.drawable.fats_oil, R.string.oil_text, 10.00),
    Triple(R.drawable.fats_olives, R.string.olives_text, 60.00),
    Triple(R.drawable.fats_avocado, R.string.avocado_text, 50.00),
    Triple(R.drawable.fats_cocoa, R.string.cocoa_text, 30.00),
    Triple(R.drawable.fats_coconut, R.string.coconut_text, 30.00),
    Triple(R.drawable.fats_chocolate, R.string.chocolate_text, 20.00),
    Triple(R.drawable.fats_nuts, R.string.nuts_text, 15.00),
    Triple(R.drawable.fats_peanut_butter, R.string.peanut_butter_text, 15.00),
    Triple(R.drawable.fats_egg_yolk, R.string.egg_yolk_text, 2.00)
).map { Food(it.first, it.second, it.third) }

val carbohydratesData = listOf(
    Triple(R.drawable.carbohydrates_rice_pasta, R.string.rice_pasta_text, 30.00),
    Triple(R.drawable.carbohydrates_cereals, R.string.cereals_text, 27.00),
    Triple(R.drawable.carbohydrates_oat_flour, R.string.oat_fleur_text, 27.00),
    Triple(R.drawable.carbohydrates_corn_flour, R.string.corn_fleur_text, 30.00),
    Triple(R.drawable.carbohydrates_beans, R.string.beans_text, 30.00),
    Triple(R.drawable.carbohydrates_honey, R.string.honey_text, 30.00),
    Triple(R.drawable.carbohydrates_bread, R.string.bread_text, 40.00),
    Triple(R.drawable.carbohydrates_toasted_bread, R.string.toasted_bread_text, 30.00),
    Triple(R.drawable.carbohydrates_potato, R.string.potato_text, 120.00),
    Triple(R.drawable.carbohydrates_potato_pure, R.string.potato_pure_text, 30.00),
    Triple(R.drawable.carbohydrates_quinoa, R.string.quinoa_text, 30.00),
    Triple(R.drawable.carbohydrates_rice_pancakes, R.string.rice_pancakes_text, 3.00)
).map { Food(it.first, it.second, it.third) }

val dairyData = listOf(
    Triple(R.drawable.dairy_skimmed_milk, R.string.skimmed_milk_text, 300.00),
    Triple(R.drawable.dairy_skimmed_yogurt, R.string.skimmed_yogurt_text, 300.00),
    Triple(R.drawable.dairy_greek_yogurt, R.string.greek_yogurt_text, 100.00),
    Triple(R.drawable.dairy_protein_yogurt, R.string.protein_yogurt_text, 200.00)
).map { Food(it.first, it.second, it.third) }