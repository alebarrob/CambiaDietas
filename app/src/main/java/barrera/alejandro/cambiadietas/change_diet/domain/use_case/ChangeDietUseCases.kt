package barrera.alejandro.cambiadietas.change_diet.domain.use_case

data class ChangeDietUseCases(
    val getFoodByName: GetFoodByName,
    val validateFoodAmount: ValidateFoodAmount,
    val getAlternativeFoodAmount: GetAlternativeFoodAmount
)
