package com.nikoarap.gametime.utils.broadcastReceiver

/**
 * A callback interface for handling connectivity changes.
 * Implement this interface to receive notifications when network connectivity becomes
 * available or is lost.
 */
interface ConnectivityCallback {

    /**
     * Called when network connectivity becomes available.
     */
    fun onConnectivityAvailable()

    /**
     * Called when network connectivity is lost.
     */
    fun onConnectivityLost()
}