package com.mahdidavar.expensesmanagement.widget.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.ui.theme.DarkBlue
import com.mahdidavar.expensesmanagement.ui.theme.Gray
import com.mahdidavar.expensesmanagement.ui.theme.NewWhite
import com.mahdidavar.expensesmanagement.utills.NumberFormatter.formatPrice
import com.mahdidavar.expensesmanagement.widget.domain.ExpenseWidgetState

@SuppressLint("RestrictedApi")
@Composable
fun ExpenseWidgetContent(
    state: ExpenseWidgetState
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(AppSpacing.MD)
            .background(ColorProvider(NewWhite)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "'چرتکه'",
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(GlanceModifier.height(AppSpacing.SM))
        LinearProgressIndicator(
            progress = state.progress.coerceIn(0f, 1f),
            modifier = GlanceModifier.fillMaxWidth(),
            backgroundColor = ColorProvider(Gray),
            color = ColorProvider(DarkBlue)
        )
        Spacer(GlanceModifier.height(AppSpacing.SM))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "مصرف شده")
            Spacer(GlanceModifier.width(AppSpacing.SM))
            Text(text = "${(state.progress.coerceIn(0f, 1f) * 100).toInt()}%")
        }
        Spacer(GlanceModifier.height(AppSpacing.SM))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Column(
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "بودجه")
                Spacer(GlanceModifier.height(AppSpacing.SM))
                Text(text = formatPrice(state.totalBudget))
            }
            Spacer(GlanceModifier.width(AppSpacing.MD))
            Column(
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "خرج شده")
                Spacer(GlanceModifier.height(AppSpacing.SM))
                Text(text = formatPrice(state.spentAmount))
            }
        }
        /* Spacer(GlanceModifier.height(AppSpacing.SM))
         Text(text = "آخرین هزینه")
         Spacer(GlanceModifier.height(AppSpacing.SM))
         Text(text = state.lastInvoiceTitle)
         Text(text = formatPrice(state.lastInvoicePrice))*/
    }
}
