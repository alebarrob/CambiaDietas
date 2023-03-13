package barrera.alejandro.cambiadietas.change_diet.presentation.start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn


@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onNavigateToSelectedFood: (String, String) -> Unit,
    viewModel: StartViewModel = hiltViewModel()
) {
    /*val categories by startScreenViewModel.categories.collectAsState(initial = listOf())
    val selectedCategory by startScreenViewModel.selectedCategory.collectAsState(initial = "Elige una categoría")
    val foodByCategory by startScreenViewModel.foodByCategory.collectAsState(initial = listOf())
    var expanded by remember { mutableStateOf(false) }
    var selectedFoodName = ""*/

    val state = viewModel.state

    AdaptableColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = stringResource(id = R.string.logo_description)
        )
        Card(
            modifier = modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FoodCategoryMenu(
                    expanded = state.menuIsExpanded,
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,

                    )
            }
        }
        /*if (selectedCategory != "Elige una categoría") {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.food_picker_question),
                fontSize = 20.sp
            )
            CambiaDietasFoodColumn(
                foodByCategory = foodByCategory,
                onSelectedFoodNameChange = onSelectedFoodNameChange,
                onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen
            )
        }*/

    }
}

/*@Composable
private fun FoodPicker(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit,
    foodByCategory: List<Food>,
    onFoodByCategoryChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onSelectedFoodNameChange: (String) -> Unit,
    onNavigateToSelectedFoodScreen: () -> Unit
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
                    foodByCategory = foodByCategory,
                    onSelectedFoodNameChange = onSelectedFoodNameChange,
                    onNavigateToSelectedFoodScreen = onNavigateToSelectedFoodScreen
                )
            }
        }
    }
}*/

@Composable
private fun FoodCategoryMenu(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit = { null },
    onFoodByCategoryChange: (String) -> Unit = { null },
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit = { null }
) {
    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { onExpandedChange(true) },
            shape = MaterialTheme.shapes.small,
            border = BorderStroke((0.5).dp, colorScheme.secondary)
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
                .background(White),
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category) },
                    onClick = {
                        onSelectedCategoryChange(category)
                        onFoodByCategoryChange(category)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

