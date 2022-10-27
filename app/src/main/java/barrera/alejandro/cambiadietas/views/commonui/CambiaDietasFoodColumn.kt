package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.models.entities.Food

@Composable
fun CambiaDietasFoodColumn(
    modifier: Modifier = Modifier,
    foodByCategory: List<Food>,
    onSelectedFoodNameChange: ((String) -> Unit)? = null,
    onAlternativeFoodChange: ((Food) -> Unit)? = null,
    onNavigateToSelectedFoodScreen: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        foodByCategory.forEach { item ->
            Button(
                onClick = {
                    onSelectedFoodNameChange?.invoke(item.name)
                    onAlternativeFoodChange?.invoke(item)
                    onNavigateToSelectedFoodScreen?.invoke()
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp),
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}