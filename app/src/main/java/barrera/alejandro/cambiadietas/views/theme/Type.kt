package barrera.alejandro.cambiadietas.views.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R

private val fontFamilyKulim = FontFamily(
    listOf(
        Font(
            resId = R.font.kulim_park_regular
        ),
        Font(
            resId = R.font.kulim_park_light,
            weight = FontWeight.Light
        )
    )
)

private val fontFamilyLato = FontFamily(
    listOf(
        Font(
            resId = R.font.lato_regular
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.Bold
        )
    )
)

val Typography = Typography(
    defaultFontFamily = fontFamilyKulim,
    caption = TextStyle(
        fontSize = 11.sp,
        color = RaisinBlack,
        letterSpacing = (1.15).sp
    ),
    body1 = TextStyle(
        fontFamily = fontFamilyLato,
        fontSize = 20.sp,
        color = RaisinBlack,
        textAlign = TextAlign.Center
    ),
    button = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp,
        letterSpacing = (1.15).sp
    ),
)

/*
More options to override

h1 = TextStyle(
        fontFamily = fontFamilyKulim,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = (1.15).sp
    ),
    h2 = TextStyle(
        fontFamily = fontFamilyKulim,
        fontSize = 15.sp,
        letterSpacing = (1.15).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )
*/