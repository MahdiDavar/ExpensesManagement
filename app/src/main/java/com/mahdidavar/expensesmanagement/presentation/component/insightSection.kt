package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.core.ui.ChartCard
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight

@Composable
fun InsightSection(
    insights: List<StatisticsInsight>,
    modifier: Modifier = Modifier
) {
    if (insights.isEmpty()) return

    ChartCard(
        title = "تحلیل هوشمند",
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppSpacing.MD)
        ) {
            insights.forEach {
                InsightItem(it)
            }
        }
    }
}