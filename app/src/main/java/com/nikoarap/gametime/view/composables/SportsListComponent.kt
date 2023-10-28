package com.nikoarap.gametime.view.composables

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.view.themes.tertiary

@Composable
fun LoadSportList(
    sports: List<SportModel>,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    paddingValues: PaddingValues
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surface)
            .padding(paddingValues)
    ) {

    }


}