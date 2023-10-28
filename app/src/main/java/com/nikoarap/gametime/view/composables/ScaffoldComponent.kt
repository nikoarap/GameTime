package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.DP_16
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadTopBarWithContent(
    sports: List<SportModel>,
    refreshing: Boolean,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primary,
                title = {
                    Text(
                        text = LocalContext.current.getString(R.string.app_name),
                        color = secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DP_16.dp),
                        style =  MaterialTheme.typography.titleMedium
                    )
                }
            )
        },
        content = { paddingValues ->
            LoadSportList(sports, refreshing, onRefresh, paddingValues)
        }
    )

}