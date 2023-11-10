package com.nikoarap.gametime.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.MINUTES_IN_HOUR
import com.nikoarap.gametime.utils.Constants.ONE_SECOND_DELAY
import com.nikoarap.gametime.utils.Constants.SECONDS_IN_DAY
import com.nikoarap.gametime.utils.Constants.SECONDS_IN_HOUR
import com.nikoarap.gametime.utils.Constants.SECONDS_IN_MINUTE
import com.nikoarap.gametime.utils.Constants.VALUE_ZERO
import kotlinx.coroutines.delay

/**
 * A composable function that displays a countdown timer for an event. This composable takes the
 * time remaining until an event starts in milliseconds and shows a timer that counts down to
 * the event's start time.
 *
 * The countdown timer calculates the days, hours, minutes, and seconds remaining until the event,
 * and displays them in a formatted string like "Xd, Xh, Xm, Xs" for days, hours, minutes, and
 * seconds, respectively. When the event has started, it displays "Event Started."
 *
 * @param timeUntilEventStartInMs The time remaining until the event starts in milliseconds.
 */
@Composable
fun CountdownTimer(timeUntilEventStartInMs: Long) {
    var timeLeft by remember { mutableLongStateOf(timeUntilEventStartInMs) }

        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > VALUE_ZERO) {
                delay(ONE_SECOND_DELAY)
                timeLeft -= MILLIS_IN_SECOND
            }
        }

    val seconds = (timeLeft / MILLIS_IN_SECOND).toInt()
    val daysLeft = seconds / SECONDS_IN_DAY
    val hoursLeft = (seconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR
    val minutesLeft = (seconds % SECONDS_IN_HOUR) / MINUTES_IN_HOUR
    val secondsLeft = seconds % SECONDS_IN_MINUTE

    val countdownValue = if (timeLeft > VALUE_ZERO) {
        val daysString = if (daysLeft > VALUE_ZERO) "${daysLeft}d, " else EMPTY_STRING
        val hoursString = if (hoursLeft > VALUE_ZERO) "${hoursLeft}h, " else EMPTY_STRING
        val minutesString = if (minutesLeft > VALUE_ZERO) "${minutesLeft}m, " else EMPTY_STRING
        String.format("$daysString$hoursString$minutesString%02ds", secondsLeft)
    } else {
        LocalContext.current.resources.getString(R.string.event_started)
    }

    Box(
        modifier = Modifier
            .border(border = BorderStroke(dimensionResource(id = R.dimen.dp_1), if (timeLeft > VALUE_ZERO) colorResource(id = R.color.primary) else Color.Transparent), shape = RectangleShape)
            .background(color = colorResource(id = R.color.surface)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dp_4)),
            text = countdownValue,
            style = MaterialTheme.typography.bodySmall,
            color = if (timeLeft > VALUE_ZERO) colorResource(id = R.color.secondary) else colorResource(id = R.color.tertiary),
        )
    }
}