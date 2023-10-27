package com.nikoarap.gametime.storage

import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.RealmUtils

open class SportsStorage {

    companion object {

        fun insert(sportModel: SportModel) {
            RealmUtils.executeTransaction { realm -> realm.insertOrUpdate(sportModel) }
        }
    }

}