package com.nikoarap.gametime.networking.DTOs

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO_LONG

/**
 * A data class representing an event model in DTO (Data Transfer Object) form.
 *
 * This class contains fields that correspond to the serialized properties of an event model when
 * it is transferred between the client and the server. It is used for parsing and serializing
 * event data to and from JSON format.
 *
 * @property id             The unique identifier for the event.
 * @property sportId        The identifier of the sport associated with the event.
 * @property name           The name of the event.
 * @property startTime      The timestamp when the event is scheduled to start.
 */
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
