package com.mahdidavar.expensesmanagement.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mahdidavar.expensesmanagement.db.MyDataBase
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpensesDB
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {

    @Insert
    suspend fun insertInvoice(invoice: InvoicesEntity): Long

    @Query("SELECT * FROM ${MyDataBase.TABLE_NAME} ORDER BY date DESC , time DESC ")
    fun getAllInvoices(): Flow<List<InvoicesEntity>>

    @Query("SELECT * FROM ${MyDataBase.TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun getInvoiceById(id: Int): InvoicesEntity?

    @Query("DELETE FROM ${MyDataBase.TABLE_NAME}")
    suspend fun clearData()

    @Query("DELETE FROM ${MyDataBase.TABLE_NAME} WHERE id = :id")
    suspend fun deleteInvoiceById(id: Int)

    @Delete
    suspend fun deleteInvoice(invoice: InvoicesEntity)

    @Query("SELECT * FROM ${MyDataBase.TABLE_NAME} WHERE date BETWEEN :start AND :end")
    fun getInvoicesByDateRange(start: Int, end: Int): Flow<List<InvoicesEntity>>

    @Query("SELECT persianDate AS date, SUM(price) AS total , COUNT(*) AS count FROM ${MyDataBase.TABLE_NAME} GROUP BY date , persianDate ORDER BY date ASC")
    fun getDailyExpenses(): Flow<List<DailyReport>>

    @Query("SELECT persianDate AS date, SUM(price) AS total , COUNT(*) AS count FROM ${MyDataBase.TABLE_NAME} WHERE date BETWEEN :startDate AND :endDate GROUP BY date , persianDate ORDER BY date ASC")
    fun getDailyExpensesByRange(startDate: Int, endDate: Int): Flow<List<DailyReport>>

    @Query("SELECT category, SUM (price) AS total , COUNT (*) AS count FROM ${MyDataBase.TABLE_NAME} GROUP BY category ORDER BY total DESC")
    fun getCategoryExpenses(): Flow<List<CategoryExpensesDB>>

    @Query("SELECT category, SUM (price) AS total , COUNT (*) AS count FROM ${MyDataBase.TABLE_NAME} WHERE date BETWEEN :startDate AND :endDate GROUP BY category ORDER BY total DESC")
    fun getCategoryExpensesByRange(startDate: Int, endDate: Int): Flow<List<CategoryExpensesDB>>

    @Query("SELECT (date/10000) AS year , ((date/100)%100) AS month , SUM (price) AS total , COUNT (*) AS count FROM ${MyDataBase.TABLE_NAME} WHERE date BETWEEN :startDate AND :endDate GROUP BY  (date / 10000), ((date / 100) % 100) ORDER BY year , month ")
    fun getMonthlyExpensesByRange(startDate: Int, endDate: Int): Flow<List<MonthlyExpenses>>

    /* @Query("SELECT * FROM invoices ORDER BY date DESC LIMIT 1")
     fun getLastInvoice(): Flow<InvoicesEntity?>*/

    @Query("SELECT * FROM invoices WHERE (date / 10000) = :year AND ((date/100)%100) =:month ORDER BY date DESC , time DESC ")
    fun getInvoiceOfMonth(
        year: Int, month: Int
    ): Flow<List<InvoicesEntity>>

    @Query("SELECT COUNT(*) FROM ${MyDataBase.TABLE_NAME}")
    fun getInvoiceCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM ${MyDataBase.TABLE_NAME} WHERE date BETWEEN :startDate AND :endDate")
    fun getInvoiceCountByRange(startDate: Int, endDate: Int): Flow<Int>
}