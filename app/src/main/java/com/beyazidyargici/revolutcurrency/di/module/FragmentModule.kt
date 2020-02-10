package com.beyazidyargici.revolutcurrency.di.module

import com.beyazidyargici.revolutcurrency.base.BaseFragment
import com.beyazidyargici.revolutcurrency.ui.currencyrates.CurrencyRatesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyRatesFragment(): CurrencyRatesFragment


}