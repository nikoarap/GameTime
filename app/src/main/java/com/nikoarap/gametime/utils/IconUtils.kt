package com.nikoarap.gametime.utils

import com.nikoarap.gametime.enums.SportTypeEnum

class IconUtils {

    companion object {

        fun getPainterResForSport(sportName: String): Int {
            val sportNameToPainterResMap = SportTypeEnum.values().associateBy { it.sportName }
            return sportNameToPainterResMap[sportName]?.painterRes ?: 0
        }
    }

}