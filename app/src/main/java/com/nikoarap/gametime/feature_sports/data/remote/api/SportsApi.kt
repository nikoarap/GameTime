package com.nikoarap.gametime.feature_sports.data.remote.api

import com.nikoarap.gametime.feature_sports.data.remote.dtos.SportDto
import com.nikoarap.gametime.utils.Constants.API_ENDPOINT
import retrofit2.http.GET

/**
 * Interface for making API requests to retrieve Sports.
 */
interface SportsApi {

    /**
     * Fetches a list of Sport dtos from the API.
     * @return A response containing a list of Sport dtos.
     */
    @GET(API_ENDPOINT)
    suspend fun getSportModels(): List<SportDto>
}