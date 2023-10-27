package com.nikoarap.gametime.networking.apiServices

import com.nikoarap.gametime.networking.DTOs.SportModelListDTO
import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface SportsAPIService {
    @GET(API_ENDPOINT)
    suspend fun getSportModels(): Response<SportModelListDTO>
}