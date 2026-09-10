package com.mahdidavar.expensesmanagement.presentation.model

import com.himanshoe.charty.line.data.LineData

data class DailyChartUiModel(
    val chartData : List<LineData>,
    val highExpenses : String ,
    val averageExpenses : String
)
