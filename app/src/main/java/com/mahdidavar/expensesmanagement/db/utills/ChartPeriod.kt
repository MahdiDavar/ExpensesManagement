package com.mahdidavar.expensesmanagement.db.utills

enum class ChartPeriod {
    LAST_7_DAYS ,
    LAST_30_DAYS ,
    THIS_MONTH ,
    THIS_YEAR ,
    ALL
}

fun ChartPeriod.toPersianName(): String{
    return when(this){
        ChartPeriod.LAST_7_DAYS -> "۷ روز اخیر"
        ChartPeriod.LAST_30_DAYS -> "۳۰ روز اخیر"
        ChartPeriod.THIS_MONTH -> "این ماه"
        ChartPeriod.THIS_YEAR -> "امسال"
        ChartPeriod.ALL -> "همه"
    }
}