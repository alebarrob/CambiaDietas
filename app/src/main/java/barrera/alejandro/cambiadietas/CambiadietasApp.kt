package barrera.alejandro.cambiadietas

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*

@Composable
fun CambiaDietasApp() {
    var activeScreen by remember { mutableStateOf("startScreen") }

    Box {
        CambiadietasBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar(onActiveScreen = { activeScreen = it }) },
            content = { paddingValues ->
                when (activeScreen) {
                    "startScreen" -> StartScreen(paddingValues = paddingValues)
                    "categoriesScreen" -> CategoriesScreen(paddingValues = paddingValues)
                    "tipsScreen" -> TipsScreen(paddingValues = paddingValues)
                } }
        )
    }
}