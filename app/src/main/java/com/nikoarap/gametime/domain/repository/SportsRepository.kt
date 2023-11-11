package com.nikoarap.gametime.domain.repository

import com.nikoarap.gametime.data.dtos.SportModelDto

interface SportsRepository {
    suspend fun getSports(): List<SportModelDto>
}