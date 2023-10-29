package com.nikoarap.gametime.utils

import com.nikoarap.gametime.utils.Constants.Companion.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.Companion.SECONDS_IN_DAY

class DateUtils {

    companion object {

        fun isDurationLessThanDay(timestampInSeconds: Long):Boolean {
            val duration =  (System.currentTimeMillis() / MILLIS_IN_SECOND) - timestampInSeconds
            return duration <= SECONDS_IN_DAY
        }
    }
}