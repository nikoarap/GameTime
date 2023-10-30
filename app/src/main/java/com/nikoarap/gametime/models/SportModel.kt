package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * A RealmObject representing a sports category.
 *
 * @property id                 The unique identifier of the sport category.
 * @property name               The name or title of the sport category.
 * @property activeEvents       A list of active events associated with this sport category.
 * @property isFavourite        Indicates whether the sport category is marked as a favorite.
 * @property isExpanded         Indicates whether the sport category expanded or not.
 */
open class SportModel: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var name: String = EMPTY_STRING
    var activeEvents: RealmList<EventModel> = RealmList()
    var isFavourite: Boolean = false
    var isExpanded: Boolean = false
}



