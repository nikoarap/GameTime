package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.utils.Constants
import com.nikoarap.gametime.utils.Constants.Companion.MILLIS_IN_SECOND
import com.nikoarap.gametime.utils.Constants.Companion.VS_VALUE
import com.nikoarap.gametime.view.themes.dp_24
import com.nikoarap.gametime.view.themes.dp_4
import com.nikoarap.gametime.view.themes.dp_8
import com.nikoarap.gametime.view.themes.onSurface
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.sp_10
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.view.themes.tertiary

@Composable
fun LoadSportEvent(
    event: EventModel
) {
    var isEventFavourite by remember { mutableStateOf(event.isFavourite) }

    Column(
        modifier = Modifier
            .background(color = surface)
            .wrapContentSize()
            .padding(dp_8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CountdownTimer((System.currentTimeMillis() / MILLIS_IN_SECOND) - event.startTime)
        Icon(
            painter = if (isEventFavourite) rememberVectorPainter(Icons.Filled.Star) else painterResource(id = R.drawable.ic_star_hollow),
            modifier = Modifier
                .padding(dp_4)
                .size(dp_24)
                .clickable {
                    isEventFavourite = !isEventFavourite
                    //realm logic here
                },

            contentDescription = Constants.ICON,
            tint = onSurface
        )
        Text(
            text = event.competitorLeft,
            style = MaterialTheme.typography.labelMedium,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
        Text(
            text = VS_VALUE,
            fontSize = sp_10,
            color = tertiary,
            modifier = Modifier
        )
        Text(
            text = event.competitorRight,
            style = MaterialTheme.typography.labelMedium,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}