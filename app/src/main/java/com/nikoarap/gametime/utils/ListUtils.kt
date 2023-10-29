package com.nikoarap.gametime.utils

import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelListDTO

/**
 * A utility class for list manipulation.
 */
class ListUtils {

    companion object {

        /**
         * Merges a list of SportModelDTO objects by grouping them by their ID and combining their active events.
         *
         * @param sportModelList        The list of SportModelDTO objects to be merged.
         * @return                      A new SportModelListDTO containing the merged SportModelDTO objects.
         */
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