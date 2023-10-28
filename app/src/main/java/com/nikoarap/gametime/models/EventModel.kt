package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class EventModel: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var sportId: String = EMPTY_STRING
    var competitorLeft: String = EMPTY_STRING
    var competitorRight: String = EMPTY_STRING
    var startTime: Int = VALUE_ZERO
    var isFavourite: Boolean = false
}

