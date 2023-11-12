package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.components

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.nikoarap.gametime.R
import com.nikoarap.gametime.feature_sports.domain.models.EventModel
import com.nikoarap.gametime.feature_sports.domain.models.SportModel
import com.nikoarap.gametime.feature_sports.presentation.common.LoadNoResultsView
import com.nikoarap.gametime.utils.Constants.DESCRIPTION_ICON
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.EVENT_ITEM_LAYOUT_WEIGHT
import com.nikoarap.gametime.utils.Constants.FLOAT_DEGREES_0
import com.nikoarap.gametime.utils.Constants.FLOAT_DEGREES_180
import com.nikoarap.gametime.utils.Constants.MAX_EVENTS_PER_ROW
import com.nikoarap.gametime.utils.Constants.SECTION_COLUMN_WEIGHT
import com.nikoarap.gametime.utils.enums.SportTypeEnum

/**
 * A composable function to load a sport section.
 *
 * @param sport          The sport model to display in the section.
 */
@Composable
fun LoadSportSection(
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
                    .padding(start = dimensionResource(id = R.dimen.dp_16))
                    .size(dimensionResource(id = R.dimen.dp_18)),
                painter = painterResource(sport.name.getPainterResForSport()),
                contentDescription = DESCRIPTION_ICON
            )
            Text(
                text = sport.name,
                color = colorResource(id = R.color.onSecondary),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.dp_8), vertical = dimensionResource(id = R.dimen.dp_8)),
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
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_24))
                            .rotate(rotationState),
                        painter = painterResource(R.drawable.ic_caret_down),
                        contentDescription = DESCRIPTION_ICON,
                        tint = colorResource(id = R.color.onSecondary)
                    )
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.dp_8)))
                }
            }
        }
    }
    if (expandedState) {
        LoadEventsInSportSection(sport.activeEvents)
    } else {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = colorResource(id = R.color.surface),
            thickness = dimensionResource(id = R.dimen.dp_8)
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
private fun LoadEventsInSportSection(
    sportEvents: List<EventModel>
) {
    if (sportEvents.isNotEmpty()) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.surface))
                .padding(dimensionResource(id = R.dimen.dp_16))
        ) {
            //using flow layout here instead of a Lazy Grid, so that the layout computation (composition) becomes smoother and without any nested hierarchies
            // such as lazy grid inside of a lazy list or vertically scrollable column
            androidx.compose.foundation.layout.FlowRow(
                modifier = Modifier.background(color = colorResource(id = R.color.surface)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_4)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_4)),
                maxItemsInEachRow = MAX_EVENTS_PER_ROW
            ) {
                for (sportEvent in sportEvents) {
                    val isEventFavourite by remember { mutableStateOf(sportEvent.isFavourite) }
                    LoadSportEvent(
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.surface))
                            .padding(dimensionResource(id = R.dimen.dp_4))
                            .wrapContentHeight()
                            .weight(EVENT_ITEM_LAYOUT_WEIGHT),
                        sportEvent,
                        isEventFavourite
                    )
                }

            }
        }
    } else {
        LoadNoResultsView(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.surface)),
            imageSizeDp = dimensionResource(id = R.dimen.dp_120),
            noResultsText = LocalContext.current.resources.getString(R.string.no_events_planned)
        )
    }
}

fun String.getPainterResForSport(): Int {
    val sportNameToPainterResMap = SportTypeEnum.values().associateBy { it.sportName }
    return sportNameToPainterResMap[this]?.painterRes ?: R.drawable.trophy_icon
}