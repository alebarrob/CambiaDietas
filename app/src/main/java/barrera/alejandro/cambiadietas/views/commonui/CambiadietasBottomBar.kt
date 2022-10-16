package barrera.alejandro.cambiadietas.views.commonui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasBottomBarViewModel
import barrera.alejandro.cambiadietas.views.theme.Cadet
import barrera.alejandro.cambiadietas.views.theme.RaisinBlack

@Composable
fun CambiaDietasBottomBar(
    onNavigateToStartScreen: () -> Unit,
    onNavigateToCategoriesScreen: () -> Unit,
    onNavigateToTipsScreen: () -> Unit,
    items: List<Int?>,
    navBackStackEntry: NavBackStackEntry?,
    currentDestination: NavDestination?,
    cambiaDietasBottomBarViewModel: CambiaDietasBottomBarViewModel
) {
    val startButtonIsSelected by cambiaDietasBottomBarViewModel.startButtonIsSelected.observeAsState(initial = true)
    val categoriesButtonIsSelected by cambiaDietasBottomBarViewModel.categoriesButtonIsSelected.observeAsState(initial = false)
    val advicesButtonIsSelected by cambiaDietasBottomBarViewModel.advicesButtonIsSelected.observeAsState(initial = false)

    BottomNavigation {
        items.forEach { screen ->
            CambiaDietasBottomNavigationItem(
                rowScope = this,
                selected = startButtonIsSelected,
                onClick = {
                    onNavigateToStartScreen()
                    cambiaDietasBottomBarViewModel.onStartButtonIsSelectedChange(true)
                    cambiaDietasBottomBarViewModel.onCategoriesButtonIsSelectedChange(false)
                    cambiaDietasBottomBarViewModel.onAdvicesButtonIsSelectedChange(false)
                },
                drawable = R.drawable.ic_start,
                text = R.string.bottom_navigation_start,
                textColor = if (startButtonIsSelected) RaisinBlack else Cadet,
                fontWeight = if (startButtonIsSelected) FontWeight.ExtraBold else FontWeight.Light
            )
            CambiaDietasBottomNavigationItem(
                rowScope = this,
                selected = categoriesButtonIsSelected,
                onClick = {
                    onNavigateToCategoriesScreen()
                    cambiaDietasBottomBarViewModel.onStartButtonIsSelectedChange(false)
                    cambiaDietasBottomBarViewModel.onCategoriesButtonIsSelectedChange(true)
                    cambiaDietasBottomBarViewModel.onAdvicesButtonIsSelectedChange(false)
                },
                drawable = R.drawable.ic_food_categories,
                text = R.string.bottom_navigation_food_categories,
                textColor = if (categoriesButtonIsSelected) RaisinBlack else Cadet,
                fontWeight = if (categoriesButtonIsSelected) FontWeight.ExtraBold else FontWeight.Light
            )
            CambiaDietasBottomNavigationItem(
                rowScope = this,
                selected = advicesButtonIsSelected,
                onClick = {
                    onNavigateToTipsScreen()
                    cambiaDietasBottomBarViewModel.onStartButtonIsSelectedChange(false)
                    cambiaDietasBottomBarViewModel.onCategoriesButtonIsSelectedChange(false)
                    cambiaDietasBottomBarViewModel.onAdvicesButtonIsSelectedChange(true)
                },
                drawable = R.drawable.ic_nutrition_advices,
                text = R.string.bottom_navigation_nutrition_advices,
                textColor = if (advicesButtonIsSelected) RaisinBlack else Cadet,
                fontWeight = if (advicesButtonIsSelected) FontWeight.ExtraBold else FontWeight.Light
            )
        }
    }
}

@Composable
private fun CambiaDietasBottomNavigationItem(
    rowScope: RowScope,
    selected: Boolean,
    onClick: () -> Unit,
    fontWeight: FontWeight,
    textColor: androidx.compose.ui.graphics.Color,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    rowScope.BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(
                painter = painterResource(id = drawable),
                contentDescription = null
            ) },
        label = { Text(
            text = stringResource(text),
            fontWeight = fontWeight,
            color = textColor
        ) },
        selectedContentColor = RaisinBlack,
        unselectedContentColor = Cadet,
    )
}