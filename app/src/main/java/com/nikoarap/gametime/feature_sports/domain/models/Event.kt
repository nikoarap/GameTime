package com.nikoarap.gametime.feature_sports.domain.models

import androidx.room.PrimaryKey
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.VALUE_ZERO_LONG

data class Event(
    @PrimaryKey
    val id: String = EMPTY_STRING,
    val sportId: String = EMPTY_STRING,
    val competitorLeft: String = EMPTY_STRING,
    val competitorRight: String = EMPTY_STRING,
    val startTime: Long = VALUE_ZERO_LONG,
    val isFavourite: Boolean = false
)

