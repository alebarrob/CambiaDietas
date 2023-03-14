package barrera.alejandro.cambiadietas.change_diet.presentation.selected_food

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.change_diet.presentation.components.FoodPicker
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing

@Composable
fun SelectedFoodScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: SelectedFoodViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(SelectedFoodEvent.LoadSelectedFood)
        viewModel.onEvent(SelectedFoodEvent.LoadFoodsByCategory)
    }

    AdaptableColumn(
        modifier = modifier.padding(
            vertical = spacing.spaceSmall,
            horizontal = spacing.spaceLarge
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = spacing.default,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBarPadding = paddingValues.calculateTopPadding()
    ) {
        FoodPicker(
            headline = stringResource(id = barrera.alejandro.cambiadietas.R.string.selected_food_food_picker_headline),
            selectedCategory = state.selectedFoodCategory,
            foods = state.foods,
            onFoodClick = { foodName, foodCategory ->

            }
        )
    }

    /** The ViewModel of this screen is declared here so that the state with the
    * alternative food chosen is not saved when switching screens.


    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val selectedFoodScreenViewModel = hiltViewModel<SelectedFoodScreenViewModel>()

    val foodByCategory by selectedFoodScreenViewModel.foodByCategory.collectAsState(listOf())
    val selectedFood by selectedFoodScreenViewModel.selectedFood.collectAsState(
        initial = Food(
            id = 100,
            drawableName = "food_image_placeholder",
            name = "",
            equivalentAmountForCalculations = 0.0,
            category = "",
            unit = ""
        )
    )
    val alternativeFood by selectedFoodScreenViewModel.alternativeFood.collectAsState(
        initial = Food(
            id = 100,
            drawableName = "food_image_placeholder",
            name = "",
            equivalentAmountForCalculations = 0.0,
            category = "",
            unit = ""
        )
    )
    val selectedFoodAmount by selectedFoodScreenViewModel.selectedFoodAmount.collectAsState(initial = "")
    val alternativeFoodAmount by selectedFoodScreenViewModel.alternativeFoodAmount.collectAsState(initial = "")
    val wrongInput by selectedFoodScreenViewModel.wrongInput.collectAsState(initial = false)

    if (wrongInput) {
        Toast.makeText(context, "Has introducido un valor incorrecto", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            selectedFoodScreenViewModel.onWrongInputChange(false)
        }, 2000)
    }
    CambiaDietasContainer(
        modifier = modifier,
        paddingValues = paddingValues,
        configuration = configuration
    ) {
        FoodComparator(
            foodByCategory = foodByCategory,
            selectedFood = selectedFood,
            alternativeFood = alternativeFood,
            onAlternativeFoodChange = {
                selectedFoodScreenViewModel.onAlternativeFoodChange(
                    selectedFood = selectedFood,
                    alternativeFood = it,
                    selectedFoodAmount = selectedFoodAmount
                )
            },
            selectedFoodAmount = selectedFoodAmount,
            onSelectedFoodAmountChange = {
                selectedFoodScreenViewModel.onSelectedFoodAmountChange(
                    selectedFoodAmount = it,
                    selectedFood = selectedFood,
                    alternativeFood = alternativeFood
                )
            },
            alternativeFoodAmount = alternativeFoodAmount,
            wrongInput = wrongInput
        )
    }*/
}

/*@Composable
fun FoodComparator(
    modifier: Modifier = Modifier,
    foodByCategory: List<Food>,
    selectedFood: Food,
    alternativeFood: Food,
    onAlternativeFoodChange: (Food) -> Unit,
    selectedFoodAmount: String,
    onSelectedFoodAmountChange: (String) -> Unit,
    alternativeFoodAmount: String,
    wrongInput: Boolean
) {
    Card(
        modifier = modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FoodImageComparator(
                selectedFood = selectedFood,
                alternativeFood = alternativeFood,
                selectedFoodAmount = selectedFoodAmount,
                onSelectedFoodAmountChange = onSelectedFoodAmountChange,
                alternativeFoodAmount = alternativeFoodAmount,
                wrongInput = wrongInput
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            CambiaDietasFoodColumn(
                foodByCategory = foodByCategory,
                onAlternativeFoodChange = onAlternativeFoodChange
            )
        }
    }
}*/

/*@Composable
fun FoodImageComparator(
    modifier: Modifier = Modifier,
    selectedFood: Food,
    alternativeFood: Food,
    selectedFoodAmount: String,
    onSelectedFoodAmountChange: (String) -> Unit,
    alternativeFoodAmount: String,
    wrongInput: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FoodQuantityCard(
            anyFood = selectedFood,
            foodAmount = selectedFoodAmount,
            onFoodAmountChange = onSelectedFoodAmountChange,
            enabled = true,
            wrongInput = wrongInput
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null
        )
        FoodQuantityCard(
            anyFood = alternativeFood,
            foodAmount = alternativeFoodAmount,
            onFoodAmountChange = { },
            enabled = false,
            wrongInput = wrongInput
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodQuantityCard(
    modifier: Modifier = Modifier,
    anyFood: Food,
    foodAmount: String,
    onFoodAmountChange: (String) -> Unit,
    enabled: Boolean,
    wrongInput: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = White,
        elevation = 5.dp
    ) {
        Column(
            modifier = modifier.padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(getResId(anyFood.category, anyFood.name)),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .width(120.dp)
                    .padding(horizontal = 5.dp),
                text = anyFood.name
            )

            TextField(
                value = foodAmount,
                onValueChange = { onFoodAmountChange(it) },
                modifier = Modifier.width(120.dp),
                enabled = enabled,
                label = { Text(text = anyFood.unit) },
                isError = wrongInput,
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = KellyGreen),
            )
        }
    }
}*/
