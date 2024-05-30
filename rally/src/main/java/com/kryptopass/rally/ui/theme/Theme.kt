package com.kryptopass.rally.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kryptopass.rally.R

private val EczarFontFamily = FontFamily(
    Font(R.font.eczar_regular),
    Font(R.font.eczar_semibold, FontWeight.SemiBold)
)
private val RobotoCondensed = FontFamily(
    Font(R.font.robotocondensed_regular),
    Font(R.font.robotocondensed_light, FontWeight.Light),
    Font(R.font.robotocondensed_bold, FontWeight.Bold)
)

/**
 * A [MaterialTheme] for Rally.
 */
@Composable
fun RallyTheme(content: @Composable () -> Unit) {
    // Rally is always dark themed.
    val colors = darkColorScheme(
        primary = Green500,
        surface = DarkBlue900,
        onSurface = Color.White,
        background = DarkBlue900,
        onBackground = Color.White
    )

    val typography = Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.W100,
            fontSize = 96.sp,
            fontFamily = RobotoCondensed,
        ),
        displayMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 44.sp,
            fontFamily = EczarFontFamily,
            letterSpacing = 1.5.sp
        ),
        displaySmall = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.W700,
            fontSize = 34.sp,
            fontFamily = RobotoCondensed
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            fontFamily = RobotoCondensed
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontFamily = EczarFontFamily,
            letterSpacing = 3.sp
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed,
            lineHeight = 20.sp,
            letterSpacing = 3.sp
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed,
            letterSpacing = 0.1.em
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = RobotoCondensed,
            letterSpacing = 0.1.em
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed,
            lineHeight = 20.sp,
            letterSpacing = 0.1.em
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed,
            lineHeight = 16.sp,
            letterSpacing = 0.2.em
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 12.sp
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            fontFamily = RobotoCondensed
        )
    )
    MaterialTheme(colorScheme = colors, typography = typography, content = content)
}

/**
 * A theme overlay used for dialogs.
 */
@Composable
fun RallyDialogThemeOverlay(content: @Composable () -> Unit) {
    // Rally is always dark themed.
    val dialogColors = darkColorScheme(
        primary = Color.White,
        surface = Color.White.copy(alpha = 0.12f).compositeOver(Color.Black),
        onSurface = Color.White
    )

    // Copy the current [Typography] and replace some text styles for this theme.
    val currentTypography = MaterialTheme.typography
    val dialogTypography = currentTypography.copy(
        bodyMedium = currentTypography.bodyLarge.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 1.sp
        ),
        labelLarge = currentTypography.labelLarge.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.2.em
        )
    )
    MaterialTheme(colorScheme = dialogColors, typography = dialogTypography, content = content)
}