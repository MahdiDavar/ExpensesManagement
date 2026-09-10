package com.mahdidavar.expensesmanagement.widget.presentation

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetStateMapper
import com.mahdidavar.expensesmanagement.widget.domain.ExpenseWidgetState


class ExpenseWidget : GlanceAppWidget(){
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent{
            val preferences = currentState<Preferences>()
            val state = ExpenseWidgetStateMapper().map(preferences)
            ExpenseWidgetContent(
                state = state
            )
        }
    }
}
