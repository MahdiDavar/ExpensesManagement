package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.DataStoreKey
import com.mahdidavar.expensesmanagement.db.DataStoreManager
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun getLoginData(): Boolean {
        return dataStoreManager.getData(DataStoreKey.isUserLoggedIn) ?: false
    }
}