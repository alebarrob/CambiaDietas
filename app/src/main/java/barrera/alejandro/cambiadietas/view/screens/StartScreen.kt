package barrera.alejandro.cambiadietas.view.screens

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
import barrera.alejandro.cambiadietas.model.entities.Category
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.model.data.categoriesData
import barrera.alejandro.cambiadietas.viewmodel.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodel.StartScreenViewModel
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasContainer
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasFoodColumn
import barrera.alejandro.cambiadietas.view.theme.Aquamarine
import barrera.alejandro.cambiadietas.view.theme.KellyGreen

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigateToSelectedFoodScreen: () -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (Food) -> Unit,
    foodItems: List<Food>,
    commonUiViewModel: CommonUiViewModel,
    startScreenViewModel: StartScreenViewModel
) {
    val expanded by startScreenViewModel.expanded.observeAsState(initial = false)
    val categories by commonUiViewModel.categories.observeAsState(initial = categoriesData)

    CambiaDietasContainer(
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
            categories = categories,
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
    categories: List<Category>,
    onNavigateToSelectedFoodScreen: () -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (Food) -> Unit,
    foodItems: List<Food>,
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
                categories = categories,
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
                CambiaDietasFoodColumn(
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
    categories: List<Category>,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
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
            categories.forEach { category ->
                val newCategory = stringResource(id = category.nameId)

                DropdownMenuItem(onClick = {
                    onFoodCategoryChange(newCategory)
                    onExpandedChange(false)
                }) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = category.nameId))
                    }
                }
            }
        }
    }
}

