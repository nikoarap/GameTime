package com.nikoarap.gametime.presentation.components

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.nikoarap.gametime.R
import com.nikoarap.gametime.domain.model.SportModel
import com.nikoarap.gametime.utils.Constants.DESCRIPTION_ICON
import com.nikoarap.gametime.presentation.viewmodel.MainViewModel

/**
 * A composable function that displays a switch button for making a sport favorite.
 *
 * @param viewModel         The view model for managing the UI and data interaction.
 * @param sportModel        The sport model associated with the switch button.
 */
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
                modifier = Modifier.size(dimensionResource(id = R.dimen.dp_24)),
                imageVector = Icons.Filled.Star,
                contentDescription = DESCRIPTION_ICON,
                tint = colorResource(id = R.color.secondary)
            )
        },
        colors = getSwitchColors()
    )
}

/**
 * A private composable function that returns the colors for the switch button.
 *
 * @return The colors for the switch button.
 */
@Composable
private fun getSwitchColors(): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = colorResource(id = R.color.primary),
        uncheckedThumbColor = Color.LightGray,
        checkedTrackColor = colorResource(id = R.color.surface),
        uncheckedTrackColor = Color.Gray
    )
}