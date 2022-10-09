package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.FoodDrawableStringAmountTriple
import barrera.alejandro.cambiadietas.data.categoriesData
import barrera.alejandro.cambiadietas.ui.commonui.FoodColumn
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen

@Composable
fun StartScreen(
    paddingValues: PaddingValues,
    onScreenChange: (String) -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (FoodDrawableStringAmountTriple) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = paddingValues.calculateBottomPadding())
            else -> modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null
        )
        FoodPicker(
            onScreenChange = onScreenChange,
            foodCategory = foodCategory,
            onFoodCategoryChange = onFoodCategoryChange,
            onFoodChange = onFoodChange
        )
    }
}

@Composable
private fun FoodCategoryMenu(
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            shape = MaterialTheme.shapes.small,
            border = BorderStroke((0.5).dp, KellyGreen)
        ) {
            Text(
                text = foodCategory,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(Color.White)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            val categoriesItems = categoriesData

            categoriesItems.forEach { item ->
                val newCategory = stringResource(id = item)

                DropdownMenuItem(onClick = {
                    onFoodCategoryChange(newCategory)
                    expanded = false
                }) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = item))
                    }
                }
            }
        }
    }
}

@Composable
private fun FoodPicker(
    onScreenChange: (String) -> Unit,
    foodCategory: String,
    onFoodCategoryChange: (String) -> Unit,
    onFoodChange: (FoodDrawableStringAmountTriple) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = (1.5).dp,
        backgroundColor = Aquamarine,
        modifier = modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodCategoryMenu(
                foodCategory = foodCategory,
                onFoodCategoryChange = onFoodCategoryChange
            )
            if (foodCategory != "Elige una categoría") {
                Text(
                    text = stringResource(id = R.string.food_picker_question),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                FoodColumn(
                    onScreenChange = onScreenChange,
                    foodCategory = foodCategory,
                    onFoodChange = onFoodChange,
                )
            }
        }
    }
}

