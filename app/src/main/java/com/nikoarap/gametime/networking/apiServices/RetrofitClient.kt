package com.nikoarap.gametime.networking.apiServices

import com.google.gson.GsonBuilder
import com.nikoarap.gametime.networking.DTOs.EventModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelListDTO
import com.nikoarap.gametime.networking.deserializers.EventModelDeserializer
import com.nikoarap.gametime.networking.deserializers.SportModelDeserializer
import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and configuring a Retrofit instance to interact with the Sports API.
 * It provides a lazy-initialized Retrofit instance with a custom Gson converter factory.
 *
 * @property sportModelGson         Customized Gson instance with type adapters for deserializing Sport and Event models.
 * @property retrofitInstance       The Retrofit service interface to make API requests.
 */
object RetrofitClient {
    private val sportModelGson = GsonBuilder()
        .registerTypeAdapter(EventModelDTO::class.java, EventModelDeserializer())
        .registerTypeAdapter(SportModelListDTO::class.java, SportModelDeserializer())
        .create()

    val retrofitInstance: SportsAPIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(sportModelGson))
            .build()

        retrofit.create(SportsAPIService::class.java)
    }
}