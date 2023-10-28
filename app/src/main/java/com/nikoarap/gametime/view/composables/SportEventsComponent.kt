package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.view.themes.surface

@Composable
fun LoadSportEventsInSection(
    sportEvents: List<EventModel>
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = surface)
    ) {

    }

}