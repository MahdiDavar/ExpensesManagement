package com.mahdidavar.expensesmanagement.repository

import android.util.Log
import com.mahdidavar.expensesmanagement.db.dao.InvoiceDao
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.presentation.model.CategoryExpenses
import com.mahdidavar.expensesmanagement.presentation.model.DailyReport
import com.mahdidavar.expensesmanagement.presentation.model.ExpensesCategory
import com.mahdidavar.expensesmanagement.presentation.model.MonthlyExpenses
import com.mahdidavar.expensesmanagement.utills.PersianDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class InvoicesRepositoryImpl @Inject constructor(
    private val invoiceDao: InvoiceDao,
    private val date: PersianDate
) : InvoiceRepository {
    fun getHour() =
        "${date.hour} : ${date.min}"

    fun currentDate() =
        "${date.year}/${date.month}/${date.day}"

    fun currentDateNum(): Int {
        return date.year * 10000 +
                date.month * 100 +
                date.day
    }


    override fun getAllInvoices() =
        invoiceDao.getAllInvoices()

    suspend fun getInvoiceById(id: Int) =
        invoiceDao.getInvoiceById(id)

    suspend fun addInvoice(
        price: Long,
        category: String,
        subCategory: String,
        desc: String?,
        dateFa: String?,
        dateNum: Int?
    ) =
        try {
            val enterInvoice = InvoicesEntity(
                price = price,
                category = category,
                subCategory = subCategory,
                des = desc,
                time = getHour(),
                persianDate = if (dateFa.isNullOrEmpty()) currentDate() else dateFa,
                date = dateNum ?: currentDateNum()
            )
            Log.d(
                "INVOICE_DEBUG",
                "dateNum=$dateNum, currentDateNum=${currentDateNum()}, dateFa=$dateFa"
            )
            val result = invoiceDao.insertInvoice(enterInvoice)
            Log.d(
                "INVOICE_DEBUG",
                "invoiceDate=${enterInvoice.date}, persianDate=${enterInvoice.persianDate}"
            )
            result > 0
        } catch (_: Exception) {
            false
        }

    suspend fun clearInvoices() =
        try {
            invoiceDao.clearData()
            true
        } catch (_: Exception) {
            false
        }

    suspend fun deleteInvoiceById(id: Int) =
        try {
            invoiceDao.deleteInvoiceById(id)
            true
        } catch (_: Exception) {
            false
        }

    suspend fun deleteInvoice(invoice: InvoicesEntity) =
        try {
            invoiceDao.deleteInvoice(invoice)
            true
        } catch (_: Exception) {
            false
        }

    fun getInvoicesByRange(start: Int, end: Int) =
        invoiceDao.getInvoicesByDateRange(start = start, end = end)

    override fun getDailyExpenses(): Flow<List<DailyReport>> =
        invoiceDao.getDailyExpenses()

    override fun getCategoryExpenses(): Flow<List<CategoryExpenses>> {
        return invoiceDao
            .getCategoryExpenses()
            .map { list ->
                list.map {
                    CategoryExpenses(
                        category = ExpensesCategory.fromTitle(it.category),
                        total = it.total,
                        count = it.count
                    )
                }
            }
    }

    override fun getCategoryExpensesByRange(
        startDate: Int,
        endDate: Int
    ): Flow<List<CategoryExpenses>> {
        return invoiceDao
            .getCategoryExpensesByRange(startDate, endDate)
            .map { list ->
                list.map {
                    CategoryExpenses(
                        category = ExpensesCategory.fromTitle(it.category),
                        total = it.total,
                        count = it.count
                    )
                }
            }
    }

    override fun getMonthlyExpensesByRange(
        startDate: Int,
        endDate: Int
    ): Flow<List<MonthlyExpenses>> {
        return invoiceDao.getMonthlyExpensesByRange(
            startDate,
            endDate
        )
    }

    override fun getInvoiceOfMonth(
        year: Int,
        month: Int
    ): Flow<List<InvoicesEntity>> {
        return invoiceDao.getInvoiceOfMonth(
            year,
            month
        )
    }

    override fun getInvoiceCount() =
        invoiceDao.getInvoiceCount()

    override fun getInvoiceCountByRange(
        startDate: Int,
        endDate: Int
    ): Flow<Int> {
        return invoiceDao.getInvoiceCountByRange(
            startDate,
            endDate
        )
    }

    override fun getDailyExpensesByDate(startDate: Int, endDate: Int): Flow<List<DailyReport>> {
        return invoiceDao.getDailyExpensesByRange(
            startDate = startDate,
            endDate = endDate
        )
    }


}
