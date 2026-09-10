package com.mahdidavar.expensesmanagement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahdidavar.expensesmanagement.core.design.AppSpacing
import com.mahdidavar.expensesmanagement.core.ui.ErrorState
import com.mahdidavar.expensesmanagement.core.ui.LoadingState
import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import com.mahdidavar.expensesmanagement.presentation.statistics.StatisticsUiState
import com.mahdidavar.expensesmanagement.viewModel.ChartsViewModel

@Composable
fun StatisticScreen(
    viewModel: ChartsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val filter by viewModel.filter.collectAsStateWithLifecycle()

    StatisticContent(
        uiState = uiState,
        period = filter,
        onPeriodChange = viewModel::changeFilter
    )
}


@Composable
private fun StatisticContent(
    uiState: StatisticsUiState,
    period: ChartPeriod,
    onPeriodChange: (ChartPeriod) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> {
            LoadingState()
        }

        uiState.error != null -> {
            ErrorState()
        }

        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.MD),
                contentPadding = PaddingValues(AppSpacing.MD)
            ) {
                item {
                    uiState.dashboard?.let { dashboard ->
                        DashboardSection(model = dashboard)
                    }
                }
                item {
                    DailyChartSection(
                        dailyExpenses = uiState.dailyExpenses,
                        period = period,
                        onPeriodChange = onPeriodChange
                    )
                }
                item {
                    CategoryChartSection(
                        categoryExpenses = uiState.categoryExpenses
                    )
                }
                item {
                    MonthlyExpensesSection(
                        monthlyExpenses = uiState.monthlyExpenses
                    )
                }
                item {
                    InsightSection(
                        insights = uiState.analysis?.insights.orEmpty()
                    )
                }
            }
        }
    }
}