package com.nikoarap.gametime.utils

import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelListDTO

class ListUtils {

    companion object {

        fun mergeSportModels(sportModelList: SportModelListDTO): SportModelListDTO {
            val mergedSportModelList = sportModelList.sportModelDTOs.groupBy { it.id }
                .map { (id, models) ->
                    SportModelDTO(
                        id = id,
                        activeEvents = models.flatMap { it.activeEvents }
                    )
                }
            return SportModelListDTO(mergedSportModelList)
        }
    }

}