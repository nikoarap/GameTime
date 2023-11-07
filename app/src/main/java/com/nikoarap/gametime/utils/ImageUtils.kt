package com.nikoarap.gametime.utils

import com.nikoarap.gametime.R
import com.nikoarap.gametime.enums.SportTypeEnum

/**
 * A utility class for images.
 */
class ImageUtils {

    companion object {

        /**
         * Gets the painter resource (Drawable resource ID) for a sport based on its name.
         *
         * @param sportName         The name of the sport.
         * @return                  The painter resource ID for the corresponding sport, or a fallback resource if not found.
         */
        fun getPainterResForSport(sportName: String): Int {
            val sportNameToPainterResMap = SportTypeEnum.values().associateBy { it.sportName }
            return sportNameToPainterResMap[sportName]?.painterRes ?: R.drawable.trophy_icon
        }
    }

}