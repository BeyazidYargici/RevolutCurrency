package com.beyazidyargici.revolutcurrency.ui.currencyrates

/**
 *  Created by beyazid on 10/02/2020.
 *  This interface created for changing base currency properties
 *  Implemented by [CurrencyRatesFragment], triggered by [CurrencyRatesAdapter]
 */
interface CurrencyListener {
    fun changeBaseValue(amount : Double)
    fun changeBaseCurrency(newBaseCurrency: String, selectedIndex: Int)
}