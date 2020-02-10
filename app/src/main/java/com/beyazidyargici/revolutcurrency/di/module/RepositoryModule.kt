package com.beyazidyargici.revolutcurrency.di.module

import com.beyazidyargici.revolutcurrency.data.datasource.CurrencyDatasource
import com.beyazidyargici.revolutcurrency.data.repository.CurrencyRepository
import com.beyazidyargici.revolutcurrency.data.repository.CurrencyRepositoryImp
import dagger.Module
import dagger.Provides

@Module(includes = [DataSourceModule::class])
class RepositoryModule {

    @Provides
    internal fun provideCurrencyRepositoryImp(currencyDatasource: CurrencyDatasource): CurrencyRepository = CurrencyRepositoryImp(currencyDatasource)

}