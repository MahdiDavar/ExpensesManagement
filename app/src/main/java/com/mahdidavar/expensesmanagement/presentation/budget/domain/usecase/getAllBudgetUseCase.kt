package com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBudgetUseCase @Inject constructor(
    private val repository: BudgetRepository
) {
    operator fun invoke(): Flow<List<BudgetEntity>> {
        return repository.getAllBudgets()
    }
}