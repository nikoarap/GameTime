package com.nikoarap.gametime.feature_sports.presentation.screenFavouriteEvents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoarap.gametime.R
import com.nikoarap.gametime.feature_sports.presentation.common.LoadNoResultsView
import com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.components.LoadSportSection
import com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.viewmodel.SportListViewModel

@Composable
fun FavouriteEventsScreen(
    viewModel: SportListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.surface))
        ) {
            for (sport in state.value.sports) {
                LoadSportSection(sport)
            }
        }

        if(state.value.error.isNotBlank()) {
            LoadNoResultsView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.surface)),
                imageSizeDp = dimensionResource(id = R.dimen.dp_180),
                noResultsText = stringResource(R.string.no_records_synced_yet)
            )
        }

        if(state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}