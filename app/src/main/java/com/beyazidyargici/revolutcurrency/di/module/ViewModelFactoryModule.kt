package com.beyazidyargici.revolutcurrency.di.module

import androidx.lifecycle.ViewModelProvider
import com.beyazidyargici.revolutcurrency.ui.currencyrates.CurrencyRatesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindCurrencyRatesViewModelFactory(currencyRatesViewModelFactory: CurrencyRatesViewModelFactory): ViewModelProvider.Factory


}
