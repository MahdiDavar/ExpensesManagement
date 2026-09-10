package com.mahdidavar.expensesmanagement.presentation.domain.rule

import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight

interface AnalysisRule {
    fun generate(
        dailyExpenses: List<DailyReport>,
        categoryExpenses: List<CategoryExpenses>,
        monthlyExpenses: List<MonthlyExpenses>
    ): StatisticsInsight?
}