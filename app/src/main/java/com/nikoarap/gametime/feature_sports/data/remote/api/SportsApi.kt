package com.nikoarap.gametime.feature_sports.data.remote.api

import com.nikoarap.gametime.feature_sports.data.remote.dtos.SportModelDto
import com.nikoarap.gametime.utils.Constants.API_ENDPOINT
import retrofit2.http.GET

/**
 * Interface for making API requests to retrieve Sport Models.
 */
interface SportsApi {

    /**
     * Fetches a list of Sport Model dtos from the API.
     * @return A response containing a list of Sport Model dtos.
     */
    @GET(API_ENDPOINT)
    suspend fun getSportModels(): List<SportModelDto>
}