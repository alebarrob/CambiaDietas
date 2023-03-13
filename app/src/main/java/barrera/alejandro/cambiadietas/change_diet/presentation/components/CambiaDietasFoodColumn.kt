/*
* Column with a drop-down list of food names that can be selected.
* When selecting a food, different actions can optionally occur.
* This allows the column to be flexible enough to be used on two different
* screens: StartScreen and SelectedFoodScreen.
*/

package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.core.domain.model.Food

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
                    /*
                    * Indicates what happens on the StartScreen when the user chooses an item
                    * of type String representing the name of the food to be replaced.
                    */
                    onSelectedFoodNameChange?.invoke(item.name)

                    /*
                    * Indicates what happens on the SelectedFoodScreen when the user chooses
                    * an item of type Food representing the alternative food he/she wants to eat.
                    */
                    onAlternativeFoodChange?.invoke(item)

                    /*
                    * Indicates the action of navigating from the StartScreen
                    * to the SelectedFoodScreen.
                    */
                    onNavigateToSelectedFoodScreen?.invoke()
                },
                shape = MaterialTheme.shapes.medium,
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
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