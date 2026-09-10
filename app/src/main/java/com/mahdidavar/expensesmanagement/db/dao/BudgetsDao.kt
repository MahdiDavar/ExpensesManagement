package com.mahdidavar.expensesmanagement.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mahdidavar.expensesmanagement.db.MyDataBase
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetsDao {

    @Upsert
    suspend fun saveBudget(budget: BudgetEntity)

    @Query("SELECT * FROM ${MyDataBase.TABLE_BUDGETS} WHERE year =:year AND month = :month")
    fun getBudget(year: Int , month: Int): Flow<BudgetEntity?>

    @Query("SELECT * FROM ${MyDataBase.TABLE_BUDGETS} ORDER BY year DESC , month DESC")
    fun getAllBudgets(): Flow<List<BudgetEntity>>

    @Delete
    suspend fun deleteBudget(budget: BudgetEntity)
}