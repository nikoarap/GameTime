package com.nikoarap.gametime.view.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.EVENT_ITEM_LAYOUT_WEIGHT
import com.nikoarap.gametime.utils.Constants.Companion.FLOAT_DEGREES_0
import com.nikoarap.gametime.utils.Constants.Companion.FLOAT_DEGREES_180
import com.nikoarap.gametime.utils.Constants.Companion.ICON
import com.nikoarap.gametime.utils.Constants.Companion.MAX_EVENTS_PER_ROW
import com.nikoarap.gametime.utils.Constants.Companion.NO_EVENTS_PLANNED
import com.nikoarap.gametime.utils.Constants.Companion.SECTION_COLUMN_WEIGHT
import com.nikoarap.gametime.utils.ImageUtils
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_18
import com.nikoarap.gametime.view.themes.dp_24
import com.nikoarap.gametime.view.themes.dp_4
import com.nikoarap.gametime.view.themes.dp_8
import com.nikoarap.gametime.view.themes.onSecondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.viewmodels.MainViewModel

/**
 * A composable function to load a sport section.
 *
 * @param viewModel      The view model for the main view.
 * @param sport          The sport model to display in the section.
 */
@Composable
fun LoadSportSection(
    viewModel: MainViewModel,
    sport: SportModel
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) FLOAT_DEGREES_180 else FLOAT_DEGREES_0, label = EMPTY_STRING)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.clickable {
                expandedState = !expandedState
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .padding(start = dp_16)
                    .size(dp_18),
                painter = painterResource(ImageUtils.getPainterResForSport(sport.name)),
                contentDescription = ICON
            )
            Text(
                text = sport.name,
                color = onSecondary,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = dp_8, vertical = dp_8),
                style =  MaterialTheme.typography.titleMedium
            )
            Column(
                Modifier
                    .wrapContentHeight()
                    .weight(SECTION_COLUMN_WEIGHT, true),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SwitchButton(viewModel, sport)
                    Spacer(Modifier.width(dp_16))
                    Icon(
                        modifier = Modifier
                            .size(dp_24)
                            .rotate(rotationState),
                        painter = painterResource(R.drawable.ic_caret_down),
                        contentDescription = ICON,
                        tint = onSecondary
                    )
                    Spacer(Modifier.width(dp_8))
                }
            }
        }
    }
    if (expandedState) {
        LoadEventsInSportSection(viewModel, sport.activeEvents)
    } else {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = surface,
            thickness = dp_16
        )
    }
}


/**
 * A composable function to load events in a sport section.
 *
 * @param viewModel         The view model for the main view.
 * @param sportEvents       The list of sport events to display.
 */
@Composable
private fun LoadEventsInSportSection(
    viewModel: MainViewModel,
    sportEvents: List<EventModel>
) {
    if (sportEvents.isNotEmpty()) {
        LoadEvents(viewModel, sportEvents)
    } else {
        LoadNoResultsView(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(dp_16)
                .background(color = surface),
            noResultsText = NO_EVENTS_PLANNED
        )
    }
}


/**
 * A composable function to load events in a sport section.
 *
 * @param viewModel         The view model for the main view.
 * @param sportEvents       The list of sport events to display.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LoadEvents(
    viewModel: MainViewModel,
    sportEvents: List<EventModel>
) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = surface)
            .padding(dp_16)
    ) {
        //using flow layout here instead of a Lazy Grid, so that the layout computation (composition) becomes smoother and without any nested hierarchies
        // such as lazy grid inside of a lazy list or vertically scrollable column
        androidx.compose.foundation.layout.FlowRow(
            modifier = Modifier.background(color = surface),
            horizontalArrangement = Arrangement.spacedBy(dp_4),
            verticalArrangement = Arrangement.spacedBy(dp_4),
            maxItemsInEachRow = MAX_EVENTS_PER_ROW
        ) {
            for (sportEvent in sportEvents) {
                LoadSportEvent(
                    modifier = Modifier
                        .background(color = surface)
                        .padding(dp_4)
                        .wrapContentHeight()
                        .weight(EVENT_ITEM_LAYOUT_WEIGHT),
                    viewModel,
                    sportEvent
                )
            }

        }
    }
}