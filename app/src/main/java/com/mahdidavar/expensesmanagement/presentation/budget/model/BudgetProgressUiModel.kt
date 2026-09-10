package com.mahdidavar.expensesmanagement.presentation.budget.model

import androidx.compose.ui.graphics.Color

data class BudgetProgressUiModel(
    val totalBudget: Long,
    val spentAmount: Long,
    val remainingAmount: Long,
    val progress: Float,
    val progressColor: Color
) {
    companion object {

        val Empty = BudgetProgressUiModel(
            totalBudget = 0,
            spentAmount = 0,
            remainingAmount = 0,
            progress = 0f,
            progressColor = Color.Transparent
        )

    }
}