package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import kotlinx.coroutines.flow.Flow

interface InvoiceRepository {

    fun getAllInvoices(): Flow<List<InvoicesEntity>>

    fun getInvoiceOfMonth(
        year: Int,
        month: Int
    ): Flow<List<InvoicesEntity>>

    fun getInvoiceCount(): Flow<Int>

    fun getInvoiceCountByRange(startDate : Int , endDate : Int): Flow<Int>

    fun getDailyExpenses(): Flow<List<DailyReport>>

    fun getCategoryExpenses(): Flow<List<CategoryExpenses>>

    fun getCategoryExpensesByRange(startDate: Int, endDate: Int): Flow<List<CategoryExpenses>>

    fun getMonthlyExpensesByRange(startDate: Int, endDate: Int): Flow<List<MonthlyExpenses>>

    fun getDailyExpensesByDate(startDate: Int, endDate: Int): Flow<List<DailyReport>>
}
