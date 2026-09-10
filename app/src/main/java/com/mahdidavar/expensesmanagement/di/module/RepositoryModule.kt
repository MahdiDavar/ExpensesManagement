package com.mahdidavar.expensesmanagement.di.module

import com.mahdidavar.expensesmanagement.repository.BudgetRepository
import com.mahdidavar.expensesmanagement.repository.BudgetRepositoryImpl
import com.mahdidavar.expensesmanagement.repository.InvoiceRepository
import com.mahdidavar.expensesmanagement.repository.InvoicesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBudgetRepository(
        impl: BudgetRepositoryImpl
    ) : BudgetRepository

    @Binds
    abstract fun bindInvoiceRepository(
        impl : InvoicesRepositoryImpl
    ): InvoiceRepository
}