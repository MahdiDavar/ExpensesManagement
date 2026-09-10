package com.mahdidavar.expensesmanagement.viewModel

import androidx.lifecycle.ViewModel
import com.mahdidavar.expensesmanagement.utills.PersianDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val date: PersianDate
) : ViewModel(){
    fun getDay() = "${date.year}/${date.month}/${date.day}"

    fun getHour() = "${date.hour} : ${date.min}"

    fun getDayName() = date.strWeekDay
}