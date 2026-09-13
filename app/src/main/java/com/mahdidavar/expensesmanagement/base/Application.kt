package com.mahdidavar.expensesmanagement.base

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import com.mahdidavar.expensesmanagement.widget.WidgetRefreshObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Application : Application(){

    @Inject
    lateinit var widgetRefreshObserver: WidgetRefreshObserver
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            widgetRefreshObserver.observe()
        }
    }
}