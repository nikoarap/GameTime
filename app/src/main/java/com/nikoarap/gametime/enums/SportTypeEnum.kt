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
    var sportName: String, var iconResName: String
) {
    TYPE_SOCCER(
        sportName = SOCCER,
        iconResName = ""
    ),
    TYPE_BASKETBALL(
        sportName = BASKETBALL,
        iconResName = ""
    ),
    TYPE_TENNIS(
        sportName = TENNIS,
        iconResName = ""
    ),
    TYPE_TABLE_TENNIS(
        sportName = TABLE_TENNIS,
        iconResName = ""
    ),
    TYPE_ESPORTS(
        sportName = ESPORTS,
        iconResName = ""
    ),
    TYPE_BASEBALL(
        sportName = BASEBALL,
        iconResName = ""
    ),
    TYPE_HANDBALL(
        sportName = HANDBALL,
        iconResName = ""
    ),
    TYPE_BEACH_VOLLEYBALL(
        sportName = BEACH_VOLLEYBALL,
        iconResName = ""
    ),
    TYPE_DARTS(
        sportName = DARTS,
        iconResName = ""
    ),
}