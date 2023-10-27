package com.nikoarap.gametime.realm

import com.nikoarap.gametime.models.SportModel
import io.realm.Realm
import io.realm.RealmResults

open class SportsStorage {

    companion object {

        fun insert(sportModel: SportModel) {
            RealmUtils.executeTransaction { realm -> realm.insertOrUpdate(sportModel) }
        }

        fun getAll(realm: Realm): RealmResults<SportModel?>? {
            return realm.where(SportModel::class.java).findAll()
        }
    }

}