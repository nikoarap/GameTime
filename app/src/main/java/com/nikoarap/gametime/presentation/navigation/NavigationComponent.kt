package com.nikoarap.gametime.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikoarap.gametime.R
import com.nikoarap.gametime.presentation.screenAllEvents.components.AllEventsScreen
import com.nikoarap.gametime.presentation.screenFavouriteEvents.components.FavouriteEventsScreen
import com.nikoarap.gametime.utils.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationComponent(

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Router.AllEventsRouter.route
    ) {
        composable(
            route = Router.AllEventsRouter.route
        ) {
            AllEventsScreen()
        }
        composable(
            route = Router.FavouriteEventsRouter.route
        ) {
            FavouriteEventsScreen()
        }
    }

    Scaffold(
        modifier = Modifier.background(color = colorResource(id = R.color.surface)),
        topBar = { LoadHeader() },
        bottomBar = { LoadBottomNavBar(navController) },
        content = { paddingValues -> (paddingValues) }
    )
}

/**
 * Loads the top app bar for the main component.
 */
@Composable
private fun LoadHeader() {
    TopAppBar(
        title = {
            Text(
                text = LocalContext.current.getString(R.string.app_name),
                color = colorResource(id = R.color.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.dp_16)),
                style =  MaterialTheme.typography.titleMedium
            )
        },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.primary)
    )
}

/**
 * Loads the bottom navigation bar for the main component.
 * @param navController NavController
 */
@Composable
private fun LoadBottomNavBar(
    navController: NavController,
) {
    val containerColor = colorResource(id = R.color.primary)
    val tintColor = colorResource(id = R.color.surface)
    val selectedTintColor = colorResource(id = R.color.secondary)

    // Observe the current navigation route
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.dp_68))
    ) {
        NavigationBar(
            containerColor = containerColor,
            content = {
                val homeSelected = navBackStackEntry?.destination?.route == Router.AllEventsRouter.route
                val favouritesSelected = navBackStackEntry?.destination?.route == Router.FavouriteEventsRouter.route
                NavigationBarItem(
                    selected = homeSelected,
                    onClick = {
                        navController.navigate(Router.AllEventsRouter.route)
                    },
                    label = {
                        Text(
                            text = LocalContext.current.resources.getString(R.string.home),
                            color = if (homeSelected) selectedTintColor else tintColor
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(dimensionResource(id = R.dimen.dp_18)),
                            imageVector = Icons.Filled.Home,
                            contentDescription = Constants.DESCRIPTION_ICON,
                            tint = if (homeSelected) selectedTintColor else tintColor
                        )
                    },
                    colors = customNavigationBarItemColors(containerColor, selectedTintColor, tintColor)
                )
                NavigationBarItem(
                    selected = favouritesSelected,
                    onClick = {
                        navController.navigate(Router.FavouriteEventsRouter.route)
                    },
                    label = {
                        Text(
                            text = LocalContext.current.resources.getString(R.string.favourites),
                            color = if (favouritesSelected) selectedTintColor else tintColor
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(dimensionResource(id = R.dimen.dp_18)),
                            imageVector = Icons.Filled.Star,
                            contentDescription = Constants.DESCRIPTION_ICON,
                            tint = if (favouritesSelected) selectedTintColor else tintColor
                        )
                    },
                    colors = customNavigationBarItemColors(containerColor, selectedTintColor, tintColor)
                )
            }
        )
    }
}

/**
 * Create custom colors for a selected NavigationBarItem.
 *
 * @param containerColor        The color of the navigation bar container.
 * @param selectedColor         The color for the selected item.
 * @param unselectedColor       The color for unselected items.
 *
 * @return A [NavigationBarItemDefaults] object with the specified custom color settings.
 */
@Composable
private fun customNavigationBarItemColors(
    containerColor: Color,
    selectedColor: Color,
    unselectedColor: Color
): NavigationBarItemColors {
    return NavigationBarItemDefaults.colors(
        selectedIconColor = selectedColor,
        selectedTextColor = selectedColor,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        indicatorColor = containerColor
    )
}
