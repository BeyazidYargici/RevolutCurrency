package com.beyazidyargici.revolutcurrency.util

import android.content.Context
import com.beyazidyargici.revolutcurrency.BuildConfig
import com.beyazidyargici.revolutcurrency.RevolutCurrencyApp
import com.facebook.stetho.Stetho
import timber.log.Timber
import javax.inject.Inject

/**
 *  This class using to start required environments at [RevolutCurrencyApp] started.
 *  Created by beyazid on 09/02/2020.
 */
class AppUtils @Inject constructor(val application: RevolutCurrencyApp, val context: Context, val initializer: Stetho.Initializer) {

    fun setupPlugins() {
        Stetho.initialize(initializer)
        if (BuildConfig.DEBUG) Timber.plant(MyDebugTree())
    }

    /**
     * I am using Timber for logging.
     * I customized to improving readability
     * LogCat shows line, method name and class name
     */
    inner class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? = String.format(
            "[Line:%s] [Method:%s] [Class:%s]",
            element.lineNumber,
            element.methodName,
            super.createStackElementTag(element)
        )
    }
}