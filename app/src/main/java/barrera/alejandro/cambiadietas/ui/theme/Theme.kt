package barrera.alejandro.cambiadietas.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Aquamarine,
    secondary = KellyGreen,
    background = Aquamarine
)

@Composable
fun CambiaDietasAppTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette
    val systemUIController = rememberSystemUiController()

    SideEffect { systemUIController.setSystemBarsColor(color = Aquamarine) }
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}