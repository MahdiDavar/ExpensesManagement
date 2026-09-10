package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.dao.BudgetsDao
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepositoryImpl @Inject constructor(
    private val dao: BudgetsDao
) : BudgetRepository {

    override suspend fun saveBudget(budget: BudgetEntity) =
        dao.saveBudget(budget)

    override fun getBudget(
        year: Int,
        month: Int
    ): Flow<BudgetEntity?> =
        dao.getBudget(year , month)

    override fun getAllBudgets(): Flow<List<BudgetEntity>> =
        dao.getAllBudgets()

    override suspend fun deleteBudget(budget: BudgetEntity) =
        dao.deleteBudget(budget)
}