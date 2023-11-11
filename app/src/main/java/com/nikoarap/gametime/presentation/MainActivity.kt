package com.nikoarap.gametime.presentation

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nikoarap.gametime.utils.broadcastReceiver.ConnectivityCallback
import com.nikoarap.gametime.utils.broadcastReceiver.NetworkChangeReceiver
import com.nikoarap.gametime.utils.DialogUtils
import com.nikoarap.gametime.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm

@AndroidEntryPoint
class MainActivity : ComponentActivity(), ConnectivityCallback {

    private var realm: Realm? = null
    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private var connectivityDialog: AlertDialog? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRealm()
        initViewModel()
        initObservables()
        setContent {
            MainComponent()
        }
    }


    private fun initRealm() {
        if (realm == null || realm!!.isClosed) {
            realm = Realm.getDefaultInstance()
        }
    }


    private fun initViewModel() {
        viewModel.initViewModel(realm)
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

    /**
     * Callback method called when network connectivity becomes available. It triggers the fetching of data from the repository
     * using the ViewModel. If a connectivity dialog is currently showing, it dismisses the dialog.
     */
    override fun onConnectivityAvailable() {
        if (connectivityDialog?.isShowing == true) {
            connectivityDialog?.dismiss()
            viewModel.fetchDataFromRepoIfNeeded()
        }
    }

    /**
     * Callback method called when network connectivity is lost. It sets a LiveData flag in the ViewModel to show a connectivity dialog.
     */
    override fun onConnectivityLost() {
        viewModel.showConnectivityDialog.value = true
    }
}


