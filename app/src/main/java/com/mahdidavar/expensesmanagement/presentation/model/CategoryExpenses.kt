package com.mahdidavar.expensesmanagement.presentation.model

data class CategoryExpenses(
    val category : ExpensesCategory ,
    val total : Long ,
    val count : Int
)



data class CategoryExpensesDB(
    val category: String ,
    val total: Long ,
    val count : Int
)