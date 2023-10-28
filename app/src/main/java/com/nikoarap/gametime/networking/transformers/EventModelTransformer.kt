package com.nikoarap.gametime.networking.transformers

import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.networking.DTOs.EventModelDTO
import com.nikoarap.gametime.utils.Constants.Companion.SCORE_REGEX
import com.nikoarap.gametime.utils.Constants.Companion.SPACER_SCORE_SPACER_REGEX

class EventModelTransformer: BaseTransformer<EventModelDTO, EventModel> {

    override fun fromDTO(dto: EventModelDTO): EventModel {
        val eventModel = EventModel()
        eventModel.id = dto.id
        eventModel.sportId = dto.sportId

        val competitorArray: Array<String>
        if (dto.name.contains(SPACER_SCORE_SPACER_REGEX)) {
            competitorArray = dto.name.split(SPACER_SCORE_SPACER_REGEX).toTypedArray()
            eventModel.competitorLeft = competitorArray[0]
            eventModel.competitorRight = competitorArray[1]
        } else if (dto.name.contains(SCORE_REGEX)) {
            competitorArray = dto.name.split(SCORE_REGEX).toTypedArray()
            eventModel.competitorLeft = competitorArray[0]
            eventModel.competitorRight = competitorArray[1]
        }

        eventModel.startTime = dto.startTime
        return eventModel
    }
}