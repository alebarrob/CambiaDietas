package barrera.alejandro.cambiadietas.view.commonui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.viewmodel.CommonUiViewModel

@Composable
fun CambiaDietasFoodColumn(
    modifier: Modifier = Modifier,
    commonUiViewModel: CommonUiViewModel,
    onNavigateToSelectedFoodScreen: (() -> Unit)? = null,
    foodCategory: String,
    onFoodChange: ((Food) -> Unit)? = null,
    onAlternativeFoodChange: ((Food) -> Unit)? = null,
    foodItems: List<Food>
) {

    commonUiViewModel.loadFoodItems(foodCategory)

    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        foodItems.forEach { item ->
            Button(
                onClick = {
                    onNavigateToSelectedFoodScreen?.invoke()
                    onFoodChange?.invoke(item)
                    onAlternativeFoodChange?.invoke(item)
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp),
                    text = stringResource(id = item.nameId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}