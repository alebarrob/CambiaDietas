package barrera.alejandro.cambiadietas.views.commonui

import barrera.alejandro.cambiadietas.R

/*
* Function that returns the id of a drawable resource after passing
* the food category and name as parameters. Not the most efficient function
* but Play Store ProGuard makes the app crash implementing a better function.
* In the next updates of CambiaDietas I will implement a better solution with
* the code you can find above.
*/
fun getResId(foodCategory: String, foodName: String): Int {
    return when (foodCategory) {
        "Frutas" -> getFruitId(foodName)
        "Grasas y Proteínas" -> getFatAndProteinId(foodName)
        "Grasas" -> getFatId(foodName)
        "Carbohidratos" -> getCarbohydrateId(foodName)
        "Lácteos" -> getDairyId(foodName)
        else -> R.drawable.food_image_placeholder
    }
}

private fun getFruitId(foodName: String): Int {
    return when (foodName) {
        "Arándanos" -> R.drawable.fruits_blueberries
        "Cerezas" -> R.drawable.fruits_cherries
        "Ciruelas" -> R.drawable.fruits_plums
        "Dátiles" -> R.drawable.fruits_dates
        "Frambuesas" -> R.drawable.fruits_raspberries
        "Fresas" -> R.drawable.fruits_strawberries
        "Higos" -> R.drawable.fruits_figs
        "Kiwi" -> R.drawable.fruits_kiwi
        "Mandarinas" -> R.drawable.fruits_tangerines
        "Mango" -> R.drawable.fruits_mango
        "Manzana" -> R.drawable.fruits_apple
        "Melocotón" -> R.drawable.fruits_peach
        "Melón" -> R.drawable.fruits_melon
        "Moras" -> R.drawable.fruits_blackberries
        "Naranja" -> R.drawable.fruits_orange
        "Nectarina" -> R.drawable.fruits_nectarine
        "Nísperos" -> R.drawable.fruits_loquat
        "Papaya" -> R.drawable.fruits_papaya
        "Pera" -> R.drawable.fruits_pear
        "Piña natural" -> R.drawable.fruits_pinneaple
        "Plátano" -> R.drawable.fruits_banana
        "Sandía" -> R.drawable.fruits_watermelon
        "Uvas" -> R.drawable.fruits_grapes
        else -> R.drawable.food_image_placeholder
    }
}

private fun getFatAndProteinId(foodName: String): Int {
    return when (foodName) {
        "Atún al natural" -> R.drawable.fats_proteins_tuna
        "Marisco" -> R.drawable.fats_proteins_seafood
        "Cerdo (Graso)" -> R.drawable.fats_proteins_pork
        "Conejo" -> R.drawable.fats_proteins_rabbit
        "Cordero" -> R.drawable.fats_proteins_lamb
        "Gluten de trigo" -> R.drawable.fats_proteins_gluten
        "Huevo (Clara)" -> R.drawable.fats_proteins_eggs_whites
        "Huevo entero XL" -> R.drawable.fats_proteins_egg
        "Jamón serrano" -> R.drawable.fats_proteins_spanish_jam
        "Lomo de cerdo (Magro)" -> R.drawable.fats_proteins_pork_loin
        "Lomo embuchado" -> R.drawable.fats_proteins_tenderloin
        "Pescado blanco" -> R.drawable.fats_proteins_white_fish
        "Pollo o pavo (Pechuga sin piel)" -> R.drawable.fats_proteins_chicken
        "Proteína en polvo" -> R.drawable.fats_proteins_protein_powder
        "Requesón desnatado" -> R.drawable.fats_proteins_cottage_cheese
        "Salmón, pescados azules" -> R.drawable.fats_proteins_salmon
        "Seitan" -> R.drawable.fats_proteins_seitan
        "Ternera, potro (Graso)" -> R.drawable.fats_proteins_fat_beef
        "Ternera, potro (Magro)" -> R.drawable.fats_proteins_lean_beef
        "Tofu" -> R.drawable.fats_proteins_tofu
        "Queso burgos desnatado" -> R.drawable.fats_proteins_burgos_cheese
        "Queso (Cualquier tipo)" -> R.drawable.fats_proteins_cheese
        "Queso fresco batido 0%" -> R.drawable.fats_proteins_fresh_cheese
        else -> R.drawable.food_image_placeholder
    }
}

private fun getFatId(foodName: String): Int {
    return when (foodName) {
        "Aceite" -> R.drawable.fats_oil
        "Aceitunas sin hueso" -> R.drawable.fats_olives
        "Aguacate" -> R.drawable.fats_avocado
        "Cacao desgrasado en polvo" -> R.drawable.fats_cocoa
        "Coco" -> R.drawable.fats_coconut
        "Chocolate negro" -> R.drawable.fats_chocolate
        "Frutos secos" -> R.drawable.fats_nuts
        "Mantequilla de cacahuete" -> R.drawable.fats_peanut_butter
        "Huevo (Yema)" -> R.drawable.fats_egg_yolk
        else -> R.drawable.food_image_placeholder
    }
}

private fun getCarbohydrateId(foodName: String): Int {
    return when (foodName) {
        "Arroz o pasta (En seco)" -> R.drawable.carbohydrates_rice_pasta
        "Cereales de desayuno" -> R.drawable.carbohydrates_cereals
        "Copos o harina de avena" -> R.drawable.carbohydrates_oat_flour
        "Harina de maíz o de arroz" -> R.drawable.carbohydrates_corn_flour
        "Legumbres (Crudas)" -> R.drawable.carbohydrates_beans
        "Miel" -> R.drawable.carbohydrates_honey
        "Pan" -> R.drawable.carbohydrates_bread
        "Pan tostado" -> R.drawable.carbohydrates_toasted_bread
        "Patata o boniato (Crudo)" -> R.drawable.carbohydrates_potato
        "Puré de patata deshidratado (En polvo)" -> R.drawable.carbohydrates_potato_pure
        "Quinoa" -> R.drawable.carbohydrates_quinoa
        "Tortitas de arroz o maíz" -> R.drawable.carbohydrates_rice_pancakes
        else -> R.drawable.food_image_placeholder
    }
}

private fun getDairyId(foodName: String): Int {
    return when (foodName) {
        "Leche desnatada" -> R.drawable.dairy_skimmed_milk
        "Yogur desnatado" -> R.drawable.dairy_skimmed_yogurt
        "Yogur griego" -> R.drawable.dairy_greek_yogurt
        "Yogur proteico" -> R.drawable.dairy_protein_yogurt
        else -> R.drawable.food_image_placeholder
    }
}

/*
Better function but makes the app crash after ProGuard.

fun getResId(resName: String, c: Class<*> = R.drawable::class.java): Int {
    return try {
        val idField: Field = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}
*/



