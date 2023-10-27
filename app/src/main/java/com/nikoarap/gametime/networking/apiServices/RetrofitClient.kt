package com.nikoarap.gametime.networking.apiServices

import com.google.gson.GsonBuilder
import com.nikoarap.gametime.networking.transforming.DTOs.EventModelDTO
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelListDTO
import com.nikoarap.gametime.networking.transforming.deserializers.EventModelDeserializer
import com.nikoarap.gametime.networking.transforming.deserializers.SportModelDeserializer
import com.nikoarap.gametime.utils.Constants.Companion.API_ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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