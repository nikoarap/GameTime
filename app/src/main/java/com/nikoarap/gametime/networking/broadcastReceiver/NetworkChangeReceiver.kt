package com.nikoarap.gametime.networking.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

/**
 * A BroadcastReceiver for monitoring network connectivity changes and notifying a callback
 * when connectivity becomes available or is lost.
 *
 * @param connectivityCallback The callback to notify about network connectivity changes.
 */
class NetworkChangeReceiver(private val connectivityCallback: ConnectivityCallback) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            if (networkInfo != null && networkInfo.isConnected) {
                // The device has an active network connection
                connectivityCallback.onConnectivityAvailable()
            } else {
                // There is no active network connection
                connectivityCallback.onConnectivityLost()
            }
        }
    }
}