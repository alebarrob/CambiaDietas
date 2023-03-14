package barrera.alejandro.cambiadietas.core.domain.use_case

data class CoreUseCases(
    val getDrawableId: GetDrawableId,
    val getAllFoodCategories: GetAllFoodCategories,
    val getFoodByCategory: GetFoodByCategory
)
