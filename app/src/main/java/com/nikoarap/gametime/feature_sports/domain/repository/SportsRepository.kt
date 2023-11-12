package com.nikoarap.gametime.feature_sports.domain.repository

import com.nikoarap.gametime.feature_sports.domain.models.SportModel
import com.nikoarap.gametime.utils.DownloadResult
import kotlinx.coroutines.flow.Flow

interface SportsRepository {
    suspend fun getAllSports(): Flow<DownloadResult<List<SportModel>>>
}