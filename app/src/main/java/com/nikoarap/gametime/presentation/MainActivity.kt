package com.nikoarap.gametime.presentation

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nikoarap.gametime.utils.DialogUtils
import com.nikoarap.gametime.utils.broadcastReceiver.ConnectivityCallback
import com.nikoarap.gametime.utils.broadcastReceiver.NetworkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), ConnectivityCallback {

    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private var connectivityDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComponent()
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }

    override fun onConnectivityAvailable() {
        if (connectivityDialog?.isShowing == true) {
            connectivityDialog?.dismiss()
        }
    }

    override fun onConnectivityLost() {
        if (connectivityDialog?.isShowing == false) {
            connectivityDialog = DialogUtils.showConnectivityDialog(this)
            connectivityDialog?.show()
        }
    }
}


