package com.nikoarap.gametime.feature_sports.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikoarap.gametime.R
import com.nikoarap.gametime.utils.Constants.ROUTE_FAVOURITES
import com.nikoarap.gametime.utils.Constants.ROUTE_HOME

sealed class Screen(
    val labelResId: Int,
    val route: String,
    val icon: ImageVector
) {
    object HomeScreen: Screen(
        labelResId = R.string.home,
        route = ROUTE_HOME,
        icon = Icons.Filled.Home
    )

    object FavouritesScreen: Screen(
        labelResId = R.string.favourites,
        route = ROUTE_FAVOURITES,
        icon = Icons.Filled.Star
    )
}