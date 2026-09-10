package com.mahdidavar.expensesmanagement.presentation.budget.model

import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetProgressUiModel
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningUiModel

data class BudgetAnalysisResult(
    val progress : BudgetProgressUiModel ,
    val warning : BudgetWarningUiModel
)
