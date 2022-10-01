package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.*
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    selectedFood: DrawableStringPair,
    selectedCategory: String,
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
        FoodComparator(
            selectedFood = selectedFood,
            selectedCategory = selectedCategory
        )
    }
}

@Composable
fun FoodComparator(
    selectedFood: DrawableStringPair,
    selectedCategory: String
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        modifier = Modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodImageComparator(selectedFood = selectedFood)
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FoodColumn(selectedCategory = selectedCategory)
        }
    }
}

@Composable
fun FoodImageComparator(selectedFood: DrawableStringPair) {

}

@Composable
fun FoodQuantityCard() {

}

@Composable
private fun FoodColumn(
    selectedCategory: String,
    modifier: Modifier = Modifier
) {
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