package barrera.alejandro.cambiadietas.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val ColorScheme = lightColorScheme(
    background = Aquamarine,
    primary = Malachite,
    onPrimary = RaisinBlack,
    secondary = BabyPowder,
    onSecondary = RaisinBlack,
    tertiary = GhostWhite,
    onTertiary = RaisinBlack
)

@Composable
fun CambiaDietasTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = ColorScheme.primary)
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colorScheme = ColorScheme,
            typography = Typography,
            content = content
        )
    }
}