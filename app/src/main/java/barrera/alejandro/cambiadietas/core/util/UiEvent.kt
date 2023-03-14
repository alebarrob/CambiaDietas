package barrera.alejandro.cambiadietas.core.util

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class ShowToast(val message: UiText): UiEvent()
}
