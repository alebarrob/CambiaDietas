package barrera.alejandro.cambiadietas.view.screens

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasContainer
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasFoodColumn
import barrera.alejandro.cambiadietas.view.theme.Aquamarine
import barrera.alejandro.cambiadietas.view.theme.KellyGreen
import barrera.alejandro.cambiadietas.viewmodel.SelectedFoodScreenViewModel
import java.lang.reflect.Field

@Composable
fun SelectedFoodScreen(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    paddingValues: PaddingValues,
    selectedCategory: String,
    foodByCategory: List<Food>,
    selectedFoodName: String,
    context: Context
) {
    val selectedFoodScreenViewModel = hiltViewModel<SelectedFoodScreenViewModel>()
    val selectedFoodAmount by selectedFoodScreenViewModel.selectedFoodAmount.collectAsState(initial = "")
    val alternativeFoodAmount by selectedFoodScreenViewModel.alternativeFoodAmount.collectAsState(initial = "")
    val wrongInput by selectedFoodScreenViewModel.wrongInput.collectAsState(initial = false)
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

    selectedFoodScreenViewModel.onSelectedFoodChange(selectedFoodName)


    CambiaDietasContainer(
        modifier = modifier,
        configuration = configuration,
        paddingValues = paddingValues
    ) {
        FoodComparator(
            selectedFood = selectedFood,
            selectedFoodAmount = selectedFoodAmount,
            onSelectedFoodAmountChange = {
                selectedFoodScreenViewModel.onSelectedFoodAmountChange(
                    selectedFoodAmount = it,
                    selectedCategory = selectedCategory,
                    selectedFood = selectedFood,
                    alternativeFood = alternativeFood
                )
                if (wrongInput) {
                    Toast.makeText(context, "Has introducido un valor incorrecto", Toast.LENGTH_SHORT).show()
                }
            },
            alternativeFood = alternativeFood,
            onAlternativeFoodChange = {
                selectedFoodScreenViewModel.onAlternativeFoodChange(
                    selectedFoodAmount = selectedFoodAmount,
                    selectedCategory = selectedCategory,
                    selectedFood = selectedFood,
                    alternativeFood = it
                )
            },
            alternativeFoodAmount = alternativeFoodAmount,
            foodByCategory = foodByCategory,
            wrongInput = wrongInput
        )
    }
}

@Composable
fun FoodComparator(
    modifier: Modifier = Modifier,
    selectedFood: Food,
    selectedFoodAmount: String,
    onSelectedFoodAmountChange: (String) -> Unit,
    alternativeFood: Food,
    onAlternativeFoodChange: (Food) -> Unit,
    alternativeFoodAmount: String,
    foodByCategory: List<Food>,
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
                selectedFoodAmount = selectedFoodAmount,
                onSelectedFoodAmountChange = onSelectedFoodAmountChange,
                alternativeFood = alternativeFood,
                alternativeFoodAmount = alternativeFoodAmount,
                wrongInput = wrongInput
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp
            )
            CambiaDietasFoodColumn(
                onAlternativeFoodChange = onAlternativeFoodChange,
                foodByCategory = foodByCategory,
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    modifier: Modifier = Modifier,
    selectedFood: Food,
    selectedFoodAmount: String,
    onSelectedFoodAmountChange: (String) -> Unit,
    alternativeFood: Food,
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
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(
            modifier = modifier.padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(getResId(anyFood.drawableName)),
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
                onValueChange = onFoodAmountChange,
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
}

fun getResId(resName: String, c: Class<*> = R.drawable::class.java): Int {
    return try {
        val idField: Field = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}
