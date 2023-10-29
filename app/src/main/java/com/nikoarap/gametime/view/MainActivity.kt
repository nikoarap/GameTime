package com.nikoarap.gametime.view

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nikoarap.gametime.networking.broadcastReceiver.ConnectivityCallback
import com.nikoarap.gametime.networking.broadcastReceiver.NetworkChangeReceiver
import com.nikoarap.gametime.utils.DialogUtils
import com.nikoarap.gametime.view.composables.MainComponent
import com.nikoarap.gametime.viewmodels.MainViewModel
import io.realm.Realm

class MainActivity : ComponentActivity(), ConnectivityCallback {

    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private var connectivityDialog: AlertDialog? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initViewModel(Realm.getDefaultInstance())
        initObservables()
        setContent {
            val sportsList by viewModel.sportModelsStateFlow.collectAsState()
            val mainComponent = MainComponent(viewModel)
            mainComponent.LoadMainComponent(
                sports = sportsList,
                navBottomItems = viewModel.navBottomItems,
                viewModel.selectedItemIndex,
                viewModel.favouriteSelected.value
            )
        }
    }

    private fun initObservables() {
        viewModel.getSportModels()?.observe(this) {
            run {
                //check if the realm results set we are observing for nullability and validity (realm object validity)
                if (it != null && it.isValid) {
                    viewModel.emitSportModels(it)
                }
            }
        }
        viewModel.showConnectivityDialog.observe(this) {
            run {
                if (it == true) {
                    connectivityDialog = DialogUtils.showConnectivityDialog(this)
                    connectivityDialog?.show()
                }
            }
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
        viewModel.fetchDataFromRepo()
        viewModel.showConnectivityDialog.value = false
        if (connectivityDialog?.isShowing == true) {
            connectivityDialog?.dismiss()
        }
    }

    override fun onConnectivityLost() {
        viewModel.showConnectivityDialog.value = true
//        connectivityDialog = DialogUtils.showConnectivityDialog(this)
//        connectivityDialog?.show()
    }
}


