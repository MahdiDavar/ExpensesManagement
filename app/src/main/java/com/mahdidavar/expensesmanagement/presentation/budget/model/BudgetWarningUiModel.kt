package com.mahdidavar.expensesmanagement.presentation.budget.model

data class BudgetWarningUiModel(
    val title : String ,
    val message : String ,
    val type : BudgetWarningType
){
    companion object {
        val Empty = BudgetWarningUiModel (
            title = "" ,
            message = "" ,
            type = BudgetWarningType.NORMAL
        )
    }
}