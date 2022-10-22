package barrera.alejandro.cambiadietas.view.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.model.data.Food
import barrera.alejandro.cambiadietas.model.data.categoriesData
import barrera.alejandro.cambiadietas.viewmodel.CommonUiViewModel
import barrera.alejandro.cambiadietas.view.theme.KellyGreen

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    commonUiViewModel: CommonUiViewModel
) {
    val categories by commonUiViewModel.categories.observeAsState(initial = categoriesData)

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
        categories.forEach { category ->
            CategoryCard(
                foodCategory = stringResource(id = category.nameId),
                commonUiViewModel = commonUiViewModel
            )
        }
    }
}

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    foodCategory: String,
    commonUiViewModel: CommonUiViewModel
) {
    Card(
        modifier = modifier.padding(5.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White,
        elevation = (1.5).dp,
        border = BorderStroke((0.5).dp, KellyGreen)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = foodCategory,
                fontWeight = FontWeight.SemiBold
            )
            Divider(
                color = KellyGreen,
                thickness = (0.5).dp
            )
            FoodRow(foodItems = commonUiViewModel.selectFoodItems(foodCategory))
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
        items(foodItems) {
            Column(
                modifier = modifier.padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    painter = painterResource(id = it.imageId),
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
                Text(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(horizontal = 5.dp),
                    text = stringResource(id = it.nameId)
                )
            }
        }
    }
}