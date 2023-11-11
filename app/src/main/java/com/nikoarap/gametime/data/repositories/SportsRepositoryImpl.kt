package com.nikoarap.gametime.data.repositories

import com.nikoarap.gametime.data.api.SportsApi
import com.nikoarap.gametime.data.dtos.SportModelDto
import com.nikoarap.gametime.domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val api: SportsApi
): SportsRepository {

    override suspend fun getSports(): List<SportModelDto> {
        return api.getSportModels()
    }
}