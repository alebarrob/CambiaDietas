package barrera.alejandro.cambiadietas.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import barrera.alejandro.cambiadietas.core.presentation.CoreEvent
import barrera.alejandro.cambiadietas.core.presentation.CoreViewModel

@Composable
fun UiController(
    viewModel: CoreViewModel,
    currentDestination: NavDestination?
) {
    when (currentDestination?.route) {
        "selectedFood/{foodName}/{foodCategory}", "categoryDetail/{foodCategory}" -> {
            viewModel.onEvent(CoreEvent.HideBottomBar)
            viewModel.onEvent(CoreEvent.ShowTopBar)
        }
        else -> {
            viewModel.onEvent(CoreEvent.ShowBottomBar)
            viewModel.onEvent(CoreEvent.HideTopBar)
        }
    }
}