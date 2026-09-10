package com.mahdidavar.expensesmanagement.utills

import javax.inject.Inject

class PersianBudgetPeriodProvider @Inject constructor(
    private val persianDate: PersianDate
) : BudgetPeriodProvider{
    override fun current() =
        BudgetPeriod(
            persianDate.year ,
            persianDate.month
        )

}