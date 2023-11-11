package com.nikoarap.gametime.data.networking.apiServices

import com.nikoarap.gametime.utils.Constants.API_ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and configuring a Retrofit instance to interact with the Sports API.
 * It provides a lazy-initialized Retrofit instance with a custom Gson converter factory.
 */
object RetrofitClient {

    val retrofitInstance: SportsApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SportsApi::class.java)
    }
}