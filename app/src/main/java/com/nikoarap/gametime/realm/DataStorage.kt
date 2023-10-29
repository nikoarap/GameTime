package com.nikoarap.gametime.realm

import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import io.realm.Realm
import io.realm.RealmResults

open class DataStorage {

    companion object {

        fun insert(sportModel: SportModel) {
            RealmUtils.executeTransaction(fun(realm: Realm) {
                realm.insertOrUpdate(sportModel)
            })
        }

        fun getAll(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.findAll()
        }

        fun getFavoriteSports(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.equalTo("isFavourite", true)?.findAll()
        }

        fun getEmpty(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.equalTo("id", EMPTY_STRING)?.findAll()
        }

        fun updateSportModelWithFavourite(sportModel: SportModel, isFavourite: Boolean) {
            RealmUtils.executeTransaction(fun(realm: Realm) {
                sportModel.isFavourite = isFavourite
                realm.insertOrUpdate(sportModel)
            })
        }

        fun updateEventModelWithFavourite(eventModel: EventModel, isFavourite: Boolean) {
            RealmUtils.executeTransaction(fun(realm: Realm) {
                eventModel.isFavourite = isFavourite
                realm.insertOrUpdate(eventModel)
            })
        }
    }

}