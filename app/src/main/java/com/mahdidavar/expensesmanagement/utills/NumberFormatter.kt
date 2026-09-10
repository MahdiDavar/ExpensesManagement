package com.mahdidavar.expensesmanagement.utills

import android.icu.text.DecimalFormat

object NumberFormatter {
    fun formatPrice(price : Long): String{
        val formatter = DecimalFormat("#,###")
        return formatter.format(price)
    }
}