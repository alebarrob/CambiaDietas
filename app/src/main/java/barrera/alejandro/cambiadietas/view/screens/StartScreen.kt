package barrera.alejandro.cambiadietas.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasContainer
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasFoodColumn
import barrera.alejandro.cambiadietas.view.theme.Aquamarine
import barrera.alejandro.cambiadietas.view.theme.KellyGreen
import barrera.alejandro.cambiadietas.viewmodel.StartScreenViewModel

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    paddingValues: PaddingValues,
    onNavigateToSelectedFoodScreen: () -> Unit,
    startScreenViewModel: StartScreenViewModel,
    foodByCategory: List<Food>,
    onFoodByCategoryChange: (String) -> Unit,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit,
    onSelectedFoodNameChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val categories by startScreenViewModel.categories.collectAsState(initial = listOf())

    CambiaDietasContainer(
        modifier = modifier,
        configuration = configuration,
        paddingValues = paddingValues
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null
        )
        FoodPicker(
            onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen,
            selectedCategory = selectedCategory,
            categories = categories,
            onSelectedCategoryChange = { onSelectedCategoryChange(it) },
            onFoodByCategoryChange = onFoodByCategoryChange,
            onSelectedFoodChange = { onSelectedFoodNameChange(it) },
            foodByCategory = foodByCategory,
            expanded = expanded,
            onExpandedChange = { expanded = it },
        )
    }
}

@Composable
private fun FoodPicker(
    modifier: Modifier = Modifier,
    categories: List<String>,
    onNavigateToSelectedFoodScreen: () -> Unit,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit,
    onFoodByCategoryChange: (String) -> Unit,
    onSelectedFoodChange: (String) -> Unit,
    foodByCategory: List<Food>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
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
                selectedCategory = selectedCategory,
                onSelectedCategoryChange = onSelectedCategoryChange,
                onFoodByCategoryChange = onFoodByCategoryChange,
                expanded = expanded,
                onExpandedChange = onExpandedChange
            )
            if (selectedCategory != "Elige una categoría") {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(id = R.string.food_picker_question),
                    fontSize = 20.sp
                )
                CambiaDietasFoodColumn(
                    onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen,
                    onSelectedFoodChange = onSelectedFoodChange,
                    foodByCategory = foodByCategory,
                )
            }
        }
    }
}

@Composable
private fun FoodCategoryMenu(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit,
    onFoodByCategoryChange: (String) -> Unit,
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
                text = selectedCategory
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
                DropdownMenuItem(onClick = {
                    onSelectedCategoryChange(category)
                    onFoodByCategoryChange(category)
                    onExpandedChange(false)
                }) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = category)
                    }
                }
            }
        }
    }
}

