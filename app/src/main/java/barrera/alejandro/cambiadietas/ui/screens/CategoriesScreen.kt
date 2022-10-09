package barrera.alejandro.cambiadietas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.data.*
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen

@Composable
fun CategoriesScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .padding(
                start = 15.dp,
                top = 15.dp,
                end = 15.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        categoriesData.forEach { CategoryCard(stringResource(id = it)) }
    }
}

@Composable
fun CategoryCard(foodCategory: String, modifier: Modifier = Modifier) {
    val foodItems = when (foodCategory) {
        "Frutas" -> fruitsData
        "Grasas y Proteínas" -> fatsAndProteinsData
        "Grasas" -> fatsData
        "Carbohidratos" -> carbohydratesData
        "Lácteos" -> dairyData
        else -> listOf()
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White,
        modifier = modifier.padding(5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(6.dp)
        ) {
            Divider(
                color = KellyGreen,
                thickness = (0.5).dp
            )
            Text(
                text = foodCategory,
                fontWeight = FontWeight.SemiBold
            )
            Divider(
                color = KellyGreen,
                thickness = (0.5).dp
            )
            FoodRow(foodItems)
        }
    }
}

@Composable
fun FoodRow(foodItems: List<FoodDrawableStringAmountTriple>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        items(foodItems) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = modifier.padding(top = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = it.drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
                Text(
                    text = stringResource(id = it.text),
                    modifier = Modifier
                        .width(120.dp)
                        .padding(horizontal = 5.dp)
                )
            }
        }
    }
}