package com.mahdidavar.expensesmanagement.presentation.model

    data class DashboardUiModel(
        val totalExpenses : String = "0",
        val averageExpense : String = "0",
        val invoiceCount : String = "0",
        val topCategory : String? = null
    )
