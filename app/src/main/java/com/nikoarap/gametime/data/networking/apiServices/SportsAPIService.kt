package com.nikoarap.gametime.data.networking.apiServices

import com.nikoarap.gametime.data.networking.dto.SportModelDto
import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.http.GET

/**
 * Retrofit service interface for making API requests to retrieve Sport Models.
 */
interface SportsAPIService {

    /**
     * Fetches a list of Sport Models from the API.
     * @return A response containing a list of Sport Models.
     */
    @GET(API_ENDPOINT)
    suspend fun getSportModels(): List<SportModelDto>
}