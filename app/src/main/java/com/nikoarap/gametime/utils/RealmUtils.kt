package com.nikoarap.gametime.utils

import android.util.Log
import io.realm.Realm

open class RealmUtils {

    companion object {
        fun getRealm(): Realm? {
            val realmConfiguration = Realm.getDefaultConfiguration()
            val globalInstanceCount = realmConfiguration?.let { Realm.getGlobalInstanceCount(it) }
            val localInstanceCount = realmConfiguration?.let { Realm.getLocalInstanceCount(it) }
            val realm = Realm.getDefaultInstance()
            val globalInstanceCountNew = realmConfiguration?.let { Realm.getGlobalInstanceCount(it) }
            val localInstanceCountNew = realmConfiguration?.let { Realm.getLocalInstanceCount(it) }
            Log.i(
                RealmUtils::class.java.simpleName,
                "RealmUtils: Created - globalInstanceCount: " + globalInstanceCount
                        + " -> " + globalInstanceCountNew
                        + ", localInstanceCount: " + localInstanceCount
                        + " -> " + localInstanceCountNew
            )
            return realm
        }

        fun executeTransaction(transaction: Realm.Transaction) {
            try {
                getRealm().use { realm -> realm?.executeTransaction(transaction) }
            } catch (e: Throwable) {
                Log.e("Executing transaction", Log.getStackTraceString(e))
            }
        }
    }

}