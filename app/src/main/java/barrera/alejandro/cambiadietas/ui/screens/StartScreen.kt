package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.*
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen

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
            contentDescription = null
        )
        FoodPicker()
    }
}

@Composable
private fun FoodPicker(modifier: Modifier = Modifier) {
    var selectedCategory by rememberSaveable { mutableStateOf("Elige una categoría") }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        modifier = modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
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
                FoodColumn(selectedCategory = selectedCategory)
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
        OutlinedButton(
            onClick = { expanded = true },
            shape = MaterialTheme.shapes.small,
            border = BorderStroke((0.5).dp, KellyGreen)
        ) {
            Text(
                text = selectedCategory,
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
private fun FoodColumn(modifier: Modifier = Modifier, selectedCategory: String) {
    var foodData by remember { mutableStateOf(listOf<DrawableStringPair>()) }

    foodData = when (selectedCategory) {
        "Frutas" -> fruitsData
        "Grasas y Proteínas" -> fatsAndProteinsData
        "Grasas" -> fatsData
        "Carbohidratos" -> carbohydratesData
        "Lácteos" -> dairyData
        else -> listOf()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .verticalScroll(rememberScrollState())
    ) {
         foodData.forEach { item ->
             Button(
                 onClick = {  },
                 shape = MaterialTheme.shapes.medium,
                 colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
             ) {
                 Text(
                     text = stringResource(id = item.text),
                     fontSize = 18.sp,
                     fontWeight = FontWeight.Light,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(horizontal = 5.dp, vertical = 10.dp)
                 )
             }

        }
    }
}

