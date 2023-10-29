package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.ICON
import com.nikoarap.gametime.view.themes.dp_24
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.viewmodels.MainViewModel

@Composable
fun SwitchButton(
    viewModel: MainViewModel,
    sportModel: SportModel
) {
    // Initialize isSwitchChecked based on the value from Realm
    var isSwitchChecked by remember { mutableStateOf(sportModel.isFavourite) }

    // Create a snapshot of the Realm object
    val sportModelSnapshot = rememberUpdatedState(sportModel)

    // Update isSwitchChecked when the value in Realm changes to ensure that the isSwitchChecked stays up-to-date
    if (isSwitchChecked != sportModelSnapshot.value.isFavourite) {
        isSwitchChecked = sportModelSnapshot.value.isFavourite
    }
    Switch(
        checked = isSwitchChecked,
        onCheckedChange = {
            isSwitchChecked = it
            viewModel.onSportFavouriteChecked(sportModel, it)
        },
        thumbContent = {
            Icon(
                modifier = Modifier.size(dp_24),
                imageVector = Icons.Filled.Star,
                contentDescription = ICON,
                tint = secondary
            )
        },
        colors = getSwitchColors()
    )
}

@Composable
private fun getSwitchColors(): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = primary,
        uncheckedThumbColor = Color.Gray,
        checkedTrackColor = surface,
        uncheckedTrackColor = Color.Gray
    )
}