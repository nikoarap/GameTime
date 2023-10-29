package com.nikoarap.gametime.networking.transformers

import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import com.nikoarap.gametime.utils.DateUtils

/**
 * A transformer for converting a SportModelDTO to a SportModel.
 */
class SportModelTransformer: BaseTransformer<SportModelDTO, SportModel> {

    private val eventModelTransformer = EventModelTransformer()

    /**
     * Transforms a SportModelDTO into a SportModel.
     *
     * @param dto       The SportModelDTO to transform.
     * @return          The resulting SportModel.
     */
    override fun fromDTO(dto: SportModelDTO): SportModel {
        val sportModel = SportModel()
        sportModel.id = dto.id
        sportModel.name = dto.name

        //disregard events that have started more than a day ago
        for (activeEvent in dto.activeEvents) {
            if (DateUtils.isDurationLessThanDay(activeEvent.startTime)) {
                sportModel.activeEvents.add(eventModelTransformer.fromDTO(activeEvent))
            }
        }
        return sportModel
    }
}