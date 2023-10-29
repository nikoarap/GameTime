package com.nikoarap.gametime.networking.apiServices

import com.nikoarap.gametime.networking.DTOs.SportModelListDTO
import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.Response
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
    suspend fun getSportModels(): Response<SportModelListDTO>
}