package com.nikoarap.gametime.feature_sports.data.repository

import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockSportsRepository: SportsRepository {

    private val sports = mutableListOf<Sport>()

    override fun getAllSports(): Flow<DataState<List<Sport>>> {
        return flow {
            emit(DataState.Loading(sports))
            if (sports.isNotEmpty()) {
                emit(DataState.Success(sports))
            } else {
                emit(DataState.Error("Sport List is empty"))
            }
        }
    }

    fun populateSportsFromMockDB(sportsFromMockDB: List<Sport>) {
        sports.addAll(sportsFromMockDB)
    }
}