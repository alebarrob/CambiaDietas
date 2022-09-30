package barrera.alejandro.cambiadietas.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import barrera.alejandro.cambiadietas.ui.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.ui.screens.StartScreen
import barrera.alejandro.cambiadietas.ui.screens.TipsScreen

@Composable
fun CambiaDietasApp() {
    val scaffoldState = rememberScaffoldState()
    var currentScreen by remember { mutableStateOf("startScreen") }

    Box {
        CambiadietasBackground()
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar(onCurrentScreen = { currentScreen = it }) },
            content = { paddingValues ->
                when (currentScreen) {
                    "startScreen" -> StartScreen(paddingValues = paddingValues)
                    "categoriesScreen" -> CategoriesScreen(paddingValues = paddingValues)
                    "tipsScreen" -> TipsScreen(paddingValues = paddingValues)
                } }
        )
    }
}