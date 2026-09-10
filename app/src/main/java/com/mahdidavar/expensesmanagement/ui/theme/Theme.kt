package com.mahdidavar.expensesmanagement.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = NewYellowDark,
    secondary = PurpleGrey40,
    tertiary = Pink40  ,
    primaryContainer = Color(0xffFCF3EC)
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    secondary = PurpleGrey80,
    tertiary = Pink80 ,
    primaryContainer = Color(0xFF0B1220)
)

@Composable
fun ExpensesManagementTheme(
    darkTheme : Boolean ,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else  LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}