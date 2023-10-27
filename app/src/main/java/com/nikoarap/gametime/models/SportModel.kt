package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY

data class SportModel(
    var id: String = EMPTY,
    var name: String = EMPTY,
    var activeEvents: ArrayList<EventModel> = arrayListOf()
)
