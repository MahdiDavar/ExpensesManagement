package com.mahdidavar.expensesmanagement.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BtnNavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BtnNavScreen(route = "homeScreen", title = "خانه", icon = Icons.Filled.Home)
    object Setting : BtnNavScreen(route = "setting", title = "تنظیمات", icon = Icons.Filled.Settings)
    object Chart : BtnNavScreen(route = "chart", title = "آمار", icon = Icons.Filled.BarChart)
    object Invoices : BtnNavScreen(route = "invoices", title = "فاکتور ها", icon = Icons.AutoMirrored.Filled.List)

    companion object {
        val navItem = listOf(Setting, Chart, Invoices, Home)
    }
}