package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nikoarap.gametime.view.themes.dp_4
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary

@Composable
fun TextWithBorder(text: String) {
    Box(
        modifier = Modifier
            .background(color = primary) // Border color
            .padding(dp_4) // Adjust the border padding as needed
            .then(Modifier.fillMaxSize()), // Expand to fill available space
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = secondary,
            modifier = Modifier
        )
    }
}