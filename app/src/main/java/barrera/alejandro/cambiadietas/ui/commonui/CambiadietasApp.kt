package barrera.alejandro.cambiadietas.ui.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import barrera.alejandro.cambiadietas.data.DrawableStringPair
import barrera.alejandro.cambiadietas.ui.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.ui.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.ui.screens.StartScreen
import barrera.alejandro.cambiadietas.ui.screens.TipsScreen

@Composable
fun CambiaDietasApp() {
    val scaffoldState = rememberScaffoldState()
    var currentScreen by rememberSaveable { mutableStateOf("startScreen") }
    var selectedCategory by rememberSaveable { mutableStateOf("Elige una categoría") }
    var selectedFood by rememberSaveable {
        mutableStateOf(DrawableStringPair(0, 0))
    }

    Box {
        CambiadietasBackground()
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar(onCurrentScreen = { currentScreen = it }) },
            content = { paddingValues ->
                when (currentScreen) {
                    "startScreen" -> StartScreen(
                        paddingValues = paddingValues,
                        selectedCategory = selectedCategory,
                        onSelectedCategory = { selectedCategory = it },
                        onCurrentScreen = { currentScreen = it },
                        onSelectedFood = { selectedFood = it }
                    )
                    "selectedFoodScreen" -> SelectedFoodScreen(
                        paddingValues = paddingValues,
                        selectedFood = selectedFood,
                        selectedCategory = selectedCategory
                    )
                    "categoriesScreen" -> CategoriesScreen(paddingValues = paddingValues)
                    "tipsScreen" -> TipsScreen(paddingValues = paddingValues)
                } }
        )
    }
}