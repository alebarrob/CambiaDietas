package barrera.alejandro.cambiadietas

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*

@Composable
fun CambiaDietasApp() {
    Box {
        CambiadietasBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar() },
            content = { StartScreen() }
        )
    }
}