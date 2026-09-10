package com.mahdidavar.expensesmanagement.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StatCardModel(
    val title : String ,
    val value : String? ,
    val icon : ImageVector ,
    val iconColor : Color
)
