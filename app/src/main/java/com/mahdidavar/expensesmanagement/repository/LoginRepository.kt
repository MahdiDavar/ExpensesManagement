package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.DataStoreManager
import com.mahdidavar.expensesmanagement.db.DataStoreKey
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    fun saveLoginStatus() {
        dataStoreManager.saveData {
            it[DataStoreKey.isUserLoggedIn] = true
        }
    }
}