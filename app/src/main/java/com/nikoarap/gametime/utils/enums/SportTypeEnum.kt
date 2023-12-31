package com.nikoarap.gametime.utils.enums

import androidx.annotation.DrawableRes
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.BASEBALL
import com.nikoarap.gametime.utils.Constants.BASKETBALL
import com.nikoarap.gametime.utils.Constants.BEACH_VOLLEYBALL
import com.nikoarap.gametime.utils.Constants.DARTS
import com.nikoarap.gametime.utils.Constants.ESPORTS
import com.nikoarap.gametime.utils.Constants.HANDBALL
import com.nikoarap.gametime.utils.Constants.SOCCER
import com.nikoarap.gametime.utils.Constants.TABLE_TENNIS
import com.nikoarap.gametime.utils.Constants.TENNIS
import com.nikoarap.gametime.utils.Constants.VOLLEYBALL

/**
 * An enum representing different sport types with associated icon resources.
 *
 * @property sportName      The name or title of the sport type.
 * @property painterRes     The resource ID of the icon associated with the sport type.
 */
enum class SportTypeEnum(
    var sportName: String,
    @DrawableRes var painterRes: Int
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
    )
}




