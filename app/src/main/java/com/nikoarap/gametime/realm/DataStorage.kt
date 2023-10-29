package com.nikoarap.gametime.realm

import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import io.realm.Realm
import io.realm.RealmResults

/**
 * A class for handling data storage operations using Realm.
 */
open class DataStorage {

    companion object {

        /**
         * Inserts a SportModel into the Realm database.
         *
         * @param sportModel The SportModel to insert or update.
         */
        fun insert(sportModel: SportModel) {
            RealmUtils.executeTransaction(fun(realm: Realm) {
                realm.insertOrUpdate(sportModel)
            })
        }

        /**
         * Retrieves all SportModels from the Realm database.
         *
         * @param realm         The Realm instance.
         * @return              A collection of SportModel objects.
         */
        fun getAll(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.notEqualTo("id", EMPTY_STRING)?.findAll()
        }

        /**
         * Retrieves favorite SportModels from the Realm database.
         *
         * @param realm         The Realm instance.
         * @return              A collection of favorite SportModel objects.
         */
        fun getFavoriteSports(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.equalTo("isFavourite", true)?.findAll()
        }

        /**
         * Retrieves SportModels with empty IDs from the Realm database.(used for RealmResult initializations)
         *
         * @param realm         The Realm instance.
         * @return              A collection of SportModel objects with empty IDs.
         */
        fun getEmpty(realm: Realm?): RealmResults<SportModel>? {
            return realm?.where(SportModel::class.java)?.equalTo("id", EMPTY_STRING)?.findAll()
        }

        /**
         * Updates a SportModel's favorite status in the Realm database.
         *
         * @param sportModel    The SportModel to update.
         * @param isFavourite   The new favorite status.
         */
        fun updateSportModelWithFavourite(sportModel: SportModel, isFavourite: Boolean) {
            RealmUtils.executeTransaction {
                sportModel.isFavourite = isFavourite
            }
        }

        /**
         * Updates an EventModel's favorite status in the Realm database.
         *
         * @param eventModel    The EventModel to update.
         * @param isFavourite   The new favorite status.
         */
        fun updateEventModelWithFavourite(eventModel: EventModel, isFavourite: Boolean) {
            RealmUtils.executeTransaction {
                eventModel.isFavourite = isFavourite
            }
        }
    }

}