package com.mahdidavar.expensesmanagement.utills

interface BudgetPeriodProvider {
    fun current(): BudgetPeriod
}