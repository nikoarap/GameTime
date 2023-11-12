package com.nikoarap.gametime.feature_sports.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.nikoarap.gametime.feature_sports.domain.models.EventModel
import com.nikoarap.gametime.feature_sports.domain.models.SportModel
import com.nikoarap.gametime.utils.Constants.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.SECONDS_IN_DAY
import io.realm.RealmList

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
data class SportModelDto (
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val name: String,
    @SerializedName("e")
    val activeEvents: List<EventModelDto>
)

fun SportModelDto.toSportModel(): SportModel {
    val activeEventsList: RealmList<EventModel> = RealmList()
    val sportModel = SportModel()

    //disregard events that have started more than a day ago
    for (activeEvent in activeEvents) {
        if (activeEvent.startTime.isDurationLessThanDay()) {
            activeEventsList.add(
                EventModelDto(
                    activeEvent.id, activeEvent.sportId, activeEvent.name, activeEvent.startTime
                ).toEventModel()
            )
        }
    }

    sportModel.id = id
    sportModel.name = name
    sportModel.activeEvents = activeEventsList
    return sportModel
}

fun Long.isDurationLessThanDay(): Boolean {
    val duration = (System.currentTimeMillis() / MILLIS_IN_SECOND) - this
    return duration <= SECONDS_IN_DAY
}
