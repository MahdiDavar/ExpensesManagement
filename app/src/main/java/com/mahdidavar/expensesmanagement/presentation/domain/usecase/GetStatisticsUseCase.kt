package com.mahdidavar.expensesmanagement.presentation.domain.usecase

import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import com.mahdidavar.expensesmanagement.presentation.domain.analysis.StatisticsAnalyzer
import com.mahdidavar.expensesmanagement.presentation.statistics.StatisticsUiState
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import com.mahdidavar.expensesmanagement.utills.ChartDateRangeCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val repository: InvoiceRepository,
    private val analyzer: StatisticsAnalyzer,
    private val dateRangeCalculator: ChartDateRangeCalculator
) {
    operator fun invoke(
        period: ChartPeriod
    ): Flow<StatisticsUiState> {

        val range = dateRangeCalculator.calculate(period)

        return combine(
            repository.getDailyExpensesByDate(
                startDate = range.startDate,
                endDate = range.endDate
            ),
            repository.getCategoryExpensesByRange(
                startDate = range.startDate ,
                endDate = range.endDate
            ),
            repository.getMonthlyExpensesByRange(
                startDate = range.startDate ,
                endDate = range.endDate
            ),
            repository.getInvoiceCountByRange(
                startDate = range.startDate ,
                endDate = range.endDate
            )
        ) { daily, category, monthly, invoiceCount ->

            val analysis = analyzer.analyze(
                dailyExpenses = daily,
                categoryExpenses = category,
                monthlyExpenses = monthly
            )

            StatisticsUiState(
                dailyExpenses = daily,
                categoryExpenses = category,
                monthlyExpenses = monthly,
                analysis = analysis,
                totalExpense = analysis.totalExpenses,
                averageExpense = analysis.averageExpenses,
                maxExpense = analysis.maxExpenseDay?.total ?: 0L,
                invoiceCount = invoiceCount,
                isLoading = false
            )
        }
    }
}