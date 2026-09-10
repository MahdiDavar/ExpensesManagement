package com.mahdidavar.expensesmanagement.widget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetUpdater
import javax.inject.Inject

class DeleteBudgetUseCase @Inject constructor(
    private val repository: BudgetRepository,
    private val widgetUpdater: ExpenseWidgetUpdater
) {
    suspend operator fun invoke(budget: BudgetEntity) {
        repository.deleteBudget(budget)
        widgetUpdater.refresh()
    }
}