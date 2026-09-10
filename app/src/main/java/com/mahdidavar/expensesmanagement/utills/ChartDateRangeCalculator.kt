package com.mahdidavar.expensesmanagement.utills

import com.mahdidavar.expensesmanagement.db.utills.ChartPeriod
import javax.inject.Inject

data class ChartDateRange(
    val startDate: Int,
    val endDate: Int
)

class ChartDateRangeCalculator @Inject constructor(
    private val persianDate: PersianDate
) {

    fun calculate(period: ChartPeriod): ChartDateRange {
        val currentYear = persianDate.year
        val currentMonth = persianDate.month
        val currentDay = persianDate.day

        val endDate = PersianDateUtils.toDateNumber(
            currentYear,
            currentMonth,
            currentDay
        )
        return when (period) {
            ChartPeriod.LAST_7_DAYS -> {
                ChartDateRange(
                    startDate = PersianDateUtils.subtrackDays(
                        currentYear,
                        currentMonth,
                        currentDay,
                        6
                    ),
                    endDate = endDate
                )
            }

            ChartPeriod.LAST_30_DAYS -> {
                ChartDateRange(
                    startDate = PersianDateUtils.subtrackDays(
                        currentYear,
                        currentMonth,
                        currentDay,
                        29
                    ),
                    endDate = endDate
                )
            }

            ChartPeriod.THIS_MONTH -> {
                ChartDateRange(
                    startDate = PersianDateUtils.toDateNumber(
                        currentYear,
                        currentMonth,
                        1
                    ),
                    endDate = endDate
                )
            }

            ChartPeriod.THIS_YEAR -> {
                ChartDateRange(
                    startDate = PersianDateUtils.toDateNumber(
                        currentYear,
                        1,
                        1
                    ),
                    endDate = endDate
                )
            }

            ChartPeriod.ALL -> {
                ChartDateRange(
                    startDate = 0,
                    endDate = endDate
                )
            }
        }
    }

}