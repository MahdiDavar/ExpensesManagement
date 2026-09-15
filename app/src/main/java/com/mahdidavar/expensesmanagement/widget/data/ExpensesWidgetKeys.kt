package com.mahdidavar.expensesmanagement.widget.data

import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object ExpensesWidgetKeys {
    val TOTAL_BUDGET = longPreferencesKey("total_budget")
    val SPENT_AMOUNT = longPreferencesKey("spent_amount")
    val REMAINING_AMOUNT = longPreferencesKey("remaining_amount")
    val PROGRESS = floatPreferencesKey("progress")
    val LAST_TITLE = stringPreferencesKey("last_title")
    val LAST_PRICE = longPreferencesKey("last_price")
    val WARNING_TYPE = stringPreferencesKey("warning_type")
}