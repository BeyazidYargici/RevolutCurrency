package com.beyazidyargici.revolutcurrency.data.network
import com.beyazidyargici.revolutcurrency.base.BaseDatasourceImp

/**
 *  Created by beyazid on 10/02/2020.
 *  Using by [BaseDatasourceImp] class
 */
sealed class Status<out T : Any>{
    data class Success<out T : Any>(val output : T) : Status<T>()
    data class Error(val exception: Exception)  : Status<Nothing>()
}