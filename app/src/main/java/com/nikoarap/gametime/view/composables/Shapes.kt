package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Dot(
    modifier: Modifier,
    circleSizeDp: Dp,
    backgroundColor: Color
) {
    Box(
        modifier = modifier
            .size(circleSizeDp)
            .clip(CircleShape)
            .background(backgroundColor)
    )
}