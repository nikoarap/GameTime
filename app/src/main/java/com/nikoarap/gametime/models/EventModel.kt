package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

open class EventModel: RealmModel {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var sportId: String = EMPTY_STRING
    var name: String = EMPTY_STRING
    var startTime: Int = VALUE_ZERO
}

