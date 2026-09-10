package com.mahdidavar.expensesmanagement.presentation.statistics

import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.DashboardUiModel
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticAnalysis

data class StatisticsUiState(
    //نمودار روزانه
    val dailyExpenses: List<DailyReport> = emptyList(),
    //نمودار دسته بندی
    val categoryExpenses: List<CategoryExpenses> = emptyList(),
    //نمودار ماهانه
    val monthlyExpenses: List<MonthlyExpenses> = emptyList(),
    //آنالیزور
    val analysis: StatisticAnalysis? = null,
    //داشبورد
    val dashboard : DashboardUiModel? = null,
    //کارت ها
    val totalExpense: Long = 0,
    val averageExpense: Long = 0,
    val maxExpense: Long = 0,
    //تعداد فاکتور ها
    val invoiceCount: Int = 0,
    //وضعیت لودینگ
    val isLoading: Boolean = false,
    //پیام خطا
    val error: String? = null
)


/*
statistics/
│
├── StatisticsScreen.kt
├── StatisticsViewModel.kt
│
├── model/
│   ├── DailyExpense.kt
│   ├── CategoryExpense.kt
│   └── StatisticsUiState.kt
│
├── components/
│   ├── DailyExpenseChart.kt
│   ├── CategoryPieChart.kt
│   ├── StatisticsCard.kt
│   └── FilterBar.kt
│
└── utils/
└── NumberFormatter.kt
*/