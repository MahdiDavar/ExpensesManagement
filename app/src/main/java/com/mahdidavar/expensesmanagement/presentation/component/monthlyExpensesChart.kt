package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.data.BarData
import com.himanshoe.charty.color.ChartyColor
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.NewYellowDark
import com.mahdidavar.expensesmanagement.utills.NumberFormatter.formatPrice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyExpensesChart(
    data: List<MonthlyExpenses>,
    modifier: Modifier = Modifier
) {
    val monthData = remember(data) {
        data.map { item ->
            BarData(
                label = "${getMonthName(item.month)} ${item.year}",
                value = item.total.toFloat(),
                color = ChartyColor.Solid(DarkBlue)
            )
        }
    }

    var selectedMonth by remember {
        mutableStateOf<MonthlyExpenses?>(null)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (monthData.isEmpty()) {
            Text("اطلاعاتی وجود ندارد")
            return@Column
        }

        BarChart(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(280.dp),
            data = { monthData },
            color = ChartyColor.Solid(NewYellowDark),
            onBarClick = { bar ->
                val index = monthData.indexOfFirst {
                    it.label == bar.label
                }
                if (index != -1) {
                    selectedMonth = data[index]
                }
                /*    selectedMonth = data.firstOrNull() {
                        "${getMonthName(it.month)} ${it.year}" == bar.label
                    }*/
            }
        )
    }
    selectedMonth?.let { month ->
        ModalBottomSheet(
            onDismissRequest = {
                selectedMonth = null
            }
        ) {
            MonthDetailSheet(
                month = month
            )
        }
    }
}


fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "فروردین"
        2 -> "اردیبهشت"
        3 -> "خرداد"
        4 -> "تیر"
        5 -> "مرداد"
        6 -> "شهریور"
        7 -> "مهر"
        8 -> "آبان"
        9 -> "آذر"
        10 -> "دی"
        11 -> "بهمن"
        12 -> "اسفند"
        else -> ""
    }
}

@Composable
fun MonthDetailSheet(
    month: MonthlyExpenses
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppSpacing.LG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${getMonthName(month.month)} ${month.year}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Spacer(Modifier.height(AppSpacing.LG))
        Text(text = "مجموع هزینه", color = Color.Black)
        Text(
            text = formatPrice(month.total),
            color = Color.Black
        )
        Spacer(Modifier.height(AppSpacing.LG))
        Text(
            text = "تعداد تراکنش",
            color = Color.Black
        )
        Text(
            text = month.count.toString(),
            style = MaterialTheme.typography.titleMedium ,
            color = Color.Black
        )
    }
}
