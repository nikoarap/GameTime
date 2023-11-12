package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.state

import com.nikoarap.gametime.feature_sports.domain.models.SportModel
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING

data class SportListState(
    val isLoading: Boolean = false,
    val sports: List<SportModel> = emptyList(),
    val error: String = EMPTY_STRING
)
