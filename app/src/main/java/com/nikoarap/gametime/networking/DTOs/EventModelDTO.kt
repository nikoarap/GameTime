package com.nikoarap.gametime.networking.DTOs

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO_LONG

data class EventModelDTO (
    @SerializedName("i")
    var id: String = EMPTY_STRING,
    @SerializedName("si")
    var sportId: String = EMPTY_STRING,
    @SerializedName("d")
    var name: String = EMPTY_STRING,
    @SerializedName("tt")
    var startTime: Long = VALUE_ZERO_LONG,
)
