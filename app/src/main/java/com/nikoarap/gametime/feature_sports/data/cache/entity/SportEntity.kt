package com.nikoarap.gametime.feature_sports.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikoarap.gametime.feature_sports.domain.models.Event
import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.utils.Constants
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING

@Entity
data class SportEntity(
    @PrimaryKey
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var activeEvents: List<Event> = emptyList(),
    var isFavourite: Boolean = false

) {
    fun toSport(): Sport {
        return Sport(
            id = id,
            name = name,
            activeEvents = activeEvents.filter { it.startTime.isDurationLessThanDay() },
            isFavourite = isFavourite
        )
    }

    private fun Long.isDurationLessThanDay(): Boolean {
        val duration = (System.currentTimeMillis() / Constants.MILLIS_IN_SECOND) - this
        return duration <= Constants.SECONDS_IN_DAY
    }
}