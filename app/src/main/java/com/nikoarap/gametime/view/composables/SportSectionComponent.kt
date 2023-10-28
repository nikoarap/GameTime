package com.nikoarap.gametime.view.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.dp
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.FLOAT_DEGREES_0
import com.nikoarap.gametime.utils.Constants.Companion.FLOAT_DEGREES_180
import com.nikoarap.gametime.utils.Constants.Companion.ICON
import com.nikoarap.gametime.utils.Constants.Companion.SECTION_COLUMN_WEIGHT
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_18
import com.nikoarap.gametime.view.themes.dp_24
import com.nikoarap.gametime.view.themes.dp_8
import com.nikoarap.gametime.view.themes.onSecondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.view.themes.tertiary

@Composable
fun LoadSportSection(
    sport: SportModel,
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
            Dot(
                modifier = Modifier.padding(start = dp_16),
                circleSizeDp = dp_18,
                backgroundColor = tertiary
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
                    SwitchButton(isChecked = sport.isFavourite)
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
        LoadEventsInSportSection(sport.activeEvents)
    } else {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = surface,
            thickness = 24.dp
        )
    }
}

@Composable
fun LoadEventsInSportSection(
    sportEvents: List<EventModel>
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = surface)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4), // 4 columns
            verticalArrangement = Arrangement.spacedBy(8.dp), // Adjust vertical spacing as needed
            modifier = Modifier.padding(8.dp) // Adjust padding as needed
        ) {
            items(sportEvents) { item ->
                LoadEventGridItem(item)
            }
        }
    }
}