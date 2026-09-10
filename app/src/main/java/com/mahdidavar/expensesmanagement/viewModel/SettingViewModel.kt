package com.mahdidavar.expensesmanagement.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.DataStoreKey
import com.mahdidavar.expensesmanagement.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {
    val themeStatus = settingRepository.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val languageState = settingRepository.languageFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DataStoreKey.PERSIAN
    )

    fun changeTheme(isDark: Boolean) {
        viewModelScope.launch {
            settingRepository.saveTheme(isDark)
        }
    }

    fun chaneLanguage(language: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            settingRepository.saveLanguage(language)
            onComplete()
        }
    }

}




