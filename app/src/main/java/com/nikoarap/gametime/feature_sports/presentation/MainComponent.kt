package com.nikoarap.gametime.feature_sports.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikoarap.gametime.R
import com.nikoarap.gametime.feature_sports.presentation.navigation.BottomNavGraph
import com.nikoarap.gametime.feature_sports.presentation.navigation.Screen
import com.nikoarap.gametime.utils.Constants.DESCRIPTION_ICON

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComponent() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.background(color = colorResource(id = R.color.surface)),
        topBar = { Header() },
        bottomBar = { BottomNavBar(navController) }
    ) {
        BottomNavGraph(navController)
    }
}

/**
 * Loads the bottom navigation bar for the main component.
 * @param navController NavHostController
 */
@Composable
private fun BottomNavBar(
    navController: NavHostController,
) {
    val screens = listOf(Screen.HomeScreen, Screen.FavouritesScreen)
    val containerColor = colorResource(id = R.color.primary)
    val tintColor = colorResource(id = R.color.surface)
    val selectedTintColor = colorResource(id = R.color.secondary)

    // Observe the current navigation route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.dp_68))
    ) {
        NavigationBar(
            containerColor = containerColor,
            content = {
                screens.forEach { screen ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route)
                        },
                        label = {
                            Text(text = stringResource(screen.labelResId),)
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(dimensionResource(id = R.dimen.dp_18)),
                                imageVector = screen.icon,
                                contentDescription = DESCRIPTION_ICON,
                                tint = if (isSelected) selectedTintColor else tintColor
                            )
                        },
                        colors = customNavigationBarItemColors(containerColor, selectedTintColor, tintColor)
                    )
                }
            }
        )
    }
}

/**
 * Loads the top app bar for the main component.
 */
@Composable
private fun Header() {
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
