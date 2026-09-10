package com.mahdidavar.expensesmanagement.presentation.domain.analysis

import com.mahdidavar.expensesmanagement.presentation.domain.rule.AverageExpensesRule
import com.mahdidavar.expensesmanagement.presentation.domain.rule.HighestExpensesRule
import com.mahdidavar.expensesmanagement.presentation.domain.rule.TopCategoryRule
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticAnalysis
import javax.inject.Inject

class StatisticsAnalyzer @Inject constructor() {
    private val rules = listOf(
        TopCategoryRule(),
        HighestExpensesRule(),
        AverageExpensesRule()
    )

    fun analyze(
        dailyExpenses: List<DailyReport>,
        categoryExpenses: List<CategoryExpenses>,
        monthlyExpenses: List<MonthlyExpenses>
    ): StatisticAnalysis {

        val insight = rules.mapNotNull {
            it.generate(
                dailyExpenses,
                categoryExpenses,
                monthlyExpenses
            )
        }

        val totalExpenses =
            dailyExpenses.sumOf { it.total }

        val averageExpenses =
            if (dailyExpenses.isEmpty())
                0
            else
                totalExpenses / dailyExpenses.size

        val maxExpensesDay =
            dailyExpenses.maxByOrNull { it.total }

        val topCategory =
            categoryExpenses.maxByOrNull { it.total }

        val invoiceCount =
            dailyExpenses.size

        return StatisticAnalysis(
            totalExpenses = totalExpenses,
            averageExpenses = averageExpenses,
            maxExpenseDay = maxExpensesDay,
            topCategory = topCategory,
            invoiceCount = invoiceCount,
            insights = insight
        )
    }

}