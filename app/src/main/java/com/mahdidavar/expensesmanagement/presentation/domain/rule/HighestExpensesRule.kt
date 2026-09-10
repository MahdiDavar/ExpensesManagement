package com.mahdidavar.expensesmanagement.presentation.domain.rule

import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight

class HighestExpensesRule : AnalysisRule {
    override fun generate(
        dailyExpenses: List<DailyReport>,
        categoryExpenses: List<CategoryExpenses>,
        monthlyExpenses: List<MonthlyExpenses>
    ): StatisticsInsight? {
        val maxDay =
            dailyExpenses.maxByOrNull { it.total } ?: return null
        return StatisticsInsight(
            icon ="\uD83D\uDCC5" ,
            title = "پر هزینه ترین روز" ,
            description = maxDay.date
        )
    }
}