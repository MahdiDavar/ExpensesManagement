package com.mahdidavar.expensesmanagement.widget

import android.util.Log
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import com.mahdidavar.expensesmanagement.utills.PersianDate
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetUpdater
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRefreshObserver @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val invoiceRepository: InvoiceRepository,
    private val expenseWidgetUpdater: ExpenseWidgetUpdater,
    private val persianDate: PersianDate
) {
    suspend fun observe() {
        val year = persianDate.year
        val month = persianDate.month

        combine(
            budgetRepository.getBudget(year, month),
            invoiceRepository.getInvoiceOfMonth(year, month)
        ) { budget, invoice ->
            budget to invoice
        }.collectLatest {
            Log.d(
                "WIDGET_OBSERVER",
                "Data changed -> refreshing widget"
            )
            expenseWidgetUpdater.refresh()
        }
    }
}