package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface

@Composable
fun LoadMainComponent(
    sports: List<SportModel>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surface),
    ) {
        LoadHeader()
        LoadSportSections(sports)
    }
}


@Composable
fun LoadHeader() {
    TopAppBar(
        title = {
            Text(
                text = LocalContext.current.getString(R.string.app_name),
                color = secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dp_16),
                style =  MaterialTheme.typography.titleMedium
            )
        },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        backgroundColor = primary
    )
}

@Composable
fun LoadSportSections(
    sports: List<SportModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .background(color = surface)
    ) {
        for (sport in sports) {
            LoadSportSection(sport)
        }
    }
}