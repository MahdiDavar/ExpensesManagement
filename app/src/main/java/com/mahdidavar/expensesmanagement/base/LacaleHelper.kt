package com.mahdidavar.expensesmanagement.base

import android.content.Context
import java.util.Locale

object LocaleHelper {
    @Suppress("DEPRECATION")
    fun setLocale(
        context: Context , language : String
    ) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        context.resources.updateConfiguration(
            config ,
            context.resources.displayMetrics
        )
    }
}