package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.charty.color.ChartyColor
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.data.LineData
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.utills.NumberFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyChartView(
    dailyExpenses: List<DailyReport>,
    modifier: Modifier = Modifier
) {
    val chartData = remember(dailyExpenses) {
        dailyExpenses.map {
            LineData(
                label = it.date,
                value = it.total.toFloat() / 1000f
            )
        }
    }

    var selectedDay by remember {
        mutableStateOf<DailyReport?>(null)
    }

    if (chartData.isEmpty()) {
        Text(text = "اطلاعاتی وجود ندارد")
        return
    }

    LineChart(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp),
        data = { chartData },
        color = ChartyColor.Solid(DarkBlue),
        onPointClick = { point ->
            selectedDay = dailyExpenses.firstOrNull {
                it.date == point.label
            }
        }
    )

    selectedDay?.let { day ->
        ModalBottomSheet(
            onDismissRequest = { selectedDay = null }
        ) {
            DailyReportCard(
                day = day
            )
        }
    }
}


@Composable
fun DailyReportCard(
    day: DailyReport,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(35)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.LG),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.date,
                fontSize = 18.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
            Spacer(Modifier.height(AppSpacing.LG))
            Text(
                text = "مجموع هزینه ها",
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = NumberFormatter.formatPrice(day.total),
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(AppSpacing.LG))
            Text(
                text = "تعداد تراکنش",
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = day.count.toString(),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}


/*
LazyColumn(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    item {
        if (chartData.isNotEmpty()) {
            LineChart(
                modifier = Modifier.size(350.dp),
                data = { chartData },
                onPointClick = { point ->
                    Log.d(
                        "chart", "${point.label} : ${point.value}"
                    )
                },
                color = ChartyColor.Solid(DarkBlue)
            )
            Spacer(Modifier.height(20.dp))
        } else {
            Text("داده‌ای برای نمایش وجود ندارد")
        }
    }
    item {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DailyReportCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                title = "کل هزینه ها",
                value = uiState.totalExpense.toString()
            )
            DailyReportCard(
                modifier = Modifier.weight(1f),
                title = "میانگین",
                value = uiState.averageExpense.toString()
            )
        }
    }
    item {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DailyReportCard(
                modifier = Modifier.weight(1f),
                title = "بیشترین هزینه",
                value = uiState.maxExpense.toString()
            )
            DailyReportCard(
                modifier = Modifier.weight(1f),
                title = "تعداد روزها",
                value = uiState.invoiceCount.toString()
            )
        }
    }
}
*/
