package com.nikoarap.gametime.models

import com.nikoarap.gametime.utils.Constants.Companion.EMPTY

data class EventModel(
    var id: String = EMPTY,
    var sportId: String = EMPTY,
    var name: String = EMPTY,
    var startTime: Int = 0,
)