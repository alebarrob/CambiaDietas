package barrera.alejandro.cambiadietas.change_diet.presentation.select_alternative_food

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.change_diet.presentation.components.FoodPicker
import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn
import barrera.alejandro.cambiadietas.core.presentation.components.FoodCard
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing
import barrera.alejandro.cambiadietas.core.util.UiEvent

@Composable
fun SelectedFoodScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: SelectAlternativeFoodViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(SelectAlternativeFoodEvent.LoadSelectedFood)
        viewModel.onEvent(SelectAlternativeFoodEvent.LoadFoodsByCategory)
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
        FoodImageComparator(
            getDrawableId = { foodCategory, foodName ->
                viewModel.getDrawableId(
                    foodCategory,
                    foodName
                )
            },
            selectedFood = state.selectedFood,
            alternativeFood = state.alternativeFood,
            selectedFoodAmount = state.selectedFoodAmount,
            onSelectedFoodAmountChange = { selectedFoodAmount ->
                viewModel.onEvent(SelectAlternativeFoodEvent.OnSelectedFoodAmountChange(selectedFoodAmount))
            },
            alternativeFoodAmount = state.alternativeFoodAmount
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        FoodPicker(
            headline = stringResource(id = R.string.food_picker_headline_selected_food),
            selectedCategory = state.selectedFoodCategory,
            foods = state.foodsByCategory,
            onFoodClick = { foodName, foodCategory ->
                viewModel.onEvent(
                    SelectAlternativeFoodEvent.OnFoodClick(
                        foodName = foodName,
                        foodCategory = foodCategory
                    )
                )
            }
        )
    }
}

@Composable
fun FoodImageComparator(
    modifier: Modifier = Modifier,
    getDrawableId: (foodCategory: String, foodName: String) -> Int,
    selectedFood: Food,
    alternativeFood: Food,
    selectedFoodAmount: String,
    onSelectedFoodAmountChange: (String) -> Unit,
    alternativeFoodAmount: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FoodQuantityCard(
            getDrawableId = getDrawableId,
            food = selectedFood,
            foodAmount = selectedFoodAmount,
            onFoodAmountChange = onSelectedFoodAmountChange,
            enabled = true
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = stringResource(id = R.string.arrow_icon_description)
        )
        FoodQuantityCard(
            getDrawableId = getDrawableId,
            food = alternativeFood,
            foodAmount = alternativeFoodAmount,
            onFoodAmountChange = { },
            enabled = false
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FoodQuantityCard(
    modifier: Modifier = Modifier,
    getDrawableId: (foodCategory: String, foodName: String) -> Int,
    food: Food,
    foodAmount: String,
    onFoodAmountChange: (String) -> Unit,
    enabled: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FoodCard(
            getDrawableId = getDrawableId,
            food = food
        )
        OutlinedTextField(
            value = foodAmount,
            onValueChange = { onFoodAmountChange(it) },
            modifier = Modifier.width(120.dp),
            enabled = enabled,
            label = { Text(text = food.unit) },
            shape = shapes.extraSmall,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorScheme.secondary,
                focusedTextColor = colorScheme.onSecondary,
                unfocusedTextColor = colorScheme.onSecondary,
                disabledTextColor = colorScheme.onSecondary,
                focusedLabelColor = colorScheme.onSecondary,
                unfocusedLabelColor = colorScheme.onSecondary,
                disabledLabelColor = colorScheme.onSecondary,
                disabledIndicatorColor = colorScheme.primary,
                unfocusedIndicatorColor = colorScheme.primary
            )
        )
    }
}
