package com.nikoarap.gametime.networking.DTOs

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING

data class SportModelDTO (
    @SerializedName("i")
    var id: String = EMPTY_STRING,
    @SerializedName("d")
    var name: String = EMPTY_STRING,
    @SerializedName("e")
    var activeEvents: List<EventModelDTO> = arrayListOf(),
)
