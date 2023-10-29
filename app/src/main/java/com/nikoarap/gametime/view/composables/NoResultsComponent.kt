package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.Companion.IMAGE
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface

@Composable
fun LoadNoResultsView(
    modifier: Modifier,
    noResultsText: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.background(color = surface),
            contentDescription = IMAGE,
            painter = painterResource(R.drawable.no_results_placeholder),
        )
        Text(
            text = noResultsText,
            color = secondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}