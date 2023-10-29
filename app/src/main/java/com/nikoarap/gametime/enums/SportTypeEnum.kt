package com.nikoarap.gametime.enums

import com.nikoarap.gametime.utils.Constants.Companion.BASEBALL
import com.nikoarap.gametime.utils.Constants.Companion.BASKETBALL
import com.nikoarap.gametime.utils.Constants.Companion.BEACH_VOLLEYBALL
import com.nikoarap.gametime.utils.Constants.Companion.DARTS
import com.nikoarap.gametime.utils.Constants.Companion.ESPORTS
import com.nikoarap.gametime.utils.Constants.Companion.HANDBALL
import com.nikoarap.gametime.utils.Constants.Companion.SOCCER
import com.nikoarap.gametime.utils.Constants.Companion.TABLE_TENNIS
import com.nikoarap.gametime.utils.Constants.Companion.TENNIS

enum class SportTypeEnum(
    var sportType: String,var iconResName: String
) {
    TYPE_SOCCER(
        sportType = SOCCER,
        iconResName = ""
    ),
    TYPE_BASKETBALL(
    sportType = BASKETBALL,
    iconResName = ""
    ),
    TYPE_TENNIS(
        sportType = TENNIS,
        iconResName = ""
    ),
    TYPE_TABLE_TENNIS(
        sportType = TABLE_TENNIS,
        iconResName = ""
    ),
    TYPE_ESPORTS(
        sportType = ESPORTS,
        iconResName = ""
    ),
    TYPE_BASEBALL(
        sportType = BASEBALL,
        iconResName = ""
    ),
    TYPE_HANDBALL(
        sportType = HANDBALL,
        iconResName = ""
    ),
    TYPE_BEACH_VOLLEYBALL(
        sportType = BEACH_VOLLEYBALL,
        iconResName = ""
    ),
    TYPE_DARTS(
        sportType = DARTS,
        iconResName = ""
    ),
}