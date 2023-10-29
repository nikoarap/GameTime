package com.nikoarap.gametime.networking.broadcastReceiver

interface ConnectivityCallback {
    fun onConnectivityAvailable()
    fun onConnectivityLost()
}