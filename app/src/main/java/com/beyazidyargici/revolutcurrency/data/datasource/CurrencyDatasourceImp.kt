package com.beyazidyargici.revolutcurrency.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazidyargici.revolutcurrency.base.BaseDatasource
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel
import com.beyazidyargici.revolutcurrency.data.network.ApiService
import javax.inject.Inject

/**
 *  This class fetches [CurrencyModel] from remote(restful api) or local(database) datasource.
 *  Created by beyazid on 09/02/2020.
 *  todo there is no database for now. It can be added to datasource classes
 */
class CurrencyDatasourceImp @Inject constructor(private val apiService: ApiService, private val baseDatasource: BaseDatasource) : CurrencyDatasource {

    override val isNetworkAvailable : LiveData<Boolean> = baseDatasource.isNetworkAvailable

    private var mFetchedCurrency = MutableLiveData<CurrencyModel>()

    override val fetchedCurrency: LiveData<CurrencyModel> = mFetchedCurrency

    /**
     *  This method is responsible for fetching [CurrencyModel] from api and preparing data to be used in repository
     */
    override suspend fun getCurrencies(baseCurrency: String): LiveData<CurrencyModel> =
        requestForCurrencies(baseCurrency)

    private suspend fun requestForCurrencies(baseCurrency: String): LiveData<CurrencyModel> {
        // check internet connection for the start of every request
        baseDatasource.checkInternetConnection()
        // call api service
        mFetchedCurrency.postValue(
            baseDatasource.safeApiCall(
                call = { apiService.getCurrencyRates(baseCurrency) },
                error = ""
            )
        )
        return fetchedCurrency
    }


}