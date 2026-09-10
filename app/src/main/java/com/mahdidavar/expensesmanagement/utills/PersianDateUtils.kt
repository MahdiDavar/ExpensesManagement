package com.mahdidavar.expensesmanagement.utills

object PersianDateUtils {
    fun toDateNumber(
        year: Int,
        month: Int,
        day: Int
    ): Int {
        return year * 10000 + month * 100 + day
    }

    fun addDays(
        year: Int,
        month: Int,
        day: Int,
        days: Int
    ): Int {
        var y = year
        var m = month
        var d = day

        repeat(days) {
            d++

            if (d > daysInMonth(y, m)) {
                d = 1
                m++
                if (m > 12) {
                    m = 1
                    y++
                }
            }
        }
        return toDateNumber(y, m, d)
    }

    fun subtrackDays(
        year: Int,
        month: Int,
        day: Int,
        days: Int
    ): Int {
        var y = year
        var m = month
        var d = day

        repeat(days) {
            d--
            if (d < 1) {
                m--
                if (m < 1) {
                    m = 12
                    y--
                }
                d = daysInMonth(y, m)
            }
        }
        return toDateNumber(y, m, d)
    }

    fun daysInMonth(
        year: Int,
        month: Int
    ): Int {
        return when (month) {
            in 1..6 -> 31
            in 7..11 -> 30
            12 -> {
                if (isLeapYear(year)) 30 else 29
            }
            else -> throw IllegalArgumentException("Invalid Persian Month :$month ")
        }
    }

    fun isLeapYear(year: Int): Boolean {
        val reminder = year % 33
        return reminder in setOf(
            1, 5, 9, 13, 17, 22, 26, 30
        )
    }

}