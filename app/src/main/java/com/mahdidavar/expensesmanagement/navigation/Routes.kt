package com.mahdidavar.expensesmanagement.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object HomePage: Routes

    @Serializable
    data object SettingPage: Routes

    @Serializable
    data object ChartPage: Routes

    @Serializable
    data object ShowInvoicesPage: Routes

    @Serializable
    data object AddInvoicesPage: Routes

    @Serializable
    data object SplashPage: Routes

    @Serializable
    data object LoginPage: Routes

    @Serializable
    data object EditProfilePage: Routes
}