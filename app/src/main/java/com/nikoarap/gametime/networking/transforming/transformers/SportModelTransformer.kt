package com.nikoarap.gametime.networking.transforming.transformers

import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelDTO

class SportModelTransformer: BaseTransformer<SportModelDTO, SportModel> {

    private val eventModelTransformer = EventModelTransformer()

    override fun fromDTO(dto: SportModelDTO): SportModel {
        val sportModel = SportModel()
        sportModel.id = dto.id
        sportModel.name = dto.name

        for (activeEvent in dto.activeEvents) {
            sportModel.activeEvents.add(eventModelTransformer.fromDTO(activeEvent))
        }
        return sportModel
    }
}