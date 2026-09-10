package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.model.StatisticsInsight

@Composable
fun InsightItem(
    insight: StatisticsInsight
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.MD),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = insight.icon,
                fontSize = 28.sp,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.width(AppSpacing.MD))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = insight.title,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(AppSpacing.XS))
                Text(
                    text = insight.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}