/*
 * The initial screen where the user can choose the food category and also which food
 * he/she wants to substitute in his/her diet.
 */

package barrera.alejandro.cambiadietas.views.screens

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
import androidx.navigation.NavController
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.models.routes.ScreenNavigation
import barrera.alejandro.cambiadietas.viewmodels.StartScreenViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasContainer
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasFoodColumn
import barrera.alejandro.cambiadietas.views.theme.Aquamarine
import barrera.alejandro.cambiadietas.views.theme.KellyGreen

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    configuration: Configuration,
    navController: NavController,
    startScreenViewModel: StartScreenViewModel
) {
    val categories by startScreenViewModel.categories.collectAsState(initial = listOf())
    val selectedCategory by startScreenViewModel.selectedCategory.collectAsState(initial = "Elige una categoría")
    val foodByCategory by startScreenViewModel.foodByCategory.collectAsState(initial = listOf())
    var expanded by remember { mutableStateOf(false) }
    var selectedFoodName = ""

    CambiaDietasContainer(
        modifier = modifier,
        paddingValues = paddingValues,
        configuration = configuration
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null
        )
        FoodPicker(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelectedCategoryChange = { startScreenViewModel.onSelectedCategoryChange(it) },
            foodByCategory = foodByCategory,
            onFoodByCategoryChange = { startScreenViewModel.onFoodByCategoryChange(it) },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            onSelectedFoodNameChange = { selectedFoodName = it },
            onNavigateToSelectedFoodScreen = {
                navController.navigate(
                    route = "selectedFoodScreen/$selectedFoodName/$selectedCategory"
                ) {
                    popUpTo(ScreenNavigation.StartScreen.route)
                }
            },
        )
    }
}

@Composable
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

