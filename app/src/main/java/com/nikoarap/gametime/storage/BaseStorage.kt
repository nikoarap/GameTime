package com.nikoarap.gametime.storage

import android.util.Log
import com.nikoarap.gametime.utils.RealmUtils
import io.realm.Realm

open class BaseStorage {

    protected open fun executeTransaction(transaction: Realm.Transaction) {
        try {
            RealmUtils.getRealm().use { realm -> realm?.executeTransaction(transaction) }
        } catch (e: Throwable) {
            Log.e("Executing transaction", Log.getStackTraceString(e))
        }
    }

}