package com.mahdidavar.expensesmanagement.db.utills

import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity

data class ShowListUiState(
    val invoices: List<InvoicesEntity> = emptyList(),
    val filteredInvoices: List<InvoicesEntity> = emptyList(),
    val searchQuery: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val totalAmount: Long = 0L,
    val selectedForDelete: InvoicesEntity? = null ,
    val selectedForDetail: InvoicesEntity? = null ,
    val showDetail: Boolean = false,
    val showDelete : Boolean = false
)