package com.mahdidavar.expensesmanagement.di.module

import android.content.Context
import androidx.room.Room
import com.mahdidavar.expensesmanagement.db.MyDataBase
import com.mahdidavar.expensesmanagement.db.dao.BudgetsDao
import com.mahdidavar.expensesmanagement.db.dao.InvoiceDao
import com.mahdidavar.expensesmanagement.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesMyDataBase(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = MyDataBase::class.java,
            name = MyDataBase.DB_NAME
        ).build()
    }

    @Provides
    fun providesInvoiceDao(myDataBase: MyDataBase): InvoiceDao {
        return myDataBase.invoiceDao()
    }

    @Provides
    fun providesUserDao(myDataBase: MyDataBase): UserDao {
        return myDataBase.userDao()
    }

    @Provides
    fun providesBudgetDao(myDataBase: MyDataBase): BudgetsDao {
        return myDataBase.budgetDao()
    }
}