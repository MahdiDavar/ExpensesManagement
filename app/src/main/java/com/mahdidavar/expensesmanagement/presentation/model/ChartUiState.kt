package com.mahdidavar.expensesmanagement.presentation.model

data class ChartUiState(
    val totalExpenses: Long = 0,
    val averageExpenses: Long = 0,
    val maxExpenses: Long = 0,
    val transactionCount: Int = 0
)