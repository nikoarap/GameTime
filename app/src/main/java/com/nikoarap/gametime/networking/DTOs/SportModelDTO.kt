package com.nikoarap.gametime.networking.DTOs

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING

/**
 * A data class representing a sports model in DTO (Data Transfer Object) form.
 *
 * This class contains fields that correspond to the serialized properties of a sports model when
 * it is transferred between the client and the server. It is used for parsing and serializing
 * sports data to and from JSON format.
 *
 * @property id                 The unique identifier for the sports model.
 * @property name               The name of the sports model.
 * @property activeEvents       A list of associated events for the sports model.
 */
data class SportModelDTO (
    @SerializedName("i")
    var id: String = EMPTY_STRING,
    @SerializedName("d")
    var name: String = EMPTY_STRING,
    @SerializedName("e")
    var activeEvents: List<EventModelDTO> = arrayListOf(),
)
