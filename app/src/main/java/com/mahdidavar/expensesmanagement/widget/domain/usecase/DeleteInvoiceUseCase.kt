package com.mahdidavar.expensesmanagement.widget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetUpdater
import javax.inject.Inject

class DeleteInvoiceUseCase @Inject constructor(
    private val repository: InvoicesRepositoryImpl,
    private val widgetUpdater: ExpenseWidgetUpdater
) {
    suspend operator fun invoke(invoices: InvoicesEntity) {
        repository.deleteInvoice(invoices)
        widgetUpdater.refresh()
    }
}