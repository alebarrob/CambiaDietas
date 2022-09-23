package barrera.alejandro.cambiadietas

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine

@Composable
fun StartScreen(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
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
            contentDescription = null,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        FoodPicker()
    }
}

@Composable
private fun FoodPicker(modifier: Modifier = Modifier) {
    var selectedCategory by rememberSaveable { mutableStateOf("Elige una categoría") }

    Card(
        modifier = modifier.padding(horizontal = 30.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodCategoryMenu(
                selectedCategory = selectedCategory,
                onSelectedCategory = { selectedCategory = it }
            )
            if (selectedCategory != "Elige una categoría") {
                Text(
                    text = stringResource(id = R.string.foodpicker_food_question),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                FoodRow(selectedCategory = selectedCategory)
            }
        }
    }
}

@Composable
private fun FoodCategoryMenu(
    modifier: Modifier = Modifier,
    selectedCategory: String,
    onSelectedCategory: (String) -> Unit
) {
    val items = categoriesData
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Surface(shape = MaterialTheme.shapes.small) {
            Text(
                text = selectedCategory,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .clickable(onClick = { expanded = true })
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
            items.forEach { item ->
                val category = stringResource(id = item)

                DropdownMenuItem(onClick = {
                    onSelectedCategory(category)
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
private fun FoodRow(modifier: Modifier = Modifier, selectedCategory: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(PaddingValues(horizontal = 10.dp))
    ) {
        val foodData = when (selectedCategory) {
            "Frutas" -> fruitsData
            "Grasas y Proteínas" -> fatsAndProteinsData
            "Grasas" -> fatsData
            "Carbohidratos" -> carbohydratesData
            "Lácteos" -> dairyData
            else -> listOf()
        }
        foodData.forEach { item ->
            FoodImage(item.drawable, item.text)
        }
    }
}

/* Alternative FoodRow

@Composable
private fun FoodRow(modifier: Modifier = Modifier, selectedCategory: String) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = modifier
    ) {
        items(
            when (selectedCategory) {
                "Frutas" -> fruitsData
                "Grasas y Proteínas" -> fatsAndProteinsData
                "Grasas" -> fatsData
                "Carbohidratos" -> carbohydratesData
                "Lácteos" -> dairyData
                else -> listOf()
            }
        ) { item ->
            FoodImage(item.drawable, item.text)
        }
    }

 */

@Composable
private fun FoodImage(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 10.dp,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }
}

