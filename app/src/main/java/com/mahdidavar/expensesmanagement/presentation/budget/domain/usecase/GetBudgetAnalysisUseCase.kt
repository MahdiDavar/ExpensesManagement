package com.mahdidavar.expensesmanagement.presentation.budget.domain.usecase

import com.mahdidavar.expensesmanagement.presentation.budget.domain.analyzer.BudgetAnalyzer
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetAnalysisResult
import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetBudgetAnalysisUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository ,
    private val invoiceRepository: InvoiceRepository ,
    private val analyzer: BudgetAnalyzer
) {
    operator fun invoke (
        year :Int ,
        month : Int
    ): Flow<BudgetAnalysisResult?> {
        return combine(
            budgetRepository.getBudget(year , month),
            invoiceRepository.getInvoiceOfMonth(year,month)
        ){ budget , invoices ->
            budget?.let {
                analyzer.analyze(
                    budget= it ,
                    invoices = invoices
                )
            }
        }
    }
}