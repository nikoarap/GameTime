package com.nikoarap.gametime.feature_sports.domain.models

import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * A RealmObject representing a sports category.
 * Realm does not support data classes, so we use an open class here instead with non-final variables that can be modified.
 *
 * @property id                 The unique identifier of the sport category.
 * @property name               The name or title of the sport category.
 * @property activeEvents       A list of active events associated with this sport category.
 * @property isFavourite        Indicates whether the sport category is marked as a favorite.
 */
open class Sport: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var name: String = EMPTY_STRING
    var activeEvents: RealmList<Event> = RealmList()
    var isFavourite: Boolean = false
}



