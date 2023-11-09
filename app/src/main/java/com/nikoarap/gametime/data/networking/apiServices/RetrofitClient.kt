package com.nikoarap.gametime.data.networking.apiServices

import com.google.gson.GsonBuilder
import com.nikoarap.gametime.data.networking.apiServices.RetrofitClient.retrofitInstance
import com.nikoarap.gametime.data.networking.apiServices.RetrofitClient.sportModelGson
import com.nikoarap.gametime.data.networking.deserializers.EventModelDeserializer
import com.nikoarap.gametime.data.networking.deserializers.SportModelDeserializer
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
        .registerTypeAdapter(EventModelDto::class.java, EventModelDeserializer())
        .registerTypeAdapter(SportModelListDto::class.java, SportModelDeserializer())
        .create()

    val retrofitInstance: SportsAPIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(sportModelGson))
            .build()

        retrofit.create(SportsAPIService::class.java)
    }
}