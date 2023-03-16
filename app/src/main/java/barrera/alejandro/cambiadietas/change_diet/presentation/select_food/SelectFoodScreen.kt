package barrera.alejandro.cambiadietas.change_diet.presentation.select_food

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.change_diet.presentation.components.FoodPicker
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing


@Composable
fun SelectFoodScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectFoodViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onNavigateToSelectedFood: (
        foodName: String,
        foodCategory: String
    ) -> Unit
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(SelectFoodEvent.LoadCategories)
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
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = stringResource(id = R.string.logo_description)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.displayLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        FoodCategoryMenu(
            menuIsExpanded = state.menuIsExpanded,
            categories = state.categories,
            selectedCategory = state.selectedCategory,
            onFoodCategoryMenuDismissRequest = {
                viewModel.onEvent(SelectFoodEvent.OnFoodCategoryMenuDismissRequest)
            },
            onSelectedCategoryChange = { selectedCategory ->
                viewModel.onEvent(SelectFoodEvent.OnSelectedCategoryChange(selectedCategory))
            },
            onFoodCategoryButtonClick = {
                viewModel.onEvent(SelectFoodEvent.OnFoodCategoryButtonClick)
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        FoodPicker(
            headline = stringResource(id = R.string.food_picker_headline_start),
            selectedCategory = state.selectedCategory,
            foods = state.foods,
            onFoodClick = { foodName, foodCategory ->
                onNavigateToSelectedFood(
                    foodName,
                    foodCategory
                )
            }
        )
    }
}

@Composable
private fun FoodCategoryMenu(
    modifier: Modifier = Modifier,
    menuIsExpanded: Boolean,
    categories: List<String>,
    selectedCategory: String,
    onFoodCategoryButtonClick: () -> Unit,
    onSelectedCategoryChange: (String) -> Unit,
    onFoodCategoryMenuDismissRequest: () -> Unit,
) {
    Box(modifier = modifier) {
        CategoryButton(
            selectedCategory = selectedCategory,
            onFoodCategoryButtonClick = onFoodCategoryButtonClick
        )
        DropdownMenu(
            modifier = Modifier.background(color = colorScheme.secondary),
            expanded = menuIsExpanded,
            onDismissRequest = onFoodCategoryMenuDismissRequest
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category.uppercase()) },
                    onClick = { onSelectedCategoryChange(category) }
                )
            }
        }
    }
}

@Composable
private fun CategoryButton(
    modifier: Modifier = Modifier,
    selectedCategory: String,
    onFoodCategoryButtonClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Button(
        onClick = onFoodCategoryButtonClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.secondary),
        border = BorderStroke(2.dp, colorScheme.primary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        contentPadding = PaddingValues(spacing.spaceMedium)
    ) {
        Text(
            text = selectedCategory.uppercase(),
            style = typography.displayMedium
        )
    }
}
