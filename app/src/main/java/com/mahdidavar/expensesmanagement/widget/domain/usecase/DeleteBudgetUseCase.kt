package com.mahdidavar.expensesmanagement.widget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import javax.inject.Inject

class DeleteBudgetUseCase @Inject constructor(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(budget: BudgetEntity) {
        repository.deleteBudget(budget)
    }
}