package com.beyazidyargici.revolutcurrency.di.module

import com.beyazidyargici.revolutcurrency.base.BaseDatasource
import com.beyazidyargici.revolutcurrency.base.BaseDatasourceImp
import com.beyazidyargici.revolutcurrency.data.datasource.CurrencyDatasource
import com.beyazidyargici.revolutcurrency.data.datasource.CurrencyDatasourceImp
import com.beyazidyargici.revolutcurrency.data.network.ApiService
import com.beyazidyargici.revolutcurrency.util.InternetConnectionAvailability
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class,NetworkModule::class])
class DataSourceModule {

    @Provides
    @Singleton
    fun provideBaseDatasource(internetConnectionAvailability: InternetConnectionAvailability) : BaseDatasource = BaseDatasourceImp(internetConnectionAvailability)


    @Provides
    internal fun provideCurrencyDatasource(apiService: ApiService, baseDatasource: BaseDatasource): CurrencyDatasource = CurrencyDatasourceImp(apiService,baseDatasource)



}