package com.mahdidavar.expensesmanagement.widget.presentation

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class ExpenseWidgetReceiver : GlanceAppWidgetReceiver(){
    private val widget = ExpenseWidget()
    override val glanceAppWidget: GlanceAppWidget
        get() = widget
}
