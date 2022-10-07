package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import java.util.*

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    foodCategory: String,
    food: DrawableStringPair,
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
            foodCategory = foodCategory,
            food = food
        )
    }
}

@Composable
fun FoodComparator(
    foodCategory: String,
    food: DrawableStringPair
) {
    val context = LocalContext.current
    val foodName = stringResource(food.text)
    var foodAmount by rememberSaveable { mutableStateOf("") }
    var foodUnit by rememberSaveable { mutableStateOf("") }
    var alternativeFood by rememberSaveable { mutableStateOf(DrawableStringPair(
        R.drawable.food_image_placeholder,
        R.string.food_text_placeholder
    )) }
    val alternativeFoodName = stringResource(alternativeFood.text)
    var alternativeFoodAmount by rememberSaveable { mutableStateOf("") }
    var alternativeFoodUnit by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

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
                foodAmount = foodAmount,
                foodUnit = foodUnit,
                onFoodUnitChange = { foodUnit = it },
                alternativeFood = alternativeFood,
                alternativeFoodAmount = alternativeFoodAmount,
                onAlternativeFoodAmountChange = {
                    if (it.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
                        isError = false
                        foodAmount = it
                        alternativeFoodAmount = String.format(
                            locale = Locale.US,
                            format = "%.2f",
                            calculateFoodAmountEquivalence(
                                foodCategory = foodCategory,
                                foodName = foodName,
                                foodAmount = it.toDouble(),
                                alternativeFoodName = alternativeFoodName
                            )
                        )
                    } else if (it == "") {
                        isError = false
                        foodAmount = it
                        alternativeFoodAmount = it
                    } else {
                        isError = true
                        Toast.makeText(
                            context, "Has introducido un valor incorrecto", Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                alternativeFoodUnit = alternativeFoodUnit,
                onAlternativeFoodUnitChange = { alternativeFoodUnit = it },
                isError = isError
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            FoodColumn(
                onScreenChange = null,
                foodCategory = foodCategory,
                onFoodChange = {
                    alternativeFood = it
                    Toast.makeText(
                        context, "BOTON PULSADO", Toast.LENGTH_SHORT
                    ).show()
                    alternativeFoodAmount = if (foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
                        String.format(
                            locale = Locale.US,
                            format = "%.2f",
                            calculateFoodAmountEquivalence(
                                foodCategory = foodCategory,
                                foodName = foodName,
                                foodAmount = foodAmount.toDouble(),
                                alternativeFoodName = alternativeFoodName
                            )
                        )
                    } else {
                        foodAmount
                    }
                }
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    food: DrawableStringPair,
    foodAmount: String,
    foodUnit: String,
    onFoodUnitChange: (String) -> Unit,
    alternativeFood: DrawableStringPair,
    alternativeFoodAmount: String,
    onAlternativeFoodAmountChange: (String) -> Unit,
    alternativeFoodUnit: String,
    onAlternativeFoodUnitChange: (String) -> Unit,
    isError: Boolean
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        FoodQuantityCard(
            food = food,
            foodAmount = foodAmount,
            onFoodAmountChange = { onAlternativeFoodAmountChange(it) },
            measurementUnit = foodUnit,
            onMeasurementUnitChange = { onFoodUnitChange(it) },
            enabled = true,
            isError = isError
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
            onMeasurementUnitChange = { onAlternativeFoodUnitChange(it) },
            enabled = false,
            isError = isError
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
    enabled: Boolean,
    isError: Boolean
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
                isError = isError,
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

private fun calculateFoodAmountEquivalence(
    foodCategory: String,
    foodName: String,
    foodAmount: Double,
    alternativeFoodName: String
): Double {
    return when (foodCategory) {
        "Frutas" -> {
            val appleGrams = convertToAppleGrams(
                SourceFoodName = foodName,
                sourceFoodAmount = foodAmount,
            )
            val alternativeFoodAmount = convertFromAppleGrams(
                appleGrams = appleGrams,
                targetFoodName = alternativeFoodName
            )

            alternativeFoodAmount
        }
        else -> 0.0
    }
}

private fun convertToAppleGrams(SourceFoodName: String, sourceFoodAmount: Double): Double {
    return when (SourceFoodName) {
        "Arándanos" -> sourceFoodAmount * 130.0 / 120.0
        "Cerezas" -> sourceFoodAmount * 130.0 / 145.0
        "Ciruelas" -> sourceFoodAmount * 130.0 / 145.0
        "Dátiles" -> sourceFoodAmount * 130.0 / 20.0
        "Frambuesas" -> sourceFoodAmount * 130.0 / 200.0
        "Fresas" -> sourceFoodAmount * 130.0 / 250.0
        "Higos" -> sourceFoodAmount * 130.0 / 160.0
        "Kiwi" -> sourceFoodAmount * 130.0 / 140.0
        "Mandarinas" -> sourceFoodAmount * 130.0 / 170.0
        "Mango" -> sourceFoodAmount * 130.0 / 120.0
        "Manzana" -> sourceFoodAmount * 130.0 / 130.0
        "Melocotón" -> sourceFoodAmount * 130.0 / 320.0
        "Melón" -> sourceFoodAmount * 130.0 / 445.0
        "Moras" -> sourceFoodAmount * 130.0 / 250.0
        "Naranja" -> sourceFoodAmount * 130.0 / 290.0
        "Nectarina" -> sourceFoodAmount * 130.0 / 135.0
        "Nísperos" -> sourceFoodAmount * 130.0 / 320.0
        "Papaya" -> sourceFoodAmount * 130.0 / 200.0
        "Pera" -> sourceFoodAmount * 130.0 / 160.0
        "Piña natural" -> sourceFoodAmount * 130.0 / 120.0
        "Plátano" -> sourceFoodAmount * 130.0 / 165.0
        "Sandía" -> sourceFoodAmount * 130.0 / 395.0
        "Uvas" -> sourceFoodAmount * 130.0 / 125.0
        else -> 0.00
    }
}

private fun convertFromAppleGrams(appleGrams: Double, targetFoodName: String): Double {
    return when (targetFoodName) {
        "Arándanos" -> appleGrams * 120.0 / 130.0
        "Cerezas" -> appleGrams * 145.0 / 130.0
        "Ciruelas" -> appleGrams * 145.0 / 130.0
        "Dátiles" -> appleGrams * 20.0 / 130.0
        "Frambuesas" -> appleGrams * 200.0 / 130.0
        "Fresas" -> appleGrams * 250.0 / 130.0
        "Higos" -> appleGrams * 160.0 / 130.0
        "Kiwi" -> appleGrams * 140.0 / 130.0
        "Mandarinas" -> appleGrams * 160.0 / 130.0
        "Mango" -> appleGrams * 120.0 / 130.0
        "Manzana" -> appleGrams * 130.0 / 130.0
        "Melocotón" -> appleGrams * 320.0 / 130.0
        "Melón" -> appleGrams * 445.0 / 130.0
        "Moras" -> appleGrams * 250.0 / 130.0
        "Naranja" -> appleGrams * 290.0 / 130.0
        "Nectarina" -> appleGrams * 135.0 / 130.0
        "Nísperos" -> appleGrams * 320.0 / 130.0
        "Papaya" -> appleGrams * 200.0 / 130.0
        "Pera" -> appleGrams * 160.0 / 130.0
        "Piña natural" -> appleGrams * 120.0 / 130.0
        "Plátano" -> appleGrams * 165.0 / 130.0
        "Sandía" -> appleGrams * 395.0 / 130.0
        "Uvas" -> appleGrams * 125.0 / 130.0
        else -> 0.00
    }
}