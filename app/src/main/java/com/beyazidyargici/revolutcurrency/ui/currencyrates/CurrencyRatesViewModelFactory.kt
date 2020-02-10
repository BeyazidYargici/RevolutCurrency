package com.beyazidyargici.revolutcurrency.ui.currencyrates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beyazidyargici.revolutcurrency.data.repository.CurrencyRepository
import javax.inject.Inject

/**
 *  Created by beyazid on 10/02/2020.
 */
class CurrencyRatesViewModelFactory @Inject constructor(private val currencyRepository: CurrencyRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CurrencyRatesViewModel(
        currencyRepository
    ) as T
}