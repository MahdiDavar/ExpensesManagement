package com.mahdidavar.expensesmanagement.presentation.budget.domain.analyzer

import androidx.compose.ui.graphics.Color
import com.mahdidavar.expensesmanagement.db.entity.BudgetEntity
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetAnalysisResult
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetProgressUiModel
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningType
import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningUiModel
import javax.inject.Inject

class BudgetAnalyzer @Inject constructor() {
    companion object {
        private const val WARNING_LIMIT = 0.8f
        private const val DANGER_LIMIT = 1f
    }

    fun analyze(
        budget: BudgetEntity,
        invoices: List<InvoicesEntity>
    ): BudgetAnalysisResult {

        val spent = invoices.sumOf { it.price }

        val remain = budget.amount - spent

        val progress =
            if (budget.amount <= 0L)
                0f
            else
                spent.toFloat() / budget.amount.toFloat()

        val color = when {
            progress >= DANGER_LIMIT -> Color.Red
            progress >= WARNING_LIMIT -> Color.Yellow
            else -> Color.Green
        }
        val progressUi =
            BudgetProgressUiModel(
                totalBudget = budget.amount,
                spentAmount = spent,
                remainingAmount = remain,
                progress = progress,
                progressColor = color
            )
        val warningUi =
            when {
                progress >= DANGER_LIMIT -> {
                    BudgetWarningUiModel(
                        title = "بودجه تمام شده",
                        message = "شما از بودجه این ماه عبور کرده اید",
                        type = BudgetWarningType.DANGER
                    )
                }

                progress >= WARNING_LIMIT -> {
                    BudgetWarningUiModel(
                        title = "نزدیک به سقف بودجه",
                        message = "بیش از 80% بودجه مصرف شده است",
                        type = BudgetWarningType.WARNING
                    )
                }

                else -> {
                    BudgetWarningUiModel(
                        title = "وضعیت مناسب",
                        message = "مصرف بودجه در محدوده مناسب قرار دارد",
                        type = BudgetWarningType.NORMAL
                    )
                }
            }

        return BudgetAnalysisResult(
            progress = progressUi,
            warning = warningUi
        )
    }
}


/*
val status = when {
            progress >= DANGER_LIMIT -> BudgetWarningType.DANGER
            progress >= WARNING_LIMIT -> BudgetWarningType.WARNING
            else -> BudgetWarningType.NORMAL
        }
*/

/* return BudgetAnalysis(
     totalBudget = budget.amount ,
     spentAmount = spent ,
     remainingAmount = remain ,
     progress = progress ,
     status = status
 )*/