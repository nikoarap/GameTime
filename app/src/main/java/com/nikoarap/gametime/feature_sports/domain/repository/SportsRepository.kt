package com.nikoarap.gametime.feature_sports.domain.repository

import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.utils.DownloadResult
import kotlinx.coroutines.flow.Flow

interface SportsRepository {
    suspend fun getAllSports(): Flow<DownloadResult<List<Sport>>>
}