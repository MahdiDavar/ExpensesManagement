package com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetUpdater
import javax.inject.Inject

class SaveBudgetUseCase @Inject constructor(
    private val repository: BudgetRepository,
    private val widgetUpdater: ExpenseWidgetUpdater
) {
    suspend operator fun invoke(budget: BudgetEntity) {
        repository.saveBudget(budget)
        widgetUpdater.refresh()
    }
}