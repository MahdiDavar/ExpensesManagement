package com.mahdidavar.expensesmanagement.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.presentation.budget.domain.analyzer.BudgetAnalyzer
import com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase.DeleteBudgetUseCase
import com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase.GetBudgetUseCase
import com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase.SaveBudgetUseCase
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetUiState
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetUiStateMapper
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import com.mahdidavar.expensesmanagement.utills.PersianDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val saveBudgetUseCase: SaveBudgetUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val deleteBudgetUseCase: DeleteBudgetUseCase,
    private val invoicesRepository: InvoiceRepository,
    private val analyzer: BudgetAnalyzer,
    private val mapper: BudgetUiStateMapper,
    private val persianDate: PersianDate
) : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetUiState())
    val uiState = _uiState.asStateFlow()


    init {
        observeCurrentMonthBudget()
    }

    fun saveBudget(amount: Long) {
        val (year, month) = createDate()
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                saveBudgetUseCase(
                    BudgetEntity(
                        amount = amount,
                        year = year,
                        month = month
                    )
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "خطا در ذخیره بودجه"
                    )
                }
            }
        }
    }

    fun deleteBudget() {
        val budget = _uiState.value.budget ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                deleteBudgetUseCase(budget)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "خطا در حذف بودجه"
                    )
                }
            }
        }
    }

    private fun observeCurrentMonthBudget() {
        val (year, month) = createDate()
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                combine(
                    getBudgetUseCase(year, month),
                    invoicesRepository.getInvoiceOfMonth(year, month)
                ) { budget, invoices ->
                    budget to invoices
                }.collectLatest { (budget, invoices) ->
                    Log.d(
                        "BUDGET_DEBUG",
                        "budget=$budget, invoices=${invoices.size}, total=${invoices.sumOf { it.price }}"
                    )
                    if (budget == null) {
                        _uiState.update {
                            BudgetUiState(
                                isLoading = false
                            )
                        }
                        return@collectLatest
                    }
                    val analysis = analyzer.analyze(
                        budget = budget,
                        invoices = invoices
                    )
                    _uiState.update {
                        it.copy(
                            budget = budget,
                            progressUiModel = mapper.toProgressUi(analysis),
                            warning = mapper.toWarningUi(analysis),
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "خطا در دریافت اطلاعات بودجه"
                    )
                }
            }
        }
    }

    private fun createDate() =
        persianDate.year to persianDate.month

}