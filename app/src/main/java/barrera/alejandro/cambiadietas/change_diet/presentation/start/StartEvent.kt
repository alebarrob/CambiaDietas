package barrera.alejandro.cambiadietas.change_diet.presentation.start

sealed class StartEvent {
    data class OnSelectedCategoryChange(val selectedCategory: String): StartEvent()
}
