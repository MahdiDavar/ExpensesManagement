package com.mahdidavar.expensesmanagement.presentation.domain.rule

import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight

class TopCategoryRule : AnalysisRule {
    override fun generate(
        dailyExpenses: List<DailyReport>,
        categoryExpenses: List<CategoryExpenses>,
        monthlyExpenses: List<MonthlyExpenses>
    ): StatisticsInsight? {
       val top = categoryExpenses.maxByOrNull { it.total } ?: return  null
        return StatisticsInsight(
            icon = "\uD83C\uDFC6" ,
            title = "بیشترین هزینه" ,
            description = " بیشترین هزینه شما مربوط به ${top.category.title} است "
        )
    }
}