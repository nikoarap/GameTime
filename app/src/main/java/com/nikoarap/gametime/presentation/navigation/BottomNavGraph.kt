package com.nikoarap.gametime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikoarap.gametime.presentation.screenAllEvents.components.AllEventsScreen
import com.nikoarap.gametime.presentation.screenFavouriteEvents.components.FavouriteEventsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            AllEventsScreen()
        }
        composable(route = Screen.FavouritesScreen.route) {
            FavouriteEventsScreen()
        }
    }
}