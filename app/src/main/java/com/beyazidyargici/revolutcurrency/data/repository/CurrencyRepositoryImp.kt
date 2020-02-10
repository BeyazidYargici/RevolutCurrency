package com.beyazidyargici.revolutcurrency.data.repository

import androidx.lifecycle.LiveData
import com.beyazidyargici.revolutcurrency.data.datasource.CurrencyDatasource
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel
import com.beyazidyargici.revolutcurrency.data.datasource.CurrencyDatasourceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Repository implementations will be called by the ViewModel concerned.
 *  Created by beyazid on 10/02/2020.
 */
class CurrencyRepositoryImp @Inject constructor(private val currencyDatasource: CurrencyDatasource) : CurrencyRepository {

    override val isNetworkAvailable: LiveData<Boolean> = currencyDatasource.isNetworkAvailable

    /**
     *  This method gets [CurrencyModel] from [CurrencyDatasourceImp] asynchronously
     */
    override suspend fun getCurrency(baseCurrency: String): LiveData<CurrencyModel> =
        withContext(Dispatchers.IO) {
            currencyDatasource.getCurrencies(baseCurrency)
        }
}