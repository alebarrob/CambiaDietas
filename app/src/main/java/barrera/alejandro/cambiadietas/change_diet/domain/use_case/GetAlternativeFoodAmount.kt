package barrera.alejandro.cambiadietas.change_diet.domain.use_case

import barrera.alejandro.cambiadietas.core.domain.model.Food
import java.util.*

class GetAlternativeFoodAmount {
    operator fun invoke(
        selectedFood: Food,
        alternativeFood: Food,
        selectedFoodAmount: String
    ): String {
        return if (selectedFoodAmount == "") "" else {
            val intermediateFoodEquivalentAmount = when (selectedFood.category) {
                "Frutas" -> 130.00
                "Grasas y Proteínas" -> 110.00
                "Grasas" -> 50.00
                "Carbohidratos" -> 40.00
                "Lácteos" -> 100.00
                else -> 0.00
            }
            val intermediateFoodAmount = selectedFoodAmount.toDouble() *
                    intermediateFoodEquivalentAmount / selectedFood.equivalentAmount
            val alternativeFoodAmount = intermediateFoodAmount *
                    alternativeFood.equivalentAmount / intermediateFoodEquivalentAmount

            String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
        }
    }
}