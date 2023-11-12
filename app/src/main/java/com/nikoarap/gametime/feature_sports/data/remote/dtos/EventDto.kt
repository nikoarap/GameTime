package com.nikoarap.gametime.feature_sports.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.feature_sports.domain.models.Event
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.SCORE_REGEX
import com.nikoarap.gametime.utils.Constants.SPACER_SCORE_SPACER_REGEX

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
data class EventModelDto (
    @SerializedName("i")
    val id: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("d")
    val name: String ,
    @SerializedName("tt")
    val startTime: Long
)

fun EventModelDto.toEventModel(): Event {
    val competitors: Pair<String, String> = unpackCompetitors(name)
    val eventModel = Event()
    eventModel.id = id
    eventModel.sportId = sportId
    eventModel.competitorLeft = competitors.first
    eventModel.competitorRight = competitors.second
    eventModel.startTime = startTime
    return eventModel
}

private fun unpackCompetitors(name: String): Pair<String, String> {
    if (name.contains(SPACER_SCORE_SPACER_REGEX)) {
        val parts = name.split(SPACER_SCORE_SPACER_REGEX)
        if (parts.size == 2) {
            return Pair(parts[0], parts[1])
        }
    } else if (name.contains(SCORE_REGEX)) {
        val parts = name.split(SCORE_REGEX)
        if (parts.size == 2) {
            return Pair(parts[0], parts[1])
        }
    }
    return Pair(EMPTY_STRING, EMPTY_STRING)
}