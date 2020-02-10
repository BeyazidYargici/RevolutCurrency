package com.beyazidyargici.revolutcurrency.util

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import com.beyazidyargici.revolutcurrency.base.BaseActivity
import com.beyazidyargici.revolutcurrency.base.BaseDatasourceImp
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  This class using in [BaseActivity] and [BaseDatasourceImp]. It's provides network connection status
 *  Created by beyazid on 10/02/2020.
 */
@Singleton
class InternetConnectionAvailability @Inject constructor(val context: Context) : LiveData<Boolean>() {


     var connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(getConnectivityManagerCallback())
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
            else -> context.registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
        else context.unregisterReceiver(networkReceiver)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkAvailableRequest() {
        val builder = NetworkRequest.Builder()
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(builder.build(), getConnectivityManagerCallback())
    }

    private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) = postValue(true)

                override fun onLost(network: Network?) = postValue(false)
            }
            return connectivityManagerCallback
        } else {
            throw IllegalAccessError("Should not happened")
        }
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) = updateConnection()
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }
}