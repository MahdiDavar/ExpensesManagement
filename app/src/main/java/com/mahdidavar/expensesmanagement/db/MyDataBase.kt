package com.mahdidavar.expensesmanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahdidavar.expensesmanagement.db.dao.BudgetsDao
import com.mahdidavar.expensesmanagement.db.dao.InvoiceDao
import com.mahdidavar.expensesmanagement.db.dao.UserDao
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.db.entity.UserEntity

@Database(
    entities = [InvoicesEntity::class, UserEntity::class, BudgetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "expensesManagement"
        const val TABLE_NAME = "invoices"
        const val TABLE_USER = "user"
        const val TABLE_BUDGETS = "budgets"
    }

    abstract fun invoiceDao(): InvoiceDao
    abstract fun userDao(): UserDao
    abstract fun budgetDao(): BudgetsDao
}