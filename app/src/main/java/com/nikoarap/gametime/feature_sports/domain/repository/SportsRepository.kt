package com.nikoarap.gametime.feature_sports.domain.repository

import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.utils.DataState
import kotlinx.coroutines.flow.Flow

interface SportsRepository {
    fun getAllSports(): Flow<DataState<List<Sport>>>
}