package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.ui.ChartCard
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses

@Composable
fun CategoryChartSection(
    categoryExpenses : List<CategoryExpenses> ,
    modifier: Modifier = Modifier
) {
    ChartCard(
        title = "دسته بندی هزینه ها" ,
        modifier = modifier
    ) {
        if (categoryExpenses.isEmpty()){
            Text("هنوز هیج هزینه ای ثبت نشده ")
        } else {
            CategoryChartView(
                data = categoryExpenses
            )
        }
    }
}