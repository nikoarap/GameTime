package com.nikoarap.gametime.data.networking.dto

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.domain.model.EventModel
import com.nikoarap.gametime.utils.Constants

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
data class emdto (
    @SerializedName("i")
    val id: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("d")
    val name: String ,
    @SerializedName("tt")
    val startTime: Long
)

fun emdto.toEventModel(): EventModel {
    val competitors: Pair<String, String> = unpackCompetitors(name)
    val eventModel = EventModel()
    eventModel.id = id
    eventModel.sportId = sportId
    eventModel.competitorLeft = competitors.first
    eventModel.competitorRight = competitors.second
    eventModel.startTime = startTime
    return eventModel
}

private fun unpackCompetitors(name: String): Pair<String, String> {
    if (name.contains(Constants.SPACER_SCORE_SPACER_REGEX)) {
        val parts = name.split(Constants.SPACER_SCORE_SPACER_REGEX)
        if (parts.size == 2) {
            return Pair(parts[0], parts[1])
        }
    } else if (name.contains(Constants.SCORE_REGEX)) {
        val parts = name.split(Constants.SCORE_REGEX)
        if (parts.size == 2) {
            return Pair(parts[0], parts[1])
        }
    }
    return Pair(Constants.EMPTY_STRING, Constants.EMPTY_STRING)
}