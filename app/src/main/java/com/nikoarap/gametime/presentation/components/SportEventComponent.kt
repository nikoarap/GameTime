package com.nikoarap.gametime.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.nikoarap.gametime.R
import com.nikoarap.gametime.domain.model.EventModel
import com.nikoarap.gametime.presentation.ui.dp_24
import com.nikoarap.gametime.presentation.ui.dp_4
import com.nikoarap.gametime.presentation.ui.onSurface
import com.nikoarap.gametime.presentation.ui.secondary
import com.nikoarap.gametime.presentation.ui.sp_10
import com.nikoarap.gametime.presentation.ui.tertiary
import com.nikoarap.gametime.utils.Constants.DESCRIPTION_ICON
import com.nikoarap.gametime.utils.Constants.MILLIS_IN_SECOND
import com.nikoarap.gametime.viewmodels.MainViewModel

/**
 * A composable function to display a sport event.
 *
 * @param modifier      The modifier for this composable.
 * @param viewModel     The view model for the main view.
 * @param event         The event model to display.
 */
@Composable
fun LoadSportEvent(
    modifier: Modifier,
    viewModel: MainViewModel,
    event: EventModel
) {
    var isEventFavourite by remember { mutableStateOf(event.isFavourite) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //set the duration in ms
        CountdownTimer((event.startTime * MILLIS_IN_SECOND) - System.currentTimeMillis())
        Icon(
            painter = if (isEventFavourite) rememberVectorPainter(Icons.Filled.Star) else painterResource(id = R.drawable.ic_star_hollow),
            modifier = Modifier
                .padding(dp_4)
                .size(dp_24)
                .clickable {
                    isEventFavourite = !isEventFavourite
                    viewModel.onEventFavouriteChecked(event, isEventFavourite)
                },

            contentDescription = DESCRIPTION_ICON,
            tint = onSurface
        )
        Text(
            text = event.competitorLeft,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
        Text(
            text = LocalContext.current.resources.getString(R.string.vs),
            fontSize = sp_10,
            color = tertiary,
            modifier = Modifier
        )
        Text(
            text = event.competitorRight,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}