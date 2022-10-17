package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import barrera.alejandro.cambiadietas.model.data.Screen
import barrera.alejandro.cambiadietas.views.theme.Cadet
import barrera.alejandro.cambiadietas.views.theme.RaisinBlack

@Composable
fun CambiaDietasBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    screens: List<Screen>
) {
    BottomNavigation {
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconImageId!!),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.iconLabelId!!),
                        fontWeight = when {
                            currentDestination?.route == "selectedFoodScreen" && screen.route == "startScreen" -> FontWeight.Bold
                            currentDestination?.hierarchy?.any { (it.route == screen.route) } == true -> FontWeight.Bold
                            else -> FontWeight.Light
                        }
                    )
                },
                selectedContentColor = RaisinBlack,
                unselectedContentColor = Cadet,
                selected = when {
                    currentDestination?.route == "selectedFoodScreen" && screen.route == "startScreen" -> true
                    currentDestination?.hierarchy?.any { (it.route == screen.route) } == true -> true
                    else -> false
                },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}