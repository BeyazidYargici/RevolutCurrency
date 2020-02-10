package com.beyazidyargici.revolutcurrency.di.component

import com.beyazidyargici.revolutcurrency.RevolutCurrencyApp
import com.beyazidyargici.revolutcurrency.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  Created by beyazid on 10/02/2020.
 *  Separate the modules and give to AppComponent
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        FragmentModule::class,
        ActivityModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RevolutCurrencyApp): Builder

        @BindsInstance
        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: RevolutCurrencyApp)

}