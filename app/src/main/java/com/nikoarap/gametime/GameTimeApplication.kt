package com.nikoarap.gametime

import android.app.Application
import com.nikoarap.gametime.utils.Constants.Companion.REALM_ID
import com.nikoarap.gametime.utils.Constants.Companion.REALM_SCHEMA_VER
import io.realm.Realm
import io.realm.RealmConfiguration

class GameTimeApplication: Application() {

    private var realmConfig: RealmConfiguration? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realmConfig = RealmConfiguration.Builder()
            .name(REALM_ID)
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(REALM_SCHEMA_VER)
            .allowWritesOnUiThread(true)
            .build()

        realmConfig.let { it?.let { realmConfiguration -> Realm.setDefaultConfiguration(realmConfiguration) } }
    }
}