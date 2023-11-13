package com.nikoarap.gametime.feature_sports.domain.models

import androidx.room.PrimaryKey
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING

data class Sport(
    @PrimaryKey
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val activeEvents: List<Event> = emptyList(),
    val isFavourite: Boolean = false
)





