package com.beyazidyargici.revolutcurrency.data.model

import com.beyazidyargici.revolutcurrency.data.model.dto.CurrencyItem
import com.beyazidyargici.revolutcurrency.data.model.response.CurrencyModel

/**
 * Created by beyazid on 10/02/2020.
 */
class CurrencyMapper {

    /**
     * This method converts the response model to currency list
     */
    fun convertResponseToCurrencyList(currencyModel: CurrencyModel, baseValue: Double) = arrayListOf<CurrencyItem>().apply {
        // add base currency to the list
        add(0,CurrencyItem(currencyModel.base, baseValue, true))
        // add other currencies to the list
        currencyModel.rates.forEach { (currency, rate) ->
            add(CurrencyItem(code = currency, rate = rate, isBase = false))
        }
    }
}