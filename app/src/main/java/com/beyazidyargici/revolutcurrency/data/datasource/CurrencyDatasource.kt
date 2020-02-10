package com.beyazidyargici.revolutcurrency.data.datasource

import androidx.lifecycle.LiveData
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel

/**
 * Created by beyazid on 10/02/2020.
 */
interface CurrencyDatasource {
    val fetchedCurrency: LiveData<CurrencyModel>
    suspend fun getCurrencies(baseCurrency : String) : LiveData<CurrencyModel>
    val isNetworkAvailable: LiveData<Boolean>
}