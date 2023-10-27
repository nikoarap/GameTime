package com.nikoarap.gametime.realm

import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import io.realm.Realm
import io.realm.RealmResults

open class DataStorage {

    companion object {

        fun insert(sportModel: SportModel) {
            RealmUtils.executeTransaction { realm -> realm.insertOrUpdate(sportModel) }
        }

        fun getAll(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.findAll()
        }

        fun getEmpty(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.equalTo("id", EMPTY_STRING)?.findAll()
        }
    }

}