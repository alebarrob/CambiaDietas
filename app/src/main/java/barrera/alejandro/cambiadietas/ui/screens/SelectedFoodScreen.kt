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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import barrera.alejandro.cambiadietas.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.ui.commonui.FoodColumn
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen
import java.util.*

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    foodCategory: String,
    food: FoodDrawableStringAmountTriple,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    var alternativeFood by rememberSaveable { mutableStateOf(FoodDrawableStringAmountTriple(
        R.drawable.food_image_placeholder,
        R.string.food_text_placeholder,
        0.00
    )) }
    var alternativeFoodAmount by rememberSaveable { mutableStateOf("") }
    var insertedFoodAmount by rememberSaveable { mutableStateOf("") }

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
            food = food,
            alternativeFood = alternativeFood,
            onAlternativeFoodChange = { alternativeFood = it },
            insertedFoodAmount = insertedFoodAmount,
            onInsertedFoodAmountChange = { insertedFoodAmount = it },
            alternativeFoodAmount = alternativeFoodAmount,
            onAlternativeFoodAmountChange = { alternativeFoodAmount = calculateFoodAmountEquivalence(
                foodCategory = foodCategory,
                food = food,
                alternativeFood = alternativeFood,
                insertedFoodAmount = insertedFoodAmount.toDouble()
            ) }
        )
    }
}

@Composable
fun FoodComparator(
    foodCategory: String,
    food: FoodDrawableStringAmountTriple,
    insertedFoodAmount: String,
    onInsertedFoodAmountChange: (String) -> Unit,
    alternativeFood: FoodDrawableStringAmountTriple,
    alternativeFoodAmount: String,
    onAlternativeFoodAmountChange: (String) -> Unit,
    onAlternativeFoodChange: (FoodDrawableStringAmountTriple) -> Unit
) {
    val context = LocalContext.current

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
                insertedFoodAmount = insertedFoodAmount,
                onFoodAmountChange = { if (it.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
                        isError = false
                        onInsertedFoodAmountChange(it)
                        onAlternativeFoodAmountChange(it)
                    } else if (it == "") {
                        isError = false
                        onInsertedFoodAmountChange(it)
                    } else {
                        isError = true
                        Toast.makeText(
                            context, "Has introducido un valor incorrecto", Toast.LENGTH_SHORT
                        ).show()
                    } },
                alternativeFood = alternativeFood,
                alternativeFoodAmount = alternativeFoodAmount,
                isError = isError
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            FoodColumn(
                foodCategory = foodCategory,
                onAlternativeFoodChange = onAlternativeFoodChange,
                alternativeFoodAmount = alternativeFoodAmount,
                onAlternativeFoodAmountChange = {
                    if (alternativeFoodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))"))) {
                        onAlternativeFoodAmountChange(insertedFoodAmount)
                    }
                }
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    food: FoodDrawableStringAmountTriple,
    insertedFoodAmount: String,
    onFoodAmountChange: (String) -> Unit,
    alternativeFood: FoodDrawableStringAmountTriple,
    alternativeFoodAmount: String,
    isError: Boolean
) {
    var foodUnit by rememberSaveable { mutableStateOf("") }
    var alternativeFoodUnit by rememberSaveable { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        FoodQuantityCard(
            food = food,
            insertedFoodAmount = insertedFoodAmount,
            onFoodAmountChange = onFoodAmountChange,
            measurementUnit = foodUnit,
            onMeasurementUnitChange = { foodUnit = it },
            enabled = true,
            isError = isError
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null
        )
        FoodQuantityCard(
            food = alternativeFood,
            insertedFoodAmount = alternativeFoodAmount,
            onFoodAmountChange = { },
            measurementUnit = alternativeFoodUnit,
            onMeasurementUnitChange = { alternativeFoodUnit = it },
            enabled = false,
            isError = isError
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodQuantityCard(
    food: FoodDrawableStringAmountTriple,
    insertedFoodAmount: String,
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
                value = insertedFoodAmount,
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
    food: FoodDrawableStringAmountTriple,
    alternativeFood: FoodDrawableStringAmountTriple,
    insertedFoodAmount: Double,
): String {
    return when (foodCategory) {
        "Frutas" -> {
            val appleGrams = convertToAppleGrams(
                sourceFoodAmount = insertedFoodAmount,
                equivalentFoodAmount = food.equivalentAmount
            )
            val alternativeFoodAmount = convertFromAppleGrams(
                appleGrams = appleGrams,
                equivalentFoodAmount = alternativeFood.equivalentAmount
            )

            String.format(locale = Locale.US, format = "%.2f", alternativeFoodAmount)
        }
        else -> "0.00"
    }
}

private fun convertToAppleGrams(sourceFoodAmount: Double, equivalentFoodAmount: Double): Double {
    return sourceFoodAmount * 130.00 / equivalentFoodAmount
}

private fun convertFromAppleGrams(appleGrams: Double, equivalentFoodAmount: Double): Double {
    return appleGrams * equivalentFoodAmount / 130.0
}