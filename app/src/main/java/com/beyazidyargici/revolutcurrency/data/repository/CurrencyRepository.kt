package com.beyazidyargici.revolutcurrency.data.repository

import androidx.lifecycle.LiveData
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel

interface CurrencyRepository{
    val isNetworkAvailable: LiveData<Boolean>
    suspend fun getCurrency(baseCurrency : String) : LiveData<CurrencyModel>
}