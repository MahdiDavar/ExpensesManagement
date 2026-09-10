package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.DataStoreManager
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    val themeFlow = dataStoreManager.themeFlow
    val languageFlow = dataStoreManager.languageFlow

    suspend fun saveLanguage(language: String) {
        dataStoreManager.saveLanguage(language)
    }

    suspend fun saveTheme(isDark: Boolean) {
        dataStoreManager.saveTheme(isDark)
    }
}