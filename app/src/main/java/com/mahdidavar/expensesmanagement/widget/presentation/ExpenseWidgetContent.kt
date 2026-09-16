package com.mahdidavar.expensesmanagement.widget.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.appwidget.action.actionRunCallback
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
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningType
import com.mahdidavar.expensesmanagement.ui.theme.Gray
import com.mahdidavar.expensesmanagement.ui.theme.Green
import com.mahdidavar.expensesmanagement.ui.theme.NewWhite
import com.mahdidavar.expensesmanagement.ui.theme.Red
import com.mahdidavar.expensesmanagement.ui.theme.Yellow
import com.mahdidavar.expensesmanagement.utills.NumberFormatter.formatPrice
import com.mahdidavar.expensesmanagement.widget.action.OpenAppFromWidgetAction
import com.mahdidavar.expensesmanagement.widget.action.OpenWidgetAction
import com.mahdidavar.expensesmanagement.widget.domain.ExpenseWidgetState

@SuppressLint("RestrictedApi")
@Composable
fun ExpenseWidgetContent(
    state: ExpenseWidgetState
) {
    val progressColor = when (state.warningType) {
        BudgetWarningType.NORMAL -> Green
        BudgetWarningType.DANGER -> Red
        BudgetWarningType.WARNING -> Yellow
    }

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(AppSpacing.SM)
            .background(ColorProvider(NewWhite))
            .clickable(
                actionRunCallback<OpenWidgetAction>()
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.totalBudget <= 0L) {
            Text(
                text = "بودجه ای برای این ماه تعیین نشده",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(GlanceModifier.height(AppSpacing.MD))
            Text(
                text = "برای تنظیم بودجه وارد برنامه شوید",
                style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
            )
            return@Column
        }
        /*  Text(
              text = "چرتکه",
              style = TextStyle(
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold
              )
          )*/
        Spacer(GlanceModifier.height(AppSpacing.XS))
        LinearProgressIndicator(
            progress = state.progress.coerceIn(0f, 1f),
            modifier = GlanceModifier.fillMaxWidth(),
            backgroundColor = ColorProvider(Gray),
            color = ColorProvider(progressColor)
        )
        Spacer(GlanceModifier.height(AppSpacing.XS))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = when (state.warningType) {
                    BudgetWarningType.NORMAL -> "وضعیت مناسب"
                    BudgetWarningType.WARNING -> "نزدیک به سقف بودجه"
                    BudgetWarningType.DANGER -> "بودجه تمام شده"
                },
                style = TextStyle(
                    color = ColorProvider(progressColor),
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(GlanceModifier.width(AppSpacing.SM))
            Text(
                text = "${(state.progress.coerceIn(0f, 1f) * 100).toInt()}%",
                style = TextStyle(
                    color = ColorProvider(progressColor),
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(GlanceModifier.height(AppSpacing.XS))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Column(
                modifier = GlanceModifier.defaultWeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "باقی مانده", style = TextStyle(fontSize = 12.sp))
                Spacer(GlanceModifier.height(AppSpacing.SM))
                Text(text = formatPrice(state.remainingAmount), style = TextStyle(fontSize = 12.sp))
            }
            Column(
                modifier = GlanceModifier.defaultWeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "خرج شده", style = TextStyle(fontSize = 12.sp))
                Spacer(GlanceModifier.height(AppSpacing.SM))
                Text(text = formatPrice(state.spentAmount), style = TextStyle(fontSize = 12.sp))
            }
            Column(
                modifier = GlanceModifier.defaultWeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "بودجه", style = TextStyle(fontSize = 12.sp))
                Spacer(GlanceModifier.height(AppSpacing.SM))
                Text(text = formatPrice(state.totalBudget), style = TextStyle(fontSize = 12.sp))
            }
        }
        if (state.lastInvoiceTitle.isNotBlank()) {
            Spacer(GlanceModifier.height(AppSpacing.XS))
            Text(text = "آخرین هزینه", style = TextStyle(fontSize = 14.sp))
            Spacer(GlanceModifier.height(AppSpacing.XS))
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .clickable(
                        actionRunCallback<OpenAppFromWidgetAction>()
                    )
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formatPrice(state.lastInvoicePrice),
                    style = TextStyle(fontSize = 12.sp)
                )
                Spacer(modifier = GlanceModifier.width(AppSpacing.XL))
                Text(
                    text = state.lastInvoiceTitle,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    } // End of main column
}
