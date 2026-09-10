package com.mahdidavar.expensesmanagement.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import com.mahdidavar.expensesmanagement.presentation.domain.usecase.GetStatisticsUseCase
import com.mahdidavar.expensesmanagement.presentation.model.DashboardUiModel
import com.mahdidavar.expensesmanagement.presentation.statistics.StatisticsUiState
import com.mahdidavar.expensesmanagement.utills.NumberFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChartsViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase
) : ViewModel() {

    private val _filter = MutableStateFlow(
        ChartPeriod.LAST_30_DAYS
    )

    val filter = _filter.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = filter.flatMapLatest { period ->
        getStatisticsUseCase(period)
    }
        .map { state ->

            val analysis = state.analysis

            state.copy(
                dashboard = analysis?.let {
                    DashboardUiModel(
                        totalExpenses =
                            NumberFormatter.formatPrice(it.totalExpenses),

                        averageExpense =
                            NumberFormatter.formatPrice(it.averageExpenses),

                        invoiceCount =
                            state.invoiceCount.toString(),

                        topCategory =
                            it.topCategory?.category?.title
                    )
                }
            )
        }
        .catch { exception ->
            emit(
                StatisticsUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "خطا در دریافت اطلاعات"
                )
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            StatisticsUiState(isLoading = true)
        )

    fun changeFilter(period: ChartPeriod) {
        _filter.value = period
    }

}