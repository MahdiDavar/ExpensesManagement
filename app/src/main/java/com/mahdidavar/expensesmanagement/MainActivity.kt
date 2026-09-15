package com.mahdidavar.expensesmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahdidavar.expensesmanagement.base.LocaleHelper
import com.mahdidavar.expensesmanagement.navigation.SetUpNavigation
import com.mahdidavar.expensesmanagement.ui.theme.ExpensesManagementTheme
import com.mahdidavar.expensesmanagement.viewModel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SettingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fromWidget = intent.getBooleanExtra("FROM_WIDGET" , false)

        enableEdgeToEdge()
        setContent {
            val themeState by viewModel.themeStatus.collectAsStateWithLifecycle()
            val language by viewModel.languageState.collectAsStateWithLifecycle()
            LaunchedEffect(language) {
                LocaleHelper.setLocale(
                    this@MainActivity,
                    language
                )
            }
            ExpensesManagementTheme(darkTheme = themeState) {
                //   containerColor = Color(0xFF0B1220)
                SetUpNavigation(
                    fromWidget = fromWidget
                )
            }
        }
    }
}

