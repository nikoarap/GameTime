package com.nikoarap.gametime.presentation.navigation

sealed class Router(val route: String) {
    object AllEventsRouter: Router("all_events_screen")
    object FavouriteEventsRouter: Router("favourite_events_screen")
}