package com.mahdidavar.expensesmanagement.db

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKey {
    val isUserLoggedIn = booleanPreferencesKey("user_is_logged_in")

    val isDarkTheme = booleanPreferencesKey("is_dark_theme")

    val language = stringPreferencesKey("app_language")
    const val PERSIAN = "fa"
    const val ENGLISH = "en"
}


