package com.nikoarap.gametime.networking.transformers

import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import java.util.UUID

class SportModelTransformer: BaseTransformer<SportModelDTO, SportModel> {

    private val eventModelTransformer = EventModelTransformer()

    override fun fromDTO(dto: SportModelDTO): SportModel {
        val sportModel = SportModel()
        sportModel.id = UUID.randomUUID().toString()
        sportModel.sportId = dto.id
        sportModel.name = dto.name

        for (activeEvent in dto.activeEvents) {
            sportModel.activeEvents.add(eventModelTransformer.fromDTO(activeEvent))
        }
        return sportModel
    }
}