package com.mahdidavar.expensesmanagement.db.entity

import androidx.room.Entity
import com.mahdidavar.expensesmanagement.db.MyDataBase

@Entity(
    tableName = MyDataBase.TABLE_BUDGETS,
    primaryKeys = ["year", "month"]
)
data class BudgetEntity(
    val year: Int,
    val month: Int,
    val amount: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
