package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.*
import barrera.alejandro.cambiadietas.ui.commonui.FoodColumn
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    food: DrawableStringPair,
    foodCategory: String,
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
            food = food,
            foodCategory = foodCategory
        )
    }
}

@Composable
fun FoodComparator(
    food: DrawableStringPair,
    foodCategory: String
) {
    var alternativeFood by rememberSaveable { mutableStateOf(DrawableStringPair(
        R.drawable.food_image_placeholder,
        R.string.food_text_placeholder
    )) }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        modifier = Modifier.padding(start = 30.dp, top = 4.dp, end = 30.dp, bottom = 15.dp),
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodImageComparator(
                food = food,
                alternativeFood = alternativeFood
            )
            Text(
                text = stringResource(id = R.string.food_comparator_question),
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FoodColumn(
                onScreenChange = null,
                foodCategory = foodCategory,
                onFoodChange = { alternativeFood = it }
            )
        }
    }
}

@Composable
fun FoodImageComparator(
    food: DrawableStringPair,
    alternativeFood: DrawableStringPair
) {
    Row {
        FoodQuantityCard(food)
        Spacer(modifier = Modifier.padding(5.dp))
        FoodQuantityCard(alternativeFood)
    }
}

@Composable
fun FoodQuantityCard(food: DrawableStringPair) {
    Column {
        Image(painter = painterResource(id = food.drawable), contentDescription = null)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = stringResource(id = food.text))
    }
}