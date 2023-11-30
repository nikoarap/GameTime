package com.nikoarap.gametime.feature_sports.domain.useCases

import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSports @Inject constructor(
    private val repository: SportsRepository
) {
    //by overriding the invoke here, we can call this use case as if it was a function
    operator fun invoke(): Flow<DataState<List<Sport>>> {
        return repository.getAllSports()
    }
}