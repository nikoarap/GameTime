package com.nikoarap.gametime.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A data class representing an item for a navigation bar.
 *
 * @param order                     The order in which the item should appear in the menu.
 * @param label                     The label or text to display for the item.
 * @param imageVector               Image vector to be set as the item's icon
 * @param tintColor                 The tint color for the item's icon and label.
 * @param selectedTintColor         The color of the item's icon and label when is selected.
 * @param onSelected                A lambda function to handle the action when the item is selected.
 */
data class NavBottomItem(
    val order: Int,
    val label: String,
    val imageVector: ImageVector,
    val tintColor: Color = Color(0xFF343434),
    val selectedTintColor: Color = Color(0xFFFFFFFF),
    val onSelected: () -> Unit,
)