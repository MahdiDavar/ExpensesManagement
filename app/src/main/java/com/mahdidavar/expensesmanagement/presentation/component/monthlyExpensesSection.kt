package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.ui.ChartCard
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses

@Composable
fun MonthlyExpensesSection(
    monthlyExpenses: List<MonthlyExpenses> ,
    modifier: Modifier = Modifier
){
    ChartCard(
        title = "گزارش ماهانه" ,
        modifier = modifier
    ) {
        if (monthlyExpenses.isEmpty()){
            Text("هنوز هیج هزینه ای ثبت نشده ")
        } else {
            MonthlyExpensesChart(
                data = monthlyExpenses
            )
        }
    }
}