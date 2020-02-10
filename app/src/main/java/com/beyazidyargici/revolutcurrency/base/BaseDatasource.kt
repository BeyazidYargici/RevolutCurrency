package com.beyazidyargici.revolutcurrency.base

import androidx.lifecycle.LiveData
import com.beyazidyargici.revolutcurrency.data.network.Status
import retrofit2.Response

/**
 * this interface implemented by [BaseDatasourceImp]
 * Created by beyazid on 10/02/2020.
 */
interface BaseDatasource {
    var isNetworkAvailable : LiveData<Boolean>
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T?
    suspend fun <T : Any> responseResult(call: suspend () -> Response<T>, error: String): Status<T>
    suspend fun checkInternetConnection()
}