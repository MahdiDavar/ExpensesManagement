    package com.mahdidavar.expensesmanagement.repository

    import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
    import kotlinx.coroutines.flow.Flow

    interface BudgetRepository {

        suspend fun saveBudget(
            budget: BudgetEntity
        )

        fun getBudget(
            year: Int,
            month: Int
        ): Flow<BudgetEntity?>

        fun getAllBudgets(): Flow<List<BudgetEntity>>

        suspend fun deleteBudget(budget: BudgetEntity)
    }