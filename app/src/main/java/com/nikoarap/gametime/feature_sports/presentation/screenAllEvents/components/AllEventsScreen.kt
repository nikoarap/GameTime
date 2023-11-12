package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoarap.gametime.R
import com.nikoarap.gametime.feature_sports.presentation.common.LoadNoResultsView
import com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.viewmodel.SportListViewModel


/**
 * Loads a Screen containing all sport events. Loads sport categories/sections with their corresponding events.
 * @param viewModel     SportListViewModel to get the state
 */
@Composable
fun AllEventsScreen(
    viewModel: SportListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.surface))
        ) {
            for (sport in state.sports) {
                LoadSportSection(sport)
            }
        }

        if(state.error.isNotBlank()) {
            LoadNoResultsView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.surface)),
                imageSizeDp = dimensionResource(id = R.dimen.dp_180),
                noResultsText = LocalContext.current.resources.getString(R.string.no_records_synced_yet)
            )
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}