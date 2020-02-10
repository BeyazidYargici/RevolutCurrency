package com.beyazidyargici.revolutcurrency.data.network

import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel
import com.beyazidyargici.revolutcurrency.util.BASE_QUERY
import com.beyazidyargici.revolutcurrency.util.CURRENCY_RATE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Created by beyazid on 10/02/2020.
 *  This interface contains requests methods.
 *  It will be using by Retrofit. Retrofit generates requests.
 */
interface ApiService {

    @GET(value = CURRENCY_RATE_ENDPOINT)
    suspend fun getCurrencyRates(@Query(BASE_QUERY) baseCurrency: String): Response<CurrencyModel>

}