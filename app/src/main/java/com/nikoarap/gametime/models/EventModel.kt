package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

open class EventModel: RealmModel {
    @PrimaryKey
    var id: String = EMPTY
    var sportId: String = EMPTY
    var name: String = EMPTY
    var startTime: Int = 0
}

