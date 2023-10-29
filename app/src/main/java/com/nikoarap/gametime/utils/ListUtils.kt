package com.nikoarap.gametime.utils

import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelListDTO

class ListUtils {

    companion object {

        fun mergeSportModels(sportModelList: SportModelListDTO): SportModelListDTO {
            val mergedSportModelList = sportModelList.sportModelDTOs.groupBy { it.id }
                .map { (_, models) ->
                    val id = models.first().id
                    val name = models.first().name
                    val activeEvents = models.flatMap { it.activeEvents }
                    SportModelDTO(id, name, activeEvents)
                }
            return SportModelListDTO(mergedSportModelList)
        }
    }

}