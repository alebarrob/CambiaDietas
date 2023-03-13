package barrera.alejandro.cambiadietas.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R

private val fontFamilyKulimPark = FontFamily(
    listOf(
        Font(
            resId = R.font.kulim_park_regular,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.kulim_park_bold,
            weight = FontWeight.Bold
        )
    )
)

private val fontFamilyLato = FontFamily(
    listOf(
        Font(
            resId = R.font.lato_regular,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.Bold
        )
    )
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = fontFamilyKulimPark,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 22.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontFamilyKulimPark,
        textAlign = TextAlign.Center,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyKulimPark,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyLato,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)