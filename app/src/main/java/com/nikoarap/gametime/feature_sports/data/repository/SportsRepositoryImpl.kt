package com.nikoarap.gametime.feature_sports.data.repository

import com.nikoarap.gametime.feature_sports.data.cache.db.SportsDao
import com.nikoarap.gametime.feature_sports.data.remote.api.SportsApi
import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.DownloadResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val api: SportsApi,
    private val dao: SportsDao
): SportsRepository {

    override suspend fun getAllSports(): Flow<DownloadResult<List<Sport>>> {
        TODO("Not yet implemented")
    }

}