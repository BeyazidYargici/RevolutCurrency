package com.beyazidyargici.revolutcurrency.di.module

import android.app.Application
import android.content.Context
import com.beyazidyargici.revolutcurrency.ui.MainActivity
import com.beyazidyargici.revolutcurrency.RevolutCurrencyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    var mApplication = RevolutCurrencyApp()
    var activity = MainActivity()

    fun AppModule(application: RevolutCurrencyApp) {
        mApplication = application
    }

    @Provides
    @Singleton
    fun providesApplication(): Application = mApplication

    @Provides
    @Singleton
    fun providesContext(): Context = mApplication


}