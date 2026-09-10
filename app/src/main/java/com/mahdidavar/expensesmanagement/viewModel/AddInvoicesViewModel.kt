package com.mahdidavar.expensesmanagement.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.entity.InvoicesEntity
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import com.mahdidavar.expensesmanagement.widget.data.ExpenseWidgetUpdater
import com.mahdidavar.expensesmanagement.widget.domain.usecase.SaveInvoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddInvoicesViewModel @Inject constructor(
    private val invoiceRepository: InvoicesRepositoryImpl,
    private val saveInvoiceUseCase: SaveInvoiceUseCase ,
    private val widgetUpdater: ExpenseWidgetUpdater
) : ViewModel() {
    var price by mutableStateOf("")
        private set

    var desc by mutableStateOf("")
        private set

    var category by mutableStateOf("")
        private set

    var subCategory by mutableStateOf("")
        private set

    var dateFarsi by mutableStateOf(invoiceRepository.currentDate())
        private set

    var dateNum by mutableIntStateOf(invoiceRepository.currentDateNum())
        private set

    private val _success = MutableSharedFlow<Boolean>()
    val success = _success.asSharedFlow()

    fun onPriceChange(value: String) {
        price = value.filter { it.isDigit() }
    }

    fun onDescChange(value: String) {
        desc = value
    }

    fun onCategorySelected(cat: String, sub: String) {
        category = cat
        subCategory = sub
    }

    fun onDateChange(newDate: String, newDateNum: Int) {
        dateFarsi = newDate
        dateNum = newDateNum
    }

    fun submit() {
        val finalPrice = price.toLongOrNull() ?: return

        viewModelScope.launch {
            saveInvoiceUseCase(
                InvoicesEntity(
                    price = finalPrice,
                    category = category,
                    subCategory = subCategory,
                    des = desc,
                    time = "",
                    persianDate = dateFarsi,
                    date = dateNum
                )
            )

            widgetUpdater.refresh()
            clearForm()
            _success.emit(true)
        }
    }

    private fun clearForm() {
        price = ""
        category = ""
        subCategory = ""
        desc = ""
        //  date = ""
        // dateNum = 0
    }

}