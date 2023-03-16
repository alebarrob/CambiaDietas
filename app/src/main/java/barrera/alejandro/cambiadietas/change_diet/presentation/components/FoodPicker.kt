package barrera.alejandro.cambiadietas.change_diet.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing

@Composable
fun FoodPicker(
    modifier: Modifier = Modifier,
    headline: String,
    selectedCategory: String,
    foods: List<Food>,
    onFoodClick: (
        foodName: String,
        foodCategory: String
    ) -> Unit
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        if (selectedCategory != "Elige una categoría") {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(spacing.spaceMedium),
                    text = headline,
                    style = typography.displaySmall
                )
                FoodColumn(
                    foods = foods,
                    onFoodClick = onFoodClick
                )
            }
        }
    }
}

@Composable
private fun FoodColumn(
    modifier: Modifier = Modifier,
    foods: List<Food>,
    onFoodClick: (
        foodName: String,
        foodCategory: String
    ) -> Unit
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = modifier
            .padding(
                vertical = spacing.spaceExtraSmall,
                horizontal = spacing.spaceSmall
            )
            .height(200.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(foods) { food ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onFoodClick(
                        food.name,
                        food.category
                    )
                },
                shape = shapes.extraSmall,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                contentPadding = PaddingValues(spacing.spaceMedium)
            ) {
                Text(
                    text = food.name,
                    style = typography.displaySmall
                )
            }
        }
    }
}