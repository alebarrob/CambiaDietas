package barrera.alejandro.cambiadietas.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    getDrawableId: (foodCategory: String, foodName: String) -> Int,
    food: Food
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = spacing.spaceMedium)
                .height(115.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = spacing.spaceExtraSmall,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(
                    getDrawableId(
                        food.category,
                        food.name
                    )
                ),
                contentDescription = stringResource(id = R.string.food_image_description),
                contentScale = ContentScale.Inside
            )
            Text(
                modifier = Modifier
                    .width(120.dp)
                    .padding(
                        vertical = spacing.spaceExtraSmall,
                        horizontal = spacing.spaceSmall
                    ),
                text = food.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}