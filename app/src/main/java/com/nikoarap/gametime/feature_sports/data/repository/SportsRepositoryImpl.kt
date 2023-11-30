package com.nikoarap.gametime.feature_sports.data.repository

import com.nikoarap.gametime.feature_sports.data.cache.db.SportsDao
import com.nikoarap.gametime.feature_sports.data.remote.api.SportsApi
import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val api: SportsApi,
    private val dao: SportsDao
): SportsRepository {

    override fun getAllSports(): Flow<DataState<List<Sport>>> = flow {
        emit(DataState.Loading())

        val sports = dao.getSportEntities().map { it.toSport() }
        emit(DataState.Loading(data = sports))

        if (sports.isEmpty()) {
            try {
                val sportDtos = api.getSportDtos()
                dao.insertSportEntities(sportDtos.map { it.toSportEntity() })
            } catch(e: HttpException) {
                emit(DataState.Error(errorMessage = e.message, data = sports))
            } catch(e: IOException) {
                emit(DataState.Error(errorMessage = e.message, data = sports))
            }

            val newSports = dao.getSportEntities().map { it.toSport() }
            emit(DataState.Success(newSports))
        } else {
            emit(DataState.Success(sports))
        }
    }
}