package barrera.alejandro.cambiadietas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.ui.theme.Cadet
import barrera.alejandro.cambiadietas.ui.theme.RaisinBlack

@Composable
fun CambiaDietasApp() {
    Scaffold(
        bottomBar = { CambiaDietasBottomBar() }
    ) {
        StartScreen()
    }
}

@Composable
private fun CambiaDietasBottomBar() {
    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(
                painter = painterResource(id = R.drawable.ic_start),
                contentDescription = null
            ) },
            label = { Text(
                text = stringResource(R.string.bottom_navigation_start),
                fontWeight = FontWeight.Bold
            ) },
            selectedContentColor = RaisinBlack,
            unselectedContentColor = Cadet,
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(
                painter = painterResource(id = R.drawable.ic_food_categories),
                contentDescription = null
            ) },
            label = { Text(text = stringResource(R.string.bottom_navigation_food_categories)) },
            selectedContentColor = RaisinBlack,
            unselectedContentColor = Cadet,
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(
                painter = painterResource(id = R.drawable.ic_nutrition_advices),
                contentDescription = null
            ) },
            label = { Text(text = stringResource(R.string.bottom_navigation_nutrition_advices)) },
            selectedContentColor = RaisinBlack,
            unselectedContentColor = Cadet,
        )
    }
}

@Composable
private fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))
        Image(
            modifier = Modifier.padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null
        )
        Spacer(Modifier.height(16.dp))
        FoodPicker()
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun FoodPicker(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = modifier
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "¿Qué alimento quieres cambiar?\n\n\n\n\n\n\n\n\n")
        }
    }

}