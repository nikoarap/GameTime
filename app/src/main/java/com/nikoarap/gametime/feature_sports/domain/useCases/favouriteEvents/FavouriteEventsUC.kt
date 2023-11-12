package com.nikoarap.gametime.feature_sports.domain.useCases.favouriteEvents

import com.nikoarap.gametime.feature_sports.domain.models.SportModel
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.DownloadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FavouriteEventsUC @Inject constructor(
    private val repository: SportsRepository
) {

    //by overriding the invoke here, we can call this use case as if it was a function
    operator fun invoke(): Flow<DownloadResult<List<SportModel>>> = flow {
        try {
            emit(DownloadResult.Loading())
            val sportDtoList = repository.downloadSports().map { it.toSportModel() }
            emit(DownloadResult.Success(sportDtoList))
        } catch(e: HttpException) {
            emit(DownloadResult.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(DownloadResult.Error("Could not reach server, check your internet connection"))
        }
    }
}