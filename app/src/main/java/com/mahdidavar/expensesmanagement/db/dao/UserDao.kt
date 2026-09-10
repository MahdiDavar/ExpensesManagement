package com.mahdidavar.expensesmanagement.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mahdidavar.expensesmanagement.db.MyDataBase
import com.mahdidavar.expensesmanagement.db.entity.Gender
import com.mahdidavar.expensesmanagement.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM ${MyDataBase.TABLE_USER}")
    fun getUser(): Flow<UserEntity?>

    @Query("SELECT * FROM ${MyDataBase.TABLE_USER} WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity

    @Query("UPDATE ${MyDataBase.TABLE_USER} SET avatar = :avatar WHERE id = :userId ")
    suspend fun updateUserAvatar(avatar: String, userId: Int)

    @Query("UPDATE ${MyDataBase.TABLE_USER} SET username = :userName , phone = :phone , email = :email , birthday = :birthday , gender = :gender ")
    suspend fun updateById(
        userName: String,
        phone: String,
        email: String,
        birthday: String,
        gender: Gender
    )
}