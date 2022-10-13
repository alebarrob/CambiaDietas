package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasAppViewModel
import barrera.alejandro.cambiadietas.views.screens.CategoriesScreen
import barrera.alejandro.cambiadietas.views.screens.SelectedFoodScreen
import barrera.alejandro.cambiadietas.views.screens.StartScreen
import barrera.alejandro.cambiadietas.views.screens.TipsScreen


@Composable
fun CambiaDietasApp(cambiaDietasAppViewModel: CambiaDietasAppViewModel) {
    val screen by cambiaDietasAppViewModel.screen.observeAsState(initial = "startScreen")
    val foodCategory by cambiaDietasAppViewModel.foodCategory.observeAsState(initial = "Elige una categoría")
    val food by cambiaDietasAppViewModel.food.observeAsState(initial = FoodDrawableStringAmountTriple(
        drawable = R.drawable.food_image_placeholder,
        text = R.string.food_text_placeholder,
        equivalentAmount = 0.00
    ))
    val configuration = LocalConfiguration.current

    Box {
        CambiadietasBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar(onScreenChange = {
                cambiaDietasAppViewModel.onScreenChange(it)
            }) },
            content = { paddingValues ->
                when (screen) {
                    "startScreen" -> StartScreen(
                        paddingValues = paddingValues,
                        onScreenChange = { cambiaDietasAppViewModel.onScreenChange(it) },
                        foodCategory = foodCategory,
                        onFoodCategoryChange = { cambiaDietasAppViewModel.onFoodCategoryChange(it) },
                        onFoodChange = { cambiaDietasAppViewModel.onFoodChange(it) },
                        configuration = configuration
                    )
                    "selectedFoodScreen" -> SelectedFoodScreen(
                        paddingValues = paddingValues,
                        food = food,
                        foodCategory = foodCategory
                    )
                    "categoriesScreen" -> CategoriesScreen(paddingValues = paddingValues)
                    "tipsScreen" -> TipsScreen(
                        paddingValues = paddingValues,
                        configuration = configuration
                    )
                } }
        )
    }
}