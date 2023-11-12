package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.nikoarap.gametime.R
import com.nikoarap.gametime.feature_sports.domain.models.EventModel
import com.nikoarap.gametime.feature_sports.presentation.common.CountdownTimer
import com.nikoarap.gametime.utils.Constants.DESCRIPTION_ICON
import com.nikoarap.gametime.utils.Constants.MILLIS_IN_SECOND

/**
 * A composable function to display a sport event.
 *
 * @param modifier          The modifier for this composable.
 * @param viewModel         The view model for the main view.
 * @param event             The event model to display.
 * @param isEventFavourite  determines if the event has been favourited by the user or not.
 */
@Composable
fun LoadSportEvent(
    modifier: Modifier,
    event: EventModel,
    isEventFavourite: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //set the duration in ms
        CountdownTimer((event.startTime * MILLIS_IN_SECOND) - System.currentTimeMillis())
        Icon(
            painter = if (isEventFavourite) rememberVectorPainter(Icons.Filled.Star) else painterResource(id = R.drawable.ic_star_hollow),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dp_4))
                .size(dimensionResource(id = R.dimen.dp_24))
                .clickable {
//                    viewModel.onEventFavouriteChecked(event, isEventFavourite)
                },

            contentDescription = DESCRIPTION_ICON,
            tint = colorResource(id = R.color.onSurface)
        )
        Text(
            text = event.competitorLeft,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.secondary),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
        Text(
            text = LocalContext.current.resources.getString(R.string.vs),
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = R.color.tertiary),
            modifier = Modifier
        )
        Text(
            text = event.competitorRight,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.secondary),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}