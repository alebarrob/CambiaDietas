/*
 * Screen that shows which foods
 * belong to the different categories.
 */

package barrera.alejandro.cambiadietas.views.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.viewmodels.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.views.commonui.AdvertView
import barrera.alejandro.cambiadietas.views.commonui.getResId
import barrera.alejandro.cambiadietas.views.theme.KellyGreen
import barrera.alejandro.cambiadietas.views.theme.White

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    categoriesScreenViewModel: CategoriesScreenViewModel
) {
    val fruitItems by categoriesScreenViewModel.fruitItems.collectAsState(initial = listOf())
    val fatsProteinsItems by categoriesScreenViewModel.fatsProteinsItems.collectAsState(initial = listOf())
    val fatsItems by categoriesScreenViewModel.fatsItems.collectAsState(initial = listOf())
    val carbohydratesItems by categoriesScreenViewModel.carbohydratesItems.collectAsState(initial = listOf())
    val dairyItems by categoriesScreenViewModel.dairyItems.collectAsState(initial = listOf())
    val foodItems = listOf(fruitItems, fatsProteinsItems, fatsItems, carbohydratesItems, dairyItems)

    Column(
        modifier = modifier
            .padding(
                start = 15.dp,
                top = 15.dp,
                end = 15.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AdvertView()
        foodItems.forEach { items ->
            CategoryCard(items = items)
        }
        AdvertView()
    }
}

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    items: List<Food>
) {
    Card(
        modifier = modifier.padding(5.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = White,
        elevation = (1.5).dp,
        border = BorderStroke((0.5).dp, KellyGreen)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = if (items.isEmpty()) "" else items[0].category,
                fontWeight = FontWeight.SemiBold
            )
            Divider(
                color = KellyGreen,
                thickness = (0.5).dp
            )
            FoodRow(foodItems = items)
        }
    }
}

@Composable
fun FoodRow(
    modifier: Modifier = Modifier,
    foodItems: List<Food>
) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        items(foodItems) { foodItem ->
            Column(
                modifier = modifier.padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    painter = painterResource(getResId(foodItem.category, foodItem.name)),
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
                Text(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(horizontal = 5.dp),
                    text = foodItem.name
                )
            }
        }
    }
}