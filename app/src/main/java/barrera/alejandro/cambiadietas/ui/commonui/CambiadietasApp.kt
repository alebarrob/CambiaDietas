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
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.ui.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.ui.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.ui.screens.StartScreen
import barrera.alejandro.cambiadietas.ui.screens.TipsScreen

@Composable
fun CambiaDietasApp() {
    val scaffoldState = rememberScaffoldState()
    var screen by rememberSaveable { mutableStateOf("startScreen") }
    var foodCategory by rememberSaveable { mutableStateOf("Elige una categoría") }
    var food by rememberSaveable { mutableStateOf(FoodDrawableStringAmountTriple(
        R.drawable.food_image_placeholder,
        R.string.food_text_placeholder,
        0.00
    )) }

    Box {
        CambiadietasBackground()
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar(onScreenChange = { screen = it }) },
            content = { paddingValues ->
                when (screen) {
                    "startScreen" -> StartScreen(
                        paddingValues = paddingValues,
                        onScreenChange = { screen = it },
                        foodCategory = foodCategory,
                        onFoodCategoryChange = { foodCategory = it },
                        onFoodChange = { food = it }
                    )
                    "selectedFoodScreen" -> SelectedFoodScreen(
                        paddingValues = paddingValues,
                        food = food,
                        foodCategory = foodCategory
                    )
                    "categoriesScreen" -> CategoriesScreen(paddingValues = paddingValues)
                    "tipsScreen" -> TipsScreen(paddingValues = paddingValues)
                } }
        )
    }
}