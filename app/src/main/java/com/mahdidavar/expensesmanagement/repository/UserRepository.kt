package com.mahdidavar.expensesmanagement.repository

import com.mahdidavar.expensesmanagement.db.dao.UserDao
import com.mahdidavar.expensesmanagement.db.entity.Gender
import com.mahdidavar.expensesmanagement.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun addUser(user: UserEntity) =
        try {
            val result = userDao.insertUser(user)
            result > 0
        } catch (_: Exception) {
            false
        }

    suspend fun updateUserById(
        username: String,
        phone: String,
        email: String,
        birthday: String,
        gender: Gender
    ) =
        try {
            userDao.updateById(username, phone, email, birthday, gender)
            true
        } catch (_: Exception) {
            false
        }

    fun getUser(): Flow<UserEntity?> = userDao.getUser()

    suspend fun getUserById(userId: Int) = userDao.getUserById(userId)

    suspend fun updateUserAvatar(avatar: String, userId: Int) =
        userDao.updateUserAvatar(avatar, userId)

}