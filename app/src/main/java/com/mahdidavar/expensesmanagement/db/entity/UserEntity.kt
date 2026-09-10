package com.mahdidavar.expensesmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahdidavar.expensesmanagement.db.MyDataBase

@Entity(tableName = MyDataBase.TABLE_USER)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val email: String? = null,
    val phone: String? = null,
    val avatar: String? = null,
    val birthday: String? = null,
    val gender: Gender = Gender.NotChoose
)

enum class Gender {
    NotChoose,
    Male,
    Female
}