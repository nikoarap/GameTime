package com.nikoarap.gametime.enums

import androidx.annotation.DrawableRes
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.Companion.BASEBALL
import com.nikoarap.gametime.utils.Constants.Companion.BASKETBALL
import com.nikoarap.gametime.utils.Constants.Companion.BEACH_VOLLEYBALL
import com.nikoarap.gametime.utils.Constants.Companion.DARTS
import com.nikoarap.gametime.utils.Constants.Companion.ESPORTS
import com.nikoarap.gametime.utils.Constants.Companion.HANDBALL
import com.nikoarap.gametime.utils.Constants.Companion.SOCCER
import com.nikoarap.gametime.utils.Constants.Companion.TABLE_TENNIS
import com.nikoarap.gametime.utils.Constants.Companion.TENNIS
import com.nikoarap.gametime.utils.Constants.Companion.VOLLEYBALL

enum class SportTypeEnum(
    var sportName: String,  @DrawableRes painterRes: Int
) {
    TYPE_SOCCER(
        sportName = SOCCER,
        painterRes = R.drawable.soccer_icon
    ),
    TYPE_BASKETBALL(
        sportName = BASKETBALL,
        painterRes = R.drawable.basketball_icon
    ),
    TYPE_TENNIS(
        sportName = TENNIS,
        painterRes = R.drawable.tennis_icon
    ),
    TYPE_TABLE_TENNIS(
        sportName = TABLE_TENNIS,
        painterRes = R.drawable.table_tennis_icon
    ),
    TYPE_ESPORTS(
        sportName = ESPORTS,
        painterRes = R.drawable.esports_icon
    ),
    TYPE_BASEBALL(
        sportName = BASEBALL,
        painterRes = R.drawable.baseball_icon
    ),
    TYPE_HANDBALL(
        sportName = HANDBALL,
        painterRes = R.drawable.handball_icon
    ),
    TYPE_VOLLEYBALL(
        sportName = VOLLEYBALL,
        painterRes = R.drawable.volleyball_icon
    ),
    TYPE_BEACH_VOLLEYBALL(
        sportName = BEACH_VOLLEYBALL,
        painterRes = R.drawable.beach_volleyball_icon
    ),
    TYPE_DARTS(
        sportName = DARTS,
        painterRes = R.drawable.darts_icon
    ),
}