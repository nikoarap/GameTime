package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SportModel: RealmObject() {
    @PrimaryKey
    var id: String = EMPTY_STRING
    var name: String = EMPTY_STRING
    var activeEvents: RealmList<EventModel> = RealmList()
    var isFavourite: Boolean = false
    var isExpanded: Boolean = false
}



