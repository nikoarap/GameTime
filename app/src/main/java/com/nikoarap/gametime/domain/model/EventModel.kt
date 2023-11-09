package com.nikoarap.gametime.domain.model

import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.VALUE_ZERO_LONG
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * A RealmObject representing a sports event.
 * Realm does not support data classes, so we use an open class here instead with non-final variables that can be modified.
 *
 * @property id                 The unique identifier of the event.
 * @property sportId            The identifier of the sport to which this event belongs.
 * @property competitorLeft     The name of the left competitor or team.
 * @property competitorRight    The name of the right competitor or team.
 * @property startTime          The start time of the event in milliseconds since the epoch.
 * @property isFavourite        Indicates whether the event is marked as a favorite.
 */
open class EventModel: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var sportId: String = EMPTY_STRING
    var competitorLeft: String = EMPTY_STRING
    var competitorRight: String = EMPTY_STRING
    var startTime: Long = VALUE_ZERO_LONG
    var isFavourite: Boolean = false
}

