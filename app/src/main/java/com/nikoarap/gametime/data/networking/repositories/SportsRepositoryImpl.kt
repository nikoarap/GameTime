package com.nikoarap.gametime.data.networking.repositories

import com.nikoarap.gametime.data.networking.apiServices.SportsAPIService
import com.nikoarap.gametime.data.networking.dto.SportModelDto
import com.nikoarap.gametime.domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val api: SportsAPIService
): SportsRepository {

    override suspend fun getSports(): List<SportModelDto> {
        return api.getSportModels()
    }
}