package com.nikoarap.gametime.feature_sports.di

import com.nikoarap.gametime.feature_sports.data.remote.api.SportsApi
import com.nikoarap.gametime.feature_sports.data.repository.SportsRepositoryImpl
import com.nikoarap.gametime.feature_sports.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.Constants.API_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//SingletonComponent makes all the dependencies inside this module live as long as the application does
//and makes sure that we have only a single instance throughout the lifetime of the application
@InstallIn(SingletonComponent::class)
object SportsModule {

    @Provides
    @Singleton
    fun provideSportsApi(): SportsApi {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSportsRepository(api: SportsApi): SportsRepository {
        return SportsRepositoryImpl(api)
    }


}