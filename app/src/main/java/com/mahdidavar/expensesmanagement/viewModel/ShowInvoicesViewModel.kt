package com.mahdidavar.expensesmanagement.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.db.utills.ShowListUiState
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import com.mahdidavar.expensesmanagement.widget.domain.usecase.DeleteInvoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowInvoicesViewModel @Inject constructor(
    private val repository: InvoicesRepositoryImpl,
    private val deleteInvoiceUseCase: DeleteInvoiceUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ShowListUiState())
    val uiState = _uiState.asStateFlow()


    init {
        invoicesObserver()
    }

    private fun invoicesObserver() {
        viewModelScope.launch {
            repository.getAllInvoices().collectLatest { list ->
                updateList(list)
            }
        }
    }

    private fun updateList(list: List<InvoicesEntity>) {
        val current = _uiState.value

        val filter = list
            .filterBySearch(current.searchQuery)
            .filterByDate(current.startDate, current.endDate)

        _uiState.value = current.copy(
            invoices = list,
            filteredInvoices = filter,
            totalAmount = list.sumOf { it.price }
        )
    }

    fun onSearchChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        updateList(_uiState.value.invoices)
    }

    fun onDateRangeSelected(start: String, end: String) {
        _uiState.update { it.copy(startDate = start, endDate = end) }
        val startNum = start.replace("/", "").toInt()
        val endNum = end.replace("/", "").toInt()
        filterByDateRange(start = startNum, end = endNum)
        updateList(_uiState.value.invoices)
    }

    fun deleteRequest(invoice: InvoicesEntity) {
        _uiState.update {
            it.copy(
                selectedForDelete = invoice,
                showDelete = true
            )
        }
    }

    fun detailRequest(invoice: InvoicesEntity) {
        _uiState.update {
            it.copy(
                selectedForDetail = invoice,
                showDetail = true
            )
        }
    }

    fun deleteConfirmed() {
        val invoice = _uiState.value.selectedForDelete ?: return
        viewModelScope.launch {
            deleteInvoiceUseCase(invoice)
            _uiState.update {
                it.copy(
                    selectedForDelete = null,
                    showDelete = false
                )
            }
        }
    }

    fun dismissDeleteDialog() {
        _uiState.update {
            it.copy(
                selectedForDelete = null,
                showDelete = false
            )
        }
    }

    fun dismissDetailButton() {
        _uiState.update {
            it.copy(
                selectedForDetail = null,
                showDetail = false
            )
        }
    }

    fun filterByDateRange(start: Int, end: Int) {
        viewModelScope.launch {
            repository.getInvoicesByRange(start, end)
                .collect { list ->
                    _uiState.update {
                        it.copy(
                            filteredInvoices = list,
                            totalAmount = list.sumOf { invoice -> invoice.price }
                        )
                    }
                }
        }
    }

}

private fun List<InvoicesEntity>.filterBySearch(query: String): List<InvoicesEntity> {
    if (query.isBlank()) return this
    return filter {
        it.category.contains(query, true) ||
                it.subCategory.contains(query, true)
    }
}

private fun List<InvoicesEntity>.filterByDate(
    start: String,
    end: String
): List<InvoicesEntity> {

    if (start.isBlank() || end.isBlank()) return this

    val startNum = start.replace("/", "").toInt()
    val endNum = end.replace("/", "").toInt()

    return filter {
        it.date in startNum..endNum
    }
}
