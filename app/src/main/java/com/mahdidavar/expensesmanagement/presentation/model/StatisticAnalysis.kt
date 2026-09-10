package com.mahdidavar.expensesmanagement.presentation.model

data class StatisticAnalysis(
    val totalExpenses : Long ,
    val averageExpenses : Long ,
    val maxExpenseDay : DailyReport? ,
    val topCategory : CategoryExpenses? ,
    val invoiceCount : Int ,
    val insights : List<StatisticsInsight>
)
