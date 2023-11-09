package com.nikoarap.gametime.domain.repository

import com.nikoarap.gametime.data.networking.dto.SportModelDto

interface SportsRepository {

    suspend fun getSports(): List<SportModelDto>

}