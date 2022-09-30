package barrera.alejandro.cambiadietas.ui.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.ui.theme.Cadet
import barrera.alejandro.cambiadietas.ui.theme.RaisinBlack

@Composable
fun CambiaDietasBottomBar(onCurrentScreen: (String) -> Unit) {
    BottomNavigation {
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = true,
            onClick = { onCurrentScreen("startScreen") },
            drawable = R.drawable.ic_start,
            text = R.string.bottom_navigation_start,
            fontWeight = FontWeight.Bold
        )
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = false,
            onClick = { onCurrentScreen("categoriesScreen") },
            drawable = R.drawable.ic_food_categories,
            text = R.string.bottom_navigation_food_categories,
            fontWeight = FontWeight.Light
        )
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = false,
            onClick = { onCurrentScreen("tipsScreen") },
            drawable = R.drawable.ic_nutrition_advices,
            text = R.string.bottom_navigation_nutrition_advices,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun CambiaDietasBottomNavigationItem(
    rowScope: RowScope,
    selected: Boolean,
    onClick: () -> Unit,
    fontWeight: FontWeight,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    rowScope.BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = null
            )
        },
        label = { Text(
            text = stringResource(text),
            fontWeight = fontWeight
        ) },
        selectedContentColor = RaisinBlack,
        unselectedContentColor = Cadet,
    )
}