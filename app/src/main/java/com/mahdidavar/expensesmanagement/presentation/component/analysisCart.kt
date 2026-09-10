package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.presentation.model.StatisticAnalysis
import com.mahdidavar.expensesmanagement.utills.NumberFormatter

@Composable
fun AnalysisCart(
    analysis: StatisticAnalysis
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            Modifier.padding(20.dp)
        ) {
            Text("تحلیل هزینه ها" , color = Color.Black , fontSize = 20.sp ,style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))
            Text("مجموع هزینه ها :${
                NumberFormatter.formatPrice(analysis.totalExpenses)
            }")
            Spacer(Modifier.height(10.dp))
            Text(" : ${
                NumberFormatter.formatPrice(analysis.averageExpenses)
            }میانگین روزانه")
            Spacer(Modifier.height(10.dp))
            analysis.topCategory?.let {
                Text("بیشترین دسته : ${it.category.title}")
            }
            Spacer(Modifier.height(10.dp))
            analysis.maxExpenseDay?.let {
                Text("پر هزینه ترین روز : ${it.date}")
            }
        }
    }
}