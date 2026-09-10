package com.mahdidavar.expensesmanagement.viewModel

import androidx.lifecycle.ViewModel
import com.mahdidavar.expensesmanagement.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SplashRepository
) : ViewModel() {

    suspend fun userIsLoggedIn(): Boolean {
        return repository.getLoginData()
    }
}
