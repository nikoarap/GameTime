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
import com.nikoarap.gametime.networking.deserializers.EventModelDeserializer
import com.nikoarap.gametime.networking.deserializers.SportModelDeserializer
import com.nikoarap.gametime.utils.DialogUtils
import com.nikoarap.gametime.utils.ToastUtils
import com.nikoarap.gametime.view.composables.MainComponent
import com.nikoarap.gametime.viewmodels.MainViewModel
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
class MainActivity : ComponentActivity(), ConnectivityCallback {

    private var realm: Realm? = null
    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private lateinit var sportModelDeserializer: SportModelDeserializer
    private lateinit var eventModelDeserializer: EventModelDeserializer
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
        initDeserializer()
        initViewModel()
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

    /**
     * Initializes the realm instance properly.
     */
    private fun initRealm() {
        if (realm == null || realm!!.isClosed) {
            realm = Realm.getDefaultInstance()
        }
    }

    /**
     * Initializes the deserializers in order to observe for any exceptions caught from them.
     */
    private fun initDeserializer() {
        sportModelDeserializer = SportModelDeserializer()
        eventModelDeserializer = EventModelDeserializer()
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
        sportModelDeserializer.getErrorLiveData().observe(this) {
            ToastUtils.showErrorToast(this)
            sportModelDeserializer.resetErrorLiveData()
        }
        eventModelDeserializer.getErrorLiveData().observe(this) {
            ToastUtils.showErrorToast(this)
            eventModelDeserializer.resetErrorLiveData()
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
     * onDestroy Lifecycle func. Releases the Realm instance before the activity is destroyed.
     *
     * When the Android activity is being destroyed, this method is called. It ensures that the Realm instance is properly closed
     * if it exists and is not already closed, preventing resource leaks and ensuring data integrity.
     *
     * @see Realm
     */
    override fun onDestroy() {
        super.onDestroy()
        if (realm != null && !realm!!.isClosed) {
            realm!!.close()
        }
    }

    /**
     * Callback method called when network connectivity becomes available. It triggers the fetching of data from the repository
     * using the ViewModel. If a connectivity dialog is currently showing, it dismisses the dialog.
     */
    override fun onConnectivityAvailable() {
        viewModel.fetchDataFromRepoIfNeeded()
        if (connectivityDialog?.isShowing == true) {
            connectivityDialog?.dismiss()
        }
    }

    /**
     * Callback method called when network connectivity is lost. It sets a LiveData flag in the ViewModel to show a connectivity dialog.
     */
    override fun onConnectivityLost() {
        viewModel.showConnectivityDialog.value = true
    }
}


