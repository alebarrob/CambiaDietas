package barrera.alejandro.cambiadietas.ui.commonui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.data.*

@Composable
fun FoodColumn(
    modifier: Modifier = Modifier,
    onScreenChange: ((String) -> Unit)? = null,
    foodCategory: String,
    onFoodChange: ((FoodDrawableStringAmountTriple) -> Unit)? = null,
    onAlternativeFoodChange: ((FoodDrawableStringAmountTriple) -> Unit)? = null,
    alternativeFoodAmount: String? = null,
    onAlternativeFoodAmountChange: ((String) -> Unit)? = null
) {
    var foodItems by remember { mutableStateOf(listOf<FoodDrawableStringAmountTriple>()) }

    foodItems = when (foodCategory) {
        "Frutas" -> fruitsData
        "Grasas y Proteínas" -> fatsAndProteinsData
        "Grasas" -> fatsData
        "Carbohidratos" -> carbohydratesData
        "Lácteos" -> dairyData
        else -> listOf()
    }
    Column(
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
                    onScreenChange?.invoke("selectedFoodScreen")
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