package com.nikoarap.gametime

import android.app.Application
import android.content.Context
import com.nikoarap.gametime.utils.Constants.Companion.REALM_ID
import com.nikoarap.gametime.utils.Constants.Companion.REALM_SCHEMA_VER
import io.realm.Realm
import io.realm.RealmConfiguration

class GameTimeApplication: Application() {

    private var realmConfig: RealmConfiguration? = null
    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Realm.init(this)
        realmConfig = RealmConfiguration.Builder()
            .name(REALM_ID)
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(REALM_SCHEMA_VER)
            .allowWritesOnUiThread(true)
            .build()

        realmConfig.let { Realm.setDefaultConfiguration(it) }
    }

    fun getContext(): Context? {
        return appContext
    }

}