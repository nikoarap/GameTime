package com.nikoarap.gametime.feature_sports.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.feature_sports.data.cache.entity.SportEntity

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
data class SportDto (
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val name: String,
    @SerializedName("e")
    val activeEvents: List<EventDto>
) {
    fun toSportEntity(): SportEntity {
        return SportEntity(
            id = id,
            name = name,
            activeEvents = activeEvents.map { it.toEvent() }
        )
    }
}


