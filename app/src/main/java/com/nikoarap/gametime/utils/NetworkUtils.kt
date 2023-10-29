package com.nikoarap.gametime.utils

import android.content.Context
import android.net.ConnectivityManager
import com.nikoarap.gametime.GameTimeApplication

class NetworkUtils {

    companion object {

        fun isNetworkConnected(): Boolean {
            val context: Context? = GameTimeApplication().getContext()
            return hasNetworkConnection(context)
        }

        private fun hasNetworkConnection(context: Context?): Boolean {
            if (context == null) {
                return false
            }

            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return manager.activeNetworkInfo != null && manager.activeNetworkInfo!!.isConnectedOrConnecting
        }

    }

}