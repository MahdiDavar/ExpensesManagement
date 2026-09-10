package com.mahdidavar.expensesmanagement.widget.domain

import com.mahdidavar.expensesmanagement.presentation.budget.model.BudgetWarningType

data class ExpenseWidgetState(
    val totalBudget : Long =0 ,
    val spentAmount : Long = 0 ,
    val remainingAmount : Long = 0,
    val progress : Float = 0f ,
    val lastInvoiceTitle : String ="" ,
  val   lastInvoicePrice : Long = 0 ,
    val warningType : BudgetWarningType = BudgetWarningType.NORMAL
)