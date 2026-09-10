package com.mahdidavar.expensesmanagement.db

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    private val dataStore : DataStore<Preferences>
) {
    val themeFlow : Flow<Boolean> =
        dataStore.data.map{
            it[DataStoreKey.isDarkTheme] ?: false
        }
    val languageFlow : Flow<String> =
        dataStore.data.map{
            it[DataStoreKey.language] ?: DataStoreKey.PERSIAN
        }

    suspend fun saveLanguage(language : String){
        dataStore.edit {
            it[DataStoreKey.language] = language
        }
    }

    suspend fun saveTheme(isDark : Boolean){
        dataStore.edit {
            it[DataStoreKey.isDarkTheme] = isDark
        }
    }

    fun saveData(worker: (mutablePreferences: MutablePreferences) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                worker(it)
            }
        }
    }

    suspend fun <T> getData(key: Preferences.Key<T>): T? {
        return dataStore.data.first()[key]
    }
}

/*
@Singleton
class DataStoreInput @Inject constructor(
) {

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    fun saveData(worker: (mutablePreferences: MutablePreferences) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                worker(it)
            }
        }
    }

    suspend fun <T> getData(key: Preferences.Key<T>): T? {
        return dataStore.data.first()[key]
    }
}*/