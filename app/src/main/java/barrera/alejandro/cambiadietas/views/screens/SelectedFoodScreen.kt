package barrera.alejandro.cambiadietas.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.viewmodels.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodels.SelectedFoodScreenViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasColumn
import barrera.alejandro.cambiadietas.views.commonui.FoodColumn
import barrera.alejandro.cambiadietas.views.theme.Aquamarine
import barrera.alejandro.cambiadietas.views.theme.KellyGreen

@Composable
fun SelectedFoodScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    foodCategory: String,
    food: FoodDrawableStringAmountTriple,
    foodItems: List<FoodDrawableStringAmountTriple>,
    commonUiViewModel: CommonUiViewModel
) {
    val selectedFoodScreenViewModel = SelectedFoodScreenViewModel()
    val context = LocalContext.current

    val foodAmount by selectedFoodScreenViewModel.foodAmount.observeAsState(initial = "")
    val alternativeFood by selectedFoodScreenViewModel.alternativeFood.observeAsState(
        initial = FoodDrawableStringAmountTriple(
            drawable = R.drawable.food_image_placeholder,
            text = R.string.food_text_placeholder,
            equivalentAmount = 0.00
        )
    )
    val alternativeFoodAmount by selectedFoodScreenViewModel.alternativeFoodAmount.observeAsState(initial = "")
    val wrongInput by selectedFoodScreenViewModel.wrongInput.observeAsState(initial = false)
    val foodUnit by selectedFoodScreenViewModel.foodUnit.observeAsState(initial = "")
    val alternativeFoodUnit by selectedFoodScreenViewModel.alternativeFoodUnit.observeAsState(initial = "")

    CambiaDietasColumn(
        modifier = modifier,
        paddingValues = paddingValues
    ) {
        FoodComparator(
            foodCategory = foodCategory,
            food = food,
            foodUnit = foodUnit,
            onFoodUnitChange = { selectedFoodScreenViewModel.onFoodUnitChange(it) },
            foodAmount = foodAmount,
            alternativeFood = alternativeFood,
            alternativeFoodUnit = alternativeFoodUnit,
            onAlternativeFoodUnitChange = { selectedFoodScreenViewModel.onAlternativeFoodUnitChange(it) },
            onAlternativeFoodChange = {
                selectedFoodScreenViewModel.onAlternativeFoodChange(it)
                selectedFoodScreenViewModel.updateAlternativeFoodAmount(
                    foodAmount = foodAmount,
                    foodCategory = foodCategory,
                    food = food
                )
            },
            onFoodAmountChange = {
                selectedFoodScreenViewModel.onFoodAmountChange(
                    foodAmount = it,
                    foodCategory = foodCategory,
                    food = food,
                    context = context
                )
            },
            alternativeFoodAmount = alternativeFoodAmount,
            foodItems = foodItems,
            wrongInput = wrongInput,
            commonUiViewModel = commonUiViewModel
        )
    }
}

@Composable
fun FoodComparator(
    foodCategory: String,
    food: FoodDrawableStringAmountTriple,
    foodUnit: String,
    onFoodUnitChange: (String) -> Unit,
    foodAmount: String,
    alternativeFood: FoodDrawableStringAmountTriple,
    alternativeFoodUnit: String,
    onAlternativeFoodUnitChange: (String) -> Unit,
    onAlternativeFoodChange: (FoodDrawableStringAmountTriple) -> Unit,
    alternativeFoodAmount: String,
    foodItems: List<FoodDrawableStringAmountTriple>,
    wrongInput: Boolean,
    onFoodAmountChange: (String) -> Unit,
    commonUiViewModel: CommonUiViewModel
) {
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
                foodUnit = foodUnit,
                onFoodUnitChange = onFoodUnitChange,
                onFoodAmountChange = onFoodAmountChange,
                insertedFoodAmount = foodAmount,
                alternativeFood = alternativeFood,
                alternativeFoodUnit = alternativeFoodUnit,
                onAlternativeFoodUnitChange = onAlternativeFoodUnitChange,
                alternativeFoodAmount = alternativeFoodAmount,
                wrongInput = wrongInput
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            FoodColumn(
                foodCategory = foodCategory,
                onAlternativeFoodChange = onAlternativeFoodChange,
                foodItems = foodItems,
                commonUiViewModel = commonUiViewModel
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    food: FoodDrawableStringAmountTriple,
    foodUnit: String,
    onFoodUnitChange: (String) -> Unit,
    insertedFoodAmount: String,
    onFoodAmountChange: (String) -> Unit,
    alternativeFood: FoodDrawableStringAmountTriple,
    alternativeFoodUnit: String,
    onAlternativeFoodUnitChange: (String) -> Unit,
    alternativeFoodAmount: String,
    wrongInput: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        FoodQuantityCard(
            food = food,
            onFoodAmountChange = onFoodAmountChange,
            insertedFoodAmount = insertedFoodAmount,
            measurementUnit = foodUnit,
            onMeasurementUnitChange = { onFoodUnitChange(it) },
            enabled = true,
            wrongInput = wrongInput
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null
        )
        FoodQuantityCard(
            food = alternativeFood,
            onFoodAmountChange = { },
            insertedFoodAmount = alternativeFoodAmount,
            measurementUnit = alternativeFoodUnit,
            onMeasurementUnitChange = { onAlternativeFoodUnitChange(it) },
            enabled = false,
            wrongInput = wrongInput
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodQuantityCard(
    food: FoodDrawableStringAmountTriple,
    onFoodAmountChange: (String) -> Unit,
    insertedFoodAmount: String,
    measurementUnit: String,
    onMeasurementUnitChange: (String) -> Unit,
    enabled: Boolean,
    wrongInput: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current

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
            when (stringResource(id = food.text)) {
                "" -> onMeasurementUnitChange("")
                "Leche desnatada" -> onMeasurementUnitChange("ml.")
                "Huevo entero XL", "Huevo (Yema)", "Tortitas de arroz o maíz" -> onMeasurementUnitChange("unidades")
                else -> onMeasurementUnitChange("gr.")
            }
            TextField(
                value = insertedFoodAmount,
                shape = RectangleShape,
                onValueChange = onFoodAmountChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                isError = wrongInput,
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