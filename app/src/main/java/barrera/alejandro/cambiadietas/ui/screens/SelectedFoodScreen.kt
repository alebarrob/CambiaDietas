package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.DrawableStringPair
import barrera.alejandro.cambiadietas.ui.commonui.FoodColumn
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    food: DrawableStringPair,
    foodCategory: String,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = paddingValues.calculateBottomPadding())
            else -> modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        }
    ) {
        FoodComparator(
            food = food,
            foodCategory = foodCategory
        )
    }
}

@Composable
fun FoodComparator(
    food: DrawableStringPair,
    foodCategory: String,
) {
    var alternativeFood by rememberSaveable { mutableStateOf(DrawableStringPair(
        R.drawable.food_image_placeholder,
        R.string.food_text_placeholder
    )) }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        modifier = Modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            FoodImageComparator(
                food = food,
                alternativeFood = alternativeFood
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            FoodColumn(
                onScreenChange = null,
                foodCategory = foodCategory,
                onFoodChange = { alternativeFood = it }
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    food: DrawableStringPair,
    alternativeFood: DrawableStringPair
) {
    val sourceFoodName = stringResource(food.text)
    val sourceAlternativeFoodName = stringResource(alternativeFood.text)
    var foodAmount by remember { mutableStateOf("") }
    var foodUnit by remember { mutableStateOf("") }
    var alternativeFoodAmount by remember { mutableStateOf("") }
    var alternativeFoodUnit by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        FoodQuantityCard(
            food = food,
            foodAmount = foodAmount,
            onFoodAmountChange = {
                val appleGrams = convertToAppleGrams(
                    SourceFoodName = sourceFoodName,
                    sourceFoodAmount = it.toDouble(),
                )
                val targetFoodGrams = convertFromAppleGrams(
                    appleGrams = appleGrams,
                    targetFoodName = sourceAlternativeFoodName
                )

                foodAmount = it
                alternativeFoodAmount = targetFoodGrams.toString()

            },
            measurementUnit = foodUnit,
            onMeasurementUnitChange = { foodUnit = it },
            enabled = true
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null
        )
        FoodQuantityCard(
            food = alternativeFood,
            foodAmount = alternativeFoodAmount,
            onFoodAmountChange = { },
            measurementUnit = alternativeFoodUnit,
            onMeasurementUnitChange = { alternativeFoodUnit = it },
            enabled = false
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodQuantityCard(
    food: DrawableStringPair,
    foodAmount: String,
    onFoodAmountChange: (String) -> Unit,
    measurementUnit: String,
    onMeasurementUnitChange: (String) -> Unit,
    enabled: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    when (stringResource(id = food.text)) {
        "" -> onMeasurementUnitChange("")
        "Leche desnatada" -> onMeasurementUnitChange("ml.")
        "Huevo entero XL", "Huevo (Yema)", "Tortitas de arroz o maíz" -> onMeasurementUnitChange("unidades")
        else -> onMeasurementUnitChange("gr.")
    }
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Image(
                painter = painterResource(id = food.drawable),
                contentDescription = null
            )
            Text(
                text = stringResource(id = food.text),
                modifier = Modifier
                    .width(120.dp)
                    .padding(horizontal = 5.dp)
            )
            TextField(
                value = foodAmount,
                shape = RectangleShape,
                onValueChange = onFoodAmountChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                singleLine = true,
                enabled = enabled,
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = KellyGreen),
                label = { Text(text = measurementUnit) },
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

private fun convertToAppleGrams(SourceFoodName: String, sourceFoodAmount: Double): Double {
    return when (SourceFoodName) {
        "Arándanos" -> sourceFoodAmount * 130.0 / 120.0
        "Cerezas" -> sourceFoodAmount * 130.0 / 145.0
        "Ciruelas" -> sourceFoodAmount * 130.0 / 145.0
        "Dátiles" -> sourceFoodAmount * 130.0 / 20.0
        "Frambuesas" -> sourceFoodAmount * 130.0 / 200.0
        "Fresas" -> sourceFoodAmount * 130.0 / 145.0
        "Higos" -> sourceFoodAmount * 130.0 / 145.0
        "Kiwi" -> sourceFoodAmount * 130.0 / 145.0
        "Mandarinas" -> sourceFoodAmount * 130.0 / 145.0
        "Mango" -> sourceFoodAmount * 130.0 / 145.0
        "Manzana" -> sourceFoodAmount * 130.0 / 145.0
        "Melocotón" -> sourceFoodAmount * 130.0 / 145.0
        "Melón" -> sourceFoodAmount * 130.0 / 145.0
        "Moras" -> sourceFoodAmount * 130.0 / 145.0
        "Naranja" -> sourceFoodAmount * 130.0 / 145.0
        "Nectarina" -> sourceFoodAmount * 130.0 / 145.0
        "Nísperos" -> sourceFoodAmount * 130.0 / 145.0
        "Papaya" -> sourceFoodAmount * 130.0 / 145.0
        "Pera" -> sourceFoodAmount * 130.0 / 145.0
        "Piña natural" -> sourceFoodAmount * 130.0 / 145.0
        "Plátano" -> sourceFoodAmount * 130.0 / 145.0
        "Sandía" -> sourceFoodAmount * 130.0 / 145.0
        "Uvas" -> sourceFoodAmount * 130.0 / 145.0
        else -> sourceFoodAmount * 130.0 / 145.0
    }
}

private fun convertFromAppleGrams(appleGrams: Double, targetFoodName: String): Double {
    return when (targetFoodName) {
        "Arándanos" -> appleGrams * 120.0 / 130.0
        "Cerezas" -> appleGrams * 145.0 / 130.0
        "Ciruelas" -> 0.00
        "Dátiles" -> 0.00
        "Frambuesas" -> 0.00
        "Fresas" -> 0.00
        "Higos" -> 0.00
        "Kiwi" -> 0.00
        "Mandarinas" -> 0.00
        "Mango" -> 0.00
        "Manzana" -> 0.00
        "Melocotón" -> 0.00
        "Melón" -> 0.00
        "Moras" -> 0.00
        "Naranja" -> 0.00
        "Nectarina" -> 0.00
        "Nísperos" -> 0.00
        "Papaya" -> 0.00
        "Pera" -> 0.00
        "Piña natural" -> 0.00
        "Plátano" -> 0.00
        "Sandía" -> 0.00
        "Uvas" -> 0.00
        else -> 0.00
    }
}