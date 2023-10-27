package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

open class SportModel: RealmModel {
    @PrimaryKey
    var id: String = EMPTY
    var name: String = EMPTY
    var activeEvents: ArrayList<EventModel> = arrayListOf()
}



