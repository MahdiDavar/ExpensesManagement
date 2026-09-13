package com.mahdidavar.expensesmanagement.widget.domain.usecase

import android.util.Log
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import javax.inject.Inject

class SaveInvoiceUseCase @Inject constructor(
    private val repository: InvoicesRepositoryImpl
) {
    suspend operator fun invoke(invoices: InvoicesEntity) {
        Log.d("WidgetDebug", "SaveInvoiceUseCase called")
        repository.addInvoice(
            price = invoices.price,
            category = invoices.category,
            subCategory = invoices.subCategory,
            desc = invoices.des,
            dateFa = invoices.time,
            dateNum = invoices.date
        )
    }
}