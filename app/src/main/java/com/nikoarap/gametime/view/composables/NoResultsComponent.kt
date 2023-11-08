package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.Companion.DESCRIPTION_IMAGE
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_8
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface

/**
 * A composable function to display a "No Results" view.
 *
 * @param modifier          The modifier for this composable.
 * @param imageSizeDp       The image size of this composable in DP.
 * @param noResultsText     The text to display as a message when there are no results.
 */
@Composable
fun LoadNoResultsView(
    modifier: Modifier,
    imageSizeDp: Dp,
    noResultsText: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = dp_8)
                .background(color = surface)
                .size(imageSizeDp),
            contentDescription = DESCRIPTION_IMAGE,
            painter = painterResource(R.drawable.no_results_placeholder),
        )
        Text(
            modifier = Modifier.padding(horizontal = dp_16, vertical = dp_8),
            text = noResultsText,
            color = secondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}