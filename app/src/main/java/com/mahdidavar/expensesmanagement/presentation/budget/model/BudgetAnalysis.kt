package com.mahdidavar.expensesmanagement.presentation.budget.model

data class BudgetAnalysis (
    val totalBudget: Long,
    val spentAmount: Long,
    val remainingAmount: Long,
    val progress: Float ,
    val status : BudgetWarningType
)