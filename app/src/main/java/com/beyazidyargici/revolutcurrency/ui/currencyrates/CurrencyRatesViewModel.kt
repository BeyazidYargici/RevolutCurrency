package com.beyazidyargici.revolutcurrency.ui.currencyrates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel
import com.beyazidyargici.revolutcurrency.data.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 10/02/2020.
 */
class CurrencyRatesViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) : ViewModel() {

    var currencyModel = MutableLiveData<CurrencyModel>()
    val isNetworkAvailable= MutableLiveData<Boolean>()

    /**
     *  it feeds the livedata with fetched data from repository
     */
    fun getCurrency(baseCurrency : String) = viewModelScope.launch {
        currencyModel.value = currencyRepository.getCurrency(baseCurrency).value
        isNetworkAvailable.value = currencyRepository.isNetworkAvailable.value
    }

    private var timer = Timer()

    /**
     * Trigger to fetching currencies for every second
     */
    fun intervalRequest(baseCurrency: String) {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runBlocking(Dispatchers.IO) { getCurrency(baseCurrency) }
            }
        }, 0, 1000)
    }

    fun stopTimer(){
        timer.cancel()
    }




}
