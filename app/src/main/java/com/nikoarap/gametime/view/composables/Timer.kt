package com.nikoarap.gametime.view.composables

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
import androidx.compose.ui.unit.dp
import com.nikoarap.gametime.utils.Constants.Companion.EVENT_STARTED
import com.nikoarap.gametime.utils.Constants.Companion.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.Companion.MINUTES_IN_HOUR
import com.nikoarap.gametime.utils.Constants.Companion.ONE_SECOND_DELAY
import com.nikoarap.gametime.utils.Constants.Companion.SECONDS_IN_DAY
import com.nikoarap.gametime.utils.Constants.Companion.SECONDS_IN_HOUR
import com.nikoarap.gametime.utils.Constants.Companion.SECONDS_IN_MINUTE
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import com.nikoarap.gametime.view.themes.dp_1
import com.nikoarap.gametime.view.themes.dp_4
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.view.themes.tertiary
import kotlinx.coroutines.delay

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

    Box(
        modifier = Modifier
            .border(border = BorderStroke(dp_1, if (timeLeft > VALUE_ZERO) primary else Color.Transparent), shape = RectangleShape)
            .background(color = surface),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(dp_4),
            text = if (timeLeft > VALUE_ZERO) "$daysLeft:$hoursLeft:$minutesLeft:$secondsLeft" else EVENT_STARTED,
            style = MaterialTheme.typography.bodySmall,
            color = if (timeLeft > VALUE_ZERO) secondary else tertiary,
        )
    }
}