package com.nikoarap.gametime.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface

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
    val order: Int = 0,
    val label: String = EMPTY_STRING,
    val imageVector: ImageVector = Icons.Filled.Home,
    val tintColor: Color = surface,
    val selectedTintColor: Color = secondary,
    val onSelected: () -> Unit,
)