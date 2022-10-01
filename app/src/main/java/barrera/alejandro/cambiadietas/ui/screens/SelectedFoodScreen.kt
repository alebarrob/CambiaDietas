package barrera.alejandro.cambiadietas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import barrera.alejandro.cambiadietas.data.DrawableStringPair

@Composable
fun SelectedFoodScreen(
    paddingValues: PaddingValues,
    selectedFood: DrawableStringPair,
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
        FoodComparator(selectedFood)
    }
}

@Composable
fun FoodComparator(selectedFood: DrawableStringPair) {
    Image(painter = painterResource(id = selectedFood.drawable), contentDescription = null)
    Text(text = stringResource(id = selectedFood.text))
}