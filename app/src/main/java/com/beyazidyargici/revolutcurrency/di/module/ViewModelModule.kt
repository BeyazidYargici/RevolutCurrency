package com.beyazidyargici.revolutcurrency.di.module

import androidx.lifecycle.ViewModel
import com.beyazidyargici.revolutcurrency.di.ViewModelKey
import com.beyazidyargici.revolutcurrency.ui.currencyrates.CurrencyRatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    /*
     * This method basically says inject this object into a Map using the @IntoMap annotation,
     * with the  YourViewModel.class as key,
     * and a Provider that will build a YourViewModel object.
     */
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyRatesViewModel::class)
    protected abstract fun bindLatestNewsViewModel(currencyRatesViewModel: CurrencyRatesViewModel): ViewModel


}
