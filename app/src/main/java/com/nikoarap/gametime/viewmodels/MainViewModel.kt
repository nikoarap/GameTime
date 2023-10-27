package com.nikoarap.gametime.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.realm.Realm

open class MainViewModel(application: Application): AndroidViewModel(application) {

    private var realm: Realm? = null

    fun initViewModel(realm: Realm?) {
        this.realm = realm
    }

}