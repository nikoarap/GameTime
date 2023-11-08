package com.nikoarap.gametime.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.models.NavBottomItem
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.networking.repositories.SportsRepository
import com.nikoarap.gametime.realm.DataStorage
import com.nikoarap.gametime.realm.RealmLiveData
import com.nikoarap.gametime.realm.RealmUtils
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ONE
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import io.realm.Realm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * The `MainViewModel` class is responsible for managing data and providing it to the UI components. It interacts with the data
 * repository to fetch and update sports data. It also handles user interactions and maintains the state of the selected tab
 * (Home or Favorites).
 *
 * @property application The application context.
 */
open class MainViewModel(application: Application): AndroidViewModel(application) {

    private var realm: Realm? = null
    private var sportsRepository = SportsRepository()
    private var sportModels: RealmLiveData<SportModel>? = null
    var favouriteSelected: MutableLiveData<Boolean> = MutableLiveData()
    var showConnectivityDialog: MutableLiveData<Boolean> = MutableLiveData()

    private val _sportModels = MutableStateFlow(emptyList<SportModel>())
    val sportModelsStateFlow: StateFlow<List<SportModel>>
        get() = _sportModels.asStateFlow()

    /**
     * Initialize the view model.
     *
     * @param realm   The Realm instance to use for data operations.
     */
    fun initViewModel(realm: Realm?) {
        this.realm = realm
        favouriteSelected.value = false
        showConnectivityDialog.value = false
        sportModels = RealmLiveData(DataStorage.getEmpty(realm))
        fetchDataFromRepoIfNeeded()
        loadSports()
    }

    /**
     * Fetch sports data from the repository. It runs in a background coroutine.
     */
    fun fetchDataFromRepoIfNeeded() {
        val results = realm?.let { DataStorage.getAll(it) }
        if (results?.isEmpty() == true) {
            viewModelScope.launch {
                sportsRepository.fetchData()
            }
        }
    }

    /**
     * Load sports data from the database based on user preferences and selected tab.
     * If the user has selected the "Favorites" tab, it loads favorite sports; otherwise, it loads all sports.
     * It then updates the LiveData holding the sports data for UI updates.
     */
    private fun loadSports() {
        if (realm == null) {
            realm = RealmUtils.getRealm()
        }

        if (realm?.isInTransaction == false) {
            if (favouriteSelected.value == true) {
                DataStorage.getFavoriteSports(realm)?.let { sportModels?.setResults(it) }
            } else {
                DataStorage.getAll(realm)?.let { sportModels?.setResults(it) }
            }
        }
    }

    /**
     * Emit a list of sport models to update the UI using the StateFlow.
     *
     * @param sportModels List of sport models.
     */
    fun emitSportModels(sportModels : List<SportModel>) {
        viewModelScope.launch {
            _sportModels.emit(sportModels.toMutableList())
        }
    }

    /**
     * Get the RealmLiveData containing sport models.
     *
     * @return RealmLiveData containing sport models.
     */
    fun getSportModels(): RealmLiveData<SportModel>? {
        return sportModels
    }

    /**
     * Handle user selection of the "Home" tab.
     */
    fun onHomeSelected() {
        favouriteSelected.value = false
        loadSports()

    }

    /**
     * Handle user selection of the "Favorites" tab.
     */
    fun onFavoritesSelected() {
        favouriteSelected.value = true
        loadSports()
    }

    /**
     * Handle user interaction to select/unselect an event as a favorite.
     *
     * @param eventModel        The event to be marked/unmarked as favorite.
     * @param isChecked         A flag indicating whether the event should be marked as a favorite.
     */
    fun onEventFavouriteChecked(eventModel: EventModel, isChecked: Boolean) {
        DataStorage.updateEventModelWithFavourite(eventModel, isChecked)
        loadSports()
    }

    /**
     * Handle user interaction to select/unselect a sport as a favorite.
     *
     * @param sportModel        The sport to be marked/unmarked as favorite.
     * @param isChecked         A flag indicating whether the sport should be marked as a favorite.
     */
    fun onSportFavouriteChecked(sportModel: SportModel, isChecked: Boolean) {
        DataStorage.updateSportModelWithFavourite(sportModel, isChecked)
        loadSports()
    }

    /**
     * Handle user interaction to expand a sport section.
     *
     * @param sportModel         The sport section to be expanded or not.
     * @param isExpanded         A flag indicating whether the sport should be expanded or not.
     */
    fun onSportExpanded(sportModel: SportModel, isExpanded: Boolean) {
        DataStorage.updateSportModelWithExpanded(sportModel, isExpanded)
        loadSports()
    }
}