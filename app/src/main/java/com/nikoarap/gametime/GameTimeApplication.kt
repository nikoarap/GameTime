package com.nikoarap.gametime

import android.app.Application
import com.nikoarap.gametime.utils.Constants.Companion.REALM_ID
import com.nikoarap.gametime.utils.Constants.Companion.REALM_SCHEMA_VER
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Custom [Application] class for initializing Realm and its configuration.
 * It sets up the Realm database with appropriate configurations during application startup.
 */
@HiltAndroidApp
class GameTimeApplication: Application() {

    private var realmConfig: RealmConfiguration? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realmConfig = RealmConfiguration.Builder()
            .name(REALM_ID)
            .schemaVersion(REALM_SCHEMA_VER)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()

        realmConfig.let { it?.let { realmConfiguration -> Realm.setDefaultConfiguration(realmConfiguration) } }
    }
}