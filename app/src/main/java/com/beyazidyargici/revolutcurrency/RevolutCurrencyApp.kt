package com.beyazidyargici.revolutcurrency

import android.app.Application
import androidx.multidex.MultiDex
import com.beyazidyargici.revolutcurrency.di.component.DaggerAppComponent
import com.beyazidyargici.revolutcurrency.di.module.ActivityModule
import com.beyazidyargici.revolutcurrency.di.module.NetworkModule
import com.beyazidyargici.revolutcurrency.util.AppUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class RevolutCurrencyApp : Application() , HasAndroidInjector {

    @Inject
    lateinit var appUtils: AppUtils

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()
            .inject(this)
        appUtils.setupPlugins()
        Timber.d("timber")
    }


}