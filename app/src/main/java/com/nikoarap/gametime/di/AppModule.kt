package com.nikoarap.gametime.di

import com.nikoarap.gametime.data.networking.apiServices.SportsApi
import com.nikoarap.gametime.data.networking.repositories.SportsRepositoryImpl
import com.nikoarap.gametime.domain.repository.SportsRepository
import com.nikoarap.gametime.utils.Constants.API_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //SingletonComponent makes all the dependencies inside this module live as long as the application does and makes sure that we have only a single instance throughout the lifetime of the application
object AppModule {

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