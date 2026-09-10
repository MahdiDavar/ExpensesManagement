package com.mahdidavar.expensesmanagement.presentation.budget.model

import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity

data class BudgetUiState(
    val budget: BudgetEntity? = null,
    val progressUiModel: BudgetProgressUiModel = BudgetProgressUiModel.Empty,
    val warning: BudgetWarningUiModel = BudgetWarningUiModel.Empty,
    val isLoading: Boolean = false,
    val error: String? = null
)