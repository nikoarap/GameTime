package com.nikoarap.gametime.utils

import com.nikoarap.gametime.data.dtos.SportModelDto

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
        fun mergeSportModels(sportModelList: List<SportModelDto>):  List<SportModelDto> {
            val mergedSportModelList = sportModelList.groupBy { it.id }
                .map { (_, models) ->
                    val id = models.first().id
                    val name = models.first().name
                    val activeEvents = models.flatMap { it.activeEvents }
                    SportModelDto(id, name, activeEvents)
                }
            return mergedSportModelList
        }
    }

}