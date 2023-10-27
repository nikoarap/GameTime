package com.nikoarap.gametime.networking.apiServices

import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofitInstance: SportsAPIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SportsAPIService::class.java)
    }
}