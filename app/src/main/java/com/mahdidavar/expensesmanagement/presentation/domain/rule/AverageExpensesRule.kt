package com.mahdidavar.expensesmanagement.presentation.domain.rule

import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight
import com.mahdidavar.expensesmanagement.utills.NumberFormatter

class AverageExpensesRule : AnalysisRule {
    override fun generate(
        dailyExpenses: List<DailyReport>,
        categoryExpenses: List<CategoryExpenses>,
        monthlyExpenses: List<MonthlyExpenses>
    ): StatisticsInsight? {
        if (dailyExpenses.isEmpty())
            return null

        val average = dailyExpenses.sumOf { it.total } / dailyExpenses.size

        return StatisticsInsight(
            icon = "\uD83D\uDCCA" ,
            title = "میانگین هزینه ها" ,
            description = NumberFormatter.formatPrice(average)
        )
    }
}