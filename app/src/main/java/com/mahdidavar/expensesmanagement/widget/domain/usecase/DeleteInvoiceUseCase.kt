package com.mahdidavar.expensesmanagement.widget.domain.usecase

import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import javax.inject.Inject

class DeleteInvoiceUseCase @Inject constructor(
    private val repository: InvoicesRepositoryImpl
) {
    suspend operator fun invoke(invoices: InvoicesEntity) {
        repository.deleteInvoice(invoices)
    }
}