package com.nikoarap.gametime.presentation

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nikoarap.gametime.presentation.navigation.NavigationComponent
import com.nikoarap.gametime.presentation.screenAllEvents.components.AllEventsScreen
import com.nikoarap.gametime.utils.broadcastReceiver.ConnectivityCallback
import com.nikoarap.gametime.utils.broadcastReceiver.NetworkChangeReceiver
import com.nikoarap.gametime.utils.DialogUtils
import com.nikoarap.gametime.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm

/**
 * The main activity for the application. It serves as the entry point and the user interface for
 * displaying sports events. This activity implements the `ConnectivityCallback` interface to
 * handle network connectivity changes.
 *
 * In this activity, a network change receiver (`NetworkChangeReceiver`) is registered to listen for
 * network connectivity changes, and a view model (`MainViewModel`) is used to manage and observe
 * the data related to sports events. The activity initializes the UI using a composable function
 * (`MainComponent.LoadMainComponent`) and observes the view model for changes to sports data and
 * connectivity status. It also manages the display of a connectivity dialog when connectivity is lost.
 *
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ConnectivityCallback {

    private var realm: Realm? = null
    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private var connectivityDialog: AlertDialog? = null
    private val viewModel: MainViewModel by viewModels()

    /**
     * onCreate Lifecycle fun. Initializes the ViewModel with a Realm instance and sets up
     * the observables. It also sets the content of the activity, which includes a composable UI using Jetpack Compose.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRealm()
        initViewModel()
        initObservables()
        setContent {
            NavigationComponent()
        }
    }

    /**
     * Initializes the realm instance properly.
     */
    private fun initRealm() {
        if (realm == null || realm!!.isClosed) {
            realm = Realm.getDefaultInstance()
        }
    }

    /**
     * Initializes the view model with a realm instance.
     */
    private fun initViewModel() {
        viewModel.initViewModel(realm)
    }

    /**
     * Initializes observables to listen for changes in the view model's live data.
     */
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

    /**
     * onStart Lifecycle fun. It registers a broadcast receiver to listen for
     * connectivity change events.
     */
    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    /**
     * onStop Lifecycle fun. It unregisters the network change receiver to stop listening
     * for connectivity change events.
     */
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


