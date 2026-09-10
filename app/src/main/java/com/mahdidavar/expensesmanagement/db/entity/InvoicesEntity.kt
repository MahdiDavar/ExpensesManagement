package com.mahdidavar.expensesmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahdidavar.expensesmanagement.db.MyDataBase

@Entity(tableName = MyDataBase.TABLE_NAME)
data class InvoicesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val price: Long,
    val category: String,
    val subCategory: String,
    val des: String?,
    val time: String,
    val persianDate: String,
    val date: Int
)