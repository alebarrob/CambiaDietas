package barrera.alejandro.cambiadietas

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.ui.theme.RaisinBlack
import barrera.alejandro.cambiadietas.ui.theme.LightGunmetal

@Composable
fun CambiaDietasApp() {
    Scaffold(
        bottomBar = { CambiaDietasBottomBar() }
    ) {

    }
}

@Composable
fun CambiaDietasBottomBar() {
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
            unselectedContentColor = LightGunmetal,
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
            unselectedContentColor = LightGunmetal,
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
            unselectedContentColor = LightGunmetal,
        )
    }
}