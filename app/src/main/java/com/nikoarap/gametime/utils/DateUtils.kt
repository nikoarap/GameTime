package com.nikoarap.gametime.utils

import com.nikoarap.gametime.utils.Constants.Companion.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.Companion.SECONDS_IN_DAY

/**
 * A utility class for date-related operations.
 */
class DateUtils {

    companion object {
        /**
         * Checks if the duration (in seconds) is less than a day.
         *
         * @param timestampInSeconds        The timestamp in seconds to compare.
         * @return                          True if the duration is less than a day, otherwise false.
         */
        fun isDurationLessThanDay(timestampInSeconds: Long):Boolean {
            val duration =  (System.currentTimeMillis() / MILLIS_IN_SECOND) - timestampInSeconds
            return duration <= SECONDS_IN_DAY
        }
    }

}