package com.nikoarap.gametime.utils

import android.annotation.SuppressLint
import com.nikoarap.gametime.utils.Constants.Companion.TIMER_SDF_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class DateUtils {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun convertTimestampToHHMMSS(timestamp: Long): String {
            val dateFormat = SimpleDateFormat(TIMER_SDF_FORMAT)
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(Date(timestamp))
        }
    }
}