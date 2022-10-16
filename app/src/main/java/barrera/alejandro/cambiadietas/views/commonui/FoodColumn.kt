package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.model.data.*
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.FoodColumnViewModel

@Composable
fun FoodColumn(
    modifier: Modifier = Modifier,
    onNavigateToSelectedFoodScreen: (() -> Unit)? = null,
    foodCategory: String,
    onFoodChange: ((FoodDrawableStringAmountTriple) -> Unit)? = null,
    onAlternativeFoodChange: ((FoodDrawableStringAmountTriple) -> Unit)? = null,
    alternativeFoodAmount: String? = null,
    onAlternativeFoodAmountChange: ((String) -> Unit)? = null,
    foodColumnViewModel: FoodColumnViewModel
) {
    val foodItems by foodColumnViewModel.foodItems.observeAsState(initial = listOf())

    when (foodCategory) {
        "Frutas" -> foodColumnViewModel.onFoodItemsChange(fruitsData)
        "Grasas y Proteínas" -> foodColumnViewModel.onFoodItemsChange(fatsAndProteinsData)
        "Grasas" -> foodColumnViewModel.onFoodItemsChange(fatsData)
        "Carbohidratos" -> foodColumnViewModel.onFoodItemsChange(carbohydratesData)
        "Lácteos" -> foodColumnViewModel.onFoodItemsChange(dairyData)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .verticalScroll(rememberScrollState())
    ) {
        foodItems.forEach { item ->
            Button(
                onClick = {
                    onNavigateToSelectedFoodScreen?.invoke()
                    onFoodChange?.invoke(item)
                    onAlternativeFoodChange?.invoke(item)
                    onAlternativeFoodAmountChange?.invoke(alternativeFoodAmount!!)
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    text = stringResource(id = item.text),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                )
            }

        }
    }
}