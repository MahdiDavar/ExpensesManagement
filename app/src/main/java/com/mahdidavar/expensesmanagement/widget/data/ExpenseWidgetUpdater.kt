package com.mahdidavar.expensesmanagement.widget.data

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mahdidavar.expensesmanagement.presentation.budget.domain.analyzer.BudgetAnalyzer
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import com.mahdidavar.expensesmanagement.utills.PersianDate
import com.mahdidavar.expensesmanagement.widget.domain.ExpenseWidgetState
import com.mahdidavar.expensesmanagement.widget.presentation.ExpenseWidget
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class ExpenseWidgetUpdater @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val invoiceRepository: InvoiceRepository,
    private val budgetAnalyzer: BudgetAnalyzer,
    private val persianDate: PersianDate,
    private val mapper: ExpenseWidgetStateMapper,
    @ApplicationContext private val context: Context
) {
    suspend fun loadWidgetState(): ExpenseWidgetState {
        val budget = budgetRepository.getBudget(
            year = persianDate.year,
            month = persianDate.month
        ).firstOrNull()

        if (budget == null) {
            return ExpenseWidgetState()
        }

        val invoices = invoiceRepository.getInvoiceOfMonth(
            persianDate.year,
            persianDate.month
        ).first()

        val result = budgetAnalyzer.analyze(
            budget = budget,
            invoices = invoices
        )

        val lastInvoice = invoices.maxByOrNull { it.date }

        return mapper.map(
            analysis = result,
            lastInvoice = lastInvoice
        )
    }

    suspend fun refresh() {

        runCatching {

            val manager = GlanceAppWidgetManager(context = context)

            val glanceId = manager.getGlanceIds(ExpenseWidget::class.java)
            val state = loadWidgetState()
            val widget = ExpenseWidget()

            glanceId.forEach { glanceId ->
                updateAppWidgetState(
                    context = context,
                    definition = PreferencesGlanceStateDefinition,
                    glanceId = glanceId
                ) { preferences ->
                    preferences.toMutablePreferences().apply {
                        this[ExpensesWidgetKeys.TOTAL_BUDGET] = state.totalBudget
                        this[ExpensesWidgetKeys.SPENT_AMOUNT] = state.spentAmount
                        this[ExpensesWidgetKeys.REMAINING_AMOUNT] = state.remainingAmount
                        this[ExpensesWidgetKeys.PROGRESS] = state.progress
                        this[ExpensesWidgetKeys.LAST_TITLE] = state.lastInvoiceTitle
                        this[ExpensesWidgetKeys.LAST_PRICE] = state.lastInvoicePrice
                    }
                }
                widget.update(
                    context,
                    glanceId
                )
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}