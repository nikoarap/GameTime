package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO_LONG
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class EventModel: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var sportId: String = EMPTY_STRING
    var competitorLeft: String = EMPTY_STRING
    var competitorRight: String = EMPTY_STRING
    var startTime: Long = VALUE_ZERO_LONG
    var isFavourite: Boolean = false
}

