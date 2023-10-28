package com.nikoarap.gametime.networking.transformers

import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.networking.DTOs.EventModelDTO

class EventModelTransformer: BaseTransformer<EventModelDTO, EventModel> {

    override fun fromDTO(dto: EventModelDTO): EventModel {
        val eventModel = EventModel()
        eventModel.id = dto.id
        eventModel.sportId = dto.sportId
        eventModel.name = dto.name
        eventModel.startTime = dto.startTime
        return eventModel
    }
}