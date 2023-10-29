package com.nikoarap.gametime.networking.DTOs

/**
 * Data class representing a list of sports.
 *
 * @property sportModelDTOs A list of sports as SportModelDTO objects.
 */
data class SportModelListDTO(
    var sportModelDTOs: List<SportModelDTO> = arrayListOf()
)

