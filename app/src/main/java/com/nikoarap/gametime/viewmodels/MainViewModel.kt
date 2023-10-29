package com.nikoarap.gametime.viewmodels

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nikoarap.gametime.models.NavBottomItem
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.networking.repositories.SportsRepository
import com.nikoarap.gametime.realm.RealmLiveData
import com.nikoarap.gametime.realm.DataStorage
import com.nikoarap.gametime.realm.RealmUtils
import com.nikoarap.gametime.utils.Constants.Companion.FAVORITES
import com.nikoarap.gametime.utils.Constants.Companion.HOME
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class MainViewModel(application: Application): AndroidViewModel(application) {

    private var realm: Realm? = null
    private var sportModels: RealmLiveData<SportModel>? = null
    private var sportsRepository = SportsRepository()

    private val _sportModels = MutableStateFlow(emptyList<SportModel>())
    val sportModelsStateFlow: StateFlow<List<SportModel>>
        get() = _sportModels.asStateFlow()

    var navBottomItems: List<NavBottomItem> = arrayListOf()

    var selectedItemIndex = 0

    fun initViewModel(realm: Realm?) {
        this.realm = realm
        createNavBottomItems()
        sportModels = RealmLiveData(DataStorage.getEmpty(realm))
        val results = realm?.let { DataStorage.getAll(it) }

        //if the DB returns 0 results, that means we have to fetch the data from the API
        if (results?.isEmpty() == true) {
            fetchDataFromRepo()
        }

        loadSports()
    }

    private fun createNavBottomItems() {
        navBottomItems = listOf(
            NavBottomItem(order = 0, label = HOME, imageVector = Icons.Filled.Home, onSelected = { onHomeSelected() }),
            NavBottomItem(order = 1, label = FAVORITES, imageVector = Icons.Filled.Star, onSelected = { onFavoritesSelected() }),
        )
    }

    private fun fetchDataFromRepo() {

        //todo check for internet connection here and act accordingly

        CoroutineScope(Dispatchers.IO).launch {
            sportsRepository.fetchData()
        }
    }

    fun loadSports() {
        if (realm == null) {
            realm = RealmUtils.getRealm()
        }
        if (realm?.isInTransaction == false) {
            DataStorage.getAll(realm)?.let { sportModels?.setResults(it) }
        }
    }

    fun emitSportModels(sportModels : List<SportModel>) {
        viewModelScope.launch {
            _sportModels.emit(sportModels.toMutableList())
        }
    }

    fun getSportModels(): RealmLiveData<SportModel>? {
        return sportModels
    }

    fun onHomeSelected() {
        selectedItemIndex = 0

    }

    fun onFavoritesSelected() {
        selectedItemIndex = 1
    }
}