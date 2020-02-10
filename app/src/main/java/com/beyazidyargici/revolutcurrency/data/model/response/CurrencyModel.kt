package com.beyazidyargici.revolutcurrency.data.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyModel(

    @field:SerializedName("date")
	val date: String? = null,

    @field:SerializedName("rates")
	val rates: Map<String,Double>,

    @field:SerializedName("base")
	val base: String = ""
)