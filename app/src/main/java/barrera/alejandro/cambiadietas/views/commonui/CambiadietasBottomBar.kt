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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.views.theme.Cadet
import barrera.alejandro.cambiadietas.views.theme.RaisinBlack

@Composable
fun CambiaDietasBottomBar(onScreenChange: (String) -> Unit) {
    var startButtonIsSelected by rememberSaveable { mutableStateOf(true) }
    var categoriesButtonIsSelected by rememberSaveable { mutableStateOf(false) }
    var advicesButtonIsSelected by rememberSaveable { mutableStateOf(false) }

    BottomNavigation {
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = startButtonIsSelected,
            onClick = {
                onScreenChange("startScreen")
                startButtonIsSelected = true
                categoriesButtonIsSelected = false
                advicesButtonIsSelected = false

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
                onScreenChange("categoriesScreen")
                startButtonIsSelected = false
                categoriesButtonIsSelected = true
                advicesButtonIsSelected = false
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
                onScreenChange("tipsScreen")
                startButtonIsSelected = false
                categoriesButtonIsSelected = false
                advicesButtonIsSelected = true
            },
            drawable = R.drawable.ic_nutrition_advices,
            text = R.string.bottom_navigation_nutrition_advices,
            textColor = if (advicesButtonIsSelected) RaisinBlack else Cadet,
            fontWeight = if (advicesButtonIsSelected) FontWeight.ExtraBold else FontWeight.Light
        )
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