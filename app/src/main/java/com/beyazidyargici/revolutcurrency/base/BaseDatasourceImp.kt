package com.beyazidyargici.revolutcurrency.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazidyargici.revolutcurrency.data.network.Status
import com.beyazidyargici.revolutcurrency.util.InternetConnectionAvailability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * This class should be implemented by datasource classes
 * Created by beyazid on 10/02/2020.
 */
open class BaseDatasourceImp @Inject constructor(var connection: InternetConnectionAvailability) : BaseDatasource {

    private var mIsNetworkAvailable = MutableLiveData<Boolean>()

    override var isNetworkAvailable: LiveData<Boolean> = mIsNetworkAvailable

    /**
     * The method using for result of the response
     * Called by datasource classes
     */
    override suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T? {
        val result = responseResult(call, error)
        var output: T? = null
        when (result) {
            is Status.Success -> output = result.output
            is Status.Error -> Log.e("Error", "${result.exception}")
        }
        return output
    }

    override suspend fun <T : Any> responseResult(call: suspend () -> Response<T>, error: String): Status<T> {
        val response = try {
            call.invoke()
        } catch (e: Exception) {
            return Status.Error(IOException("an error occurred while processing your request : ${e.localizedMessage}"))
        }
        return if (response.isSuccessful)
            Status.Success(response.body()!!)
        else
            Status.Error(IOException("an error occurred while processing your request -> code : ${response.code()} error : $error"))
    }

    /**
     * This method checks the internet availability on main thread
     */
    override suspend fun checkInternetConnection(){
        val connectionLiveData = connection
        withContext(Dispatchers.Main) {
            connectionLiveData.observeForever { isConnected ->
                isConnected?.let {
                    mIsNetworkAvailable.value = it
                }
            }

        }
    }

}