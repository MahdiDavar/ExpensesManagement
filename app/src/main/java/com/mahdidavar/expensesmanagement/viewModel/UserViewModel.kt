package com.mahdidavar.expensesmanagement.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdidavar.expensesmanagement.db.entity.Gender
import com.mahdidavar.expensesmanagement.db.entity.UserEntity
import com.mahdidavar.expensesmanagement.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var username by mutableStateOf( "")
        private set

    var email by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var birthday by mutableStateOf("")
        private set

    var gender by mutableStateOf(Gender.NotChoose)
        private set

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user
    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message
    private val _success = MutableSharedFlow<Boolean>()
    val success = _success.asSharedFlow()

    fun onUsernameChange(value: String) {
        username = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }
    }

    fun onBirthdayChange(value: String) {
        birthday = value
    }

    fun onGenderChange(value: Gender) {
        gender = value
    }

    fun submitUpdate() {
        viewModelScope.launch {
            val result = userRepository.updateUserById(
                username = username,
                email = email,
                phone = phone,
                birthday = birthday,
                gender = gender
            )
            if (result) {
                _success.emit(true)
            }
        }
    }

    fun loadUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = userRepository.getUserById(userId)
        }
    }

    fun getUser(): Flow<UserEntity?> = userRepository.getUser()

    fun addUser(enterUser: UserEntity) {
        viewModelScope.launch {
            val result = userRepository.addUser(enterUser)
            _message.emit(
                if (result) "ذخیره سازی نا موفق" else "ذخیره سازی موفق"
            )
        }
    }

    /* fun updateById (userEntity: UserEntity) {
         viewModelScope.launch {
             val result = userRepository.updateUserById(
                 username = userEntity.username ,
                 phone = userEntity.phone?:"" ,
                 email = userEntity.email?:"" ,
                 birthday = userEntity.birthday?:""
             )
             _message.emit(
                 if (result) "به روز رسانی موفق" else "به روز رسانی ناموفق"
             )
         }
     }*/

    fun updateUserAvatar(avatar: String, userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUserAvatar(avatar, userId)
            loadUser(userId)
        }
    }
}