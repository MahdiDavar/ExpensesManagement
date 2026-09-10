package com.mahdidavar.expensesmanagement.presentation.budget.model

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class BudgetUiStateMapper @Inject constructor() {
  /*  fun toProgressUi(
        analysis: BudgetAnalysisResult
    ): BudgetProgressUiModel {
        return BudgetProgressUiModel(
            totalBudget = analysis.progress.totalBudget,
            spentAmount = analysis.progress.spentAmount,
            remainingAmount = analysis.progress.remainingAmount,
            progress = analysis.progress.progress,
            progressColor = when (analysis.warning.type) {
                BudgetWarningType.WARNING -> Color(0xFFFFC107)
                BudgetWarningType.DANGER -> Color(0xFFF44336)
                BudgetWarningType.NORMAL -> Color(0xFF4CAF50)
            }
        )
    }

    fun toWarningUi(
        analysis: BudgetAnalysisResult
    ): BudgetWarningUiModel {
        return when (analysis.warning.type) {
            BudgetWarningType.NORMAL ->
                BudgetWarningUiModel(
                    title = "وضعیت مناسب",
                    message = "مصرف بودجه در محدوده مناسب قرار دارد.",
                    type = BudgetWarningType.NORMAL
                )

            BudgetWarningType.WARNING ->
                BudgetWarningUiModel(
                    title = "نزدیک سقف بودجه",
                    message = "بیش از ۸۰٪ بودجه مصرف شده است.",
                    type = BudgetWarningType.WARNING
                )

            BudgetWarningType.DANGER ->
                BudgetWarningUiModel(
                    title = "بودجه تمام شده",
                    message = "شما از بودجه این ماه عبور کرده‌اید.",
                    type = BudgetWarningType.DANGER
                )
        }
    }
   */
  fun toProgressUi(
      analysis: BudgetAnalysisResult
  ): BudgetProgressUiModel {
      return analysis.progress
  }

    fun toWarningUi(
        analysis: BudgetAnalysisResult
    ): BudgetWarningUiModel {
        return analysis.warning
    }
}