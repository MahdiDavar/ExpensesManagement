package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.config.LabelConfig
import com.himanshoe.charty.pie.config.PieChartConfig
import com.himanshoe.charty.pie.config.PieChartStyle
import com.himanshoe.charty.pie.data.PieData
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.utills.NumberFormatter.formatPrice
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChartView(
    data: List<CategoryExpenses>,
    modifier: Modifier = Modifier
) {
    val total = remember(data) {
        data.sumOf { it.total }
    }
    val pieData = remember(data) {
        data.map {
            PieData(
                label = it.category.title,
                value = it.total.toFloat(),
                color = it.category.color
            )
        }
    }
    var selectedCategory by remember {
        mutableStateOf<CategoryExpenses?>(null)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pieData.isEmpty()) {
            Text("داده ای وجود ندارد")
            return
        }
        PieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            data = { pieData },
            centerContent = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "کل هزینه"
                    )
                    Text(
                        text = formatPrice(total)
                    )
                }
            },
            onSliceClick = { pie, _ ->
                selectedCategory = data.firstOrNull {
                    it.category.title == pie.label
                }
            },
            config = PieChartConfig(
                style = PieChartStyle.DONUT,
                sliceSpacingDegrees = 3f,
                shouldShowCenterText = true,
                labelConfig = LabelConfig(
                    shouldShowLabels = true,
                    shouldShowPercentage = true
                )
            )
        )
        Spacer(
            modifier = Modifier.height(AppSpacing.MD)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(
                items = data,
                key = { it.category.name }
            ) {
                CategoryItemDetail(
                    item = it,
                    total = total
                )
            }
        }
    }
    selectedCategory?.let { category ->
        ModalBottomSheet(
            onDismissRequest = {
                selectedCategory = null
            }
        ) {
            CategoryDetailSheet(
                category = category
            )
        }
    }
}


@Composable
fun CategoryItemDetail(
    item: CategoryExpenses,
    total: Long
) {
    val percent = calculatePercent(value = item.total, total = total)

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = AppSpacing.SM),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(color = item.category.color, shape = CircleShape)
        )
        Spacer(Modifier.width(AppSpacing.MD))
        Icon(
            imageVector = item.category.icon,
            contentDescription = null,
            tint = item.category.color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(AppSpacing.SM))
        Text(
            text = item.category.title,
            Modifier.weight(1f),
            color = Color.Black,
            maxLines = 1
        )
        Spacer(Modifier.width(AppSpacing.SM))
        Text(text = formatPrice(item.total), color = Color.Black)
        Spacer(Modifier.width(AppSpacing.SM))
        Text(text = "$percent%", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}


fun calculatePercent(
    value: Long,
    total: Long
): Int {
    if (total <= 0L) return 0
    return ((value.toDouble() / total) * 100).roundToInt()
}


@Composable
fun CategoryDetailSheet(
    category: CategoryExpenses
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = category.category.title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Spacer(Modifier.height(20.dp))
        Text(text = "مجموع هزینه", color = Color.Black)
        Text(
            text = formatPrice(category.total),
            color = Color.Black
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "تعداد تراکنش",
            color = Color.Black
        )
        Text(
            text = category.count.toString(),
            color = Color.Black
        )
    }
}
