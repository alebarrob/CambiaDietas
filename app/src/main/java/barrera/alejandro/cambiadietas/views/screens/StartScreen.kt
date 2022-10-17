package barrera.alejandro.cambiadietas.views.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.model.data.categoriesData
import barrera.alejandro.cambiadietas.viewmodels.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodels.StartScreenViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasColumn
import barrera.alejandro.cambiadietas.views.commonui.FoodColumn
import barrera.alejandro.cambiadietas.views.theme.Aquamarine
import barrera.alejandro.cambiadietas.views.theme.KellyGreen

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigateToSelectedFoodScreen: () -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (FoodDrawableStringAmountTriple) -> Unit,
    foodItems: List<FoodDrawableStringAmountTriple>,
    commonUiViewModel: CommonUiViewModel,
    startScreenViewModel: StartScreenViewModel
) {
    val expanded by startScreenViewModel.expanded.observeAsState(initial = false)

    CambiaDietasColumn(
        modifier = modifier,
        paddingValues = paddingValues
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null
        )
        FoodPicker(
            onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen,
            foodCategory = foodCategory,
            onFoodCategoryChange = onFoodCategoryChange,
            onFoodChange = onFoodChange,
            foodItems = foodItems,
            expanded = expanded,
            onExpandedChange = { startScreenViewModel.onExpandedChange(it) },
            commonUiViewModel = commonUiViewModel
        )
    }
}

@Composable
private fun FoodPicker(
    modifier: Modifier = Modifier,
    onNavigateToSelectedFoodScreen: () -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (FoodDrawableStringAmountTriple) -> Unit,
    foodItems: List<FoodDrawableStringAmountTriple>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    commonUiViewModel: CommonUiViewModel
) {
    Card(
        modifier = modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = (1.5).dp,
        backgroundColor = Aquamarine
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodCategoryMenu(
                foodCategory = foodCategory,
                onFoodCategoryChange = onFoodCategoryChange,
                expanded = expanded,
                onExpandedChange = onExpandedChange
            )
            if (foodCategory != "Elige una categoría") {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(id = R.string.food_picker_question),
                    fontSize = 20.sp
                )
                FoodColumn(
                    onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen,
                    foodCategory = foodCategory,
                    onFoodChange = onFoodChange,
                    foodItems = foodItems,
                    commonUiViewModel = commonUiViewModel
                )
            }
        }
    }
}

@Composable
private fun FoodCategoryMenu(
    modifier: Modifier = Modifier,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    val categoriesItems = categoriesData

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { onExpandedChange(true) },
            shape = MaterialTheme.shapes.small,
            border = BorderStroke((0.5).dp, KellyGreen)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(Color.White),
                text = foodCategory
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            categoriesItems.forEach { item ->
                val newCategory = stringResource(id = item)

                DropdownMenuItem(onClick = {
                    onFoodCategoryChange(newCategory)
                    onExpandedChange(false)
                }) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = item))
                    }
                }
            }
        }
    }
}

