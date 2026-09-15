package com.mahdidavar.expensesmanagement.widget.data

import androidx.datastore.preferences.core.Preferences
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetAnalysisResult
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningType
import com.mahdidavar.expensesmanagement.widget.domain.ExpenseWidgetState
import javax.inject.Inject

class ExpenseWidgetStateMapper @Inject constructor() {
    fun map(
        analysis: BudgetAnalysisResult,
        lastInvoice: InvoicesEntity?
    ): ExpenseWidgetState {
        return ExpenseWidgetState(
            totalBudget = analysis.progress.totalBudget,
            spentAmount = analysis.progress.spentAmount,
            remainingAmount = analysis.progress.remainingAmount,
            progress = analysis.progress.progress,
            lastInvoiceTitle = lastInvoice?.subCategory ?: "",
            lastInvoicePrice = lastInvoice?.price ?: 0L,
            warningType = analysis.warning.type
        )
    }

    fun map(
        preferences: Preferences
    ): ExpenseWidgetState {
        return ExpenseWidgetState(
            totalBudget = preferences[ExpensesWidgetKeys.TOTAL_BUDGET] ?: 0L,
            spentAmount = preferences[ExpensesWidgetKeys.SPENT_AMOUNT] ?: 0L,
            remainingAmount = preferences[ExpensesWidgetKeys.REMAINING_AMOUNT] ?: 0L,
            progress = preferences[ExpensesWidgetKeys.PROGRESS] ?: 0f,
            lastInvoiceTitle = preferences[ExpensesWidgetKeys.LAST_TITLE] ?: "",
            lastInvoicePrice = preferences[ExpensesWidgetKeys.LAST_PRICE] ?: 0L,
            warningType = preferences[ExpensesWidgetKeys.WARNING_TYPE]
                ?.let { BudgetWarningType.valueOf(it) }
                ?: BudgetWarningType.NORMAL
        )
    }
}
