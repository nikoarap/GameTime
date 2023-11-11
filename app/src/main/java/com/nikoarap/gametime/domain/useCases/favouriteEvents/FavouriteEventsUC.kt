package com.nikoarap.gametime.domain.useCases.favouriteEvents

import com.nikoarap.gametime.data.networking.dto.toSportModel
import com.nikoarap.gametime.domain.models.SportModel
import com.nikoarap.gametime.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.ResourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FavouriteEventsUC @Inject constructor(
    private val repository: SportsRepository
) {

    //by overriding the invoke here, we can call this use case as if it was a function
    operator fun invoke(): Flow<ResourceManager<List<SportModel>>> = flow {
        try {
            emit(ResourceManager.Loading<List<SportModel>>())
            val sportDtoList = repository.getSports().map { it.toSportModel() }
            emit(ResourceManager.Success<List<SportModel>>(sportDtoList))
        } catch(e: HttpException) {
            emit(ResourceManager.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(ResourceManager.Error("Could not reach server, check your internet connection"))
        }
    }
}