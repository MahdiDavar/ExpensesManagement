package com.mahdidavar.expensesmanagement.presentation.model

data class MonthlyExpenses(
    val year: Int,
    val month: Int,
    val total: Long ,
   val count: Int
)