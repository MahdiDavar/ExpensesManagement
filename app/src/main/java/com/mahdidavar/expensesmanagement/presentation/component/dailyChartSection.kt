package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.ui.ChartCard
import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport

@Composable
fun DailyChartSection(
    dailyExpenses: List<DailyReport>,
    period: ChartPeriod,
    onPeriodChange: (ChartPeriod) -> Unit,
    modifier: Modifier = Modifier
) {
    ChartCard(
        title = "گزارش روزانه",
        modifier = modifier,
        action = {
            PeriodMenu(
                selected = period,
                onSelected = onPeriodChange
            )
        }

    ) {
        if (dailyExpenses.isEmpty()) {
            Text("هنوز هیج هزینه ای ثبت نشده ")
        } else {
            DailyChartView(
                dailyExpenses = dailyExpenses
            )
        }
    }
}