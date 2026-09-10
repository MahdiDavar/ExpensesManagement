package com.mahdidavar.expensesmanagement.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.entity.UserEntity
import com.mahdidavar.expensesmanagement.repository.LoginRepository
import com.mahdidavar.expensesmanagement.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _loginStatus = MutableStateFlow<Boolean>(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus
    private val _message = MutableSharedFlow<String>(replay = 1)
    val message: SharedFlow<String> = _message

    fun login(userName: String) {
        val enterUser = UserEntity(username = userName)
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.addUser(enterUser)
            _loginStatus.value = result
            _message.emit(
                if (result) "ذخیره سازی موفق" else "ذخیره سازی ناموفق"
            )
        }
    }

    fun saveData() {
        loginRepository.saveLoginStatus()
    }
}