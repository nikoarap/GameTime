package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.NavBottomItem
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants
import com.nikoarap.gametime.utils.Constants.Companion.NO_FAVOURITES_YET
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_18
import com.nikoarap.gametime.view.themes.dp_180
import com.nikoarap.gametime.view.themes.dp_68
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.viewmodels.MainViewModel

/**
 * A class representing the main UI component of the application.
 *
 * @param viewModel     The view model for managing the UI and data interaction.
 */
class MainComponent(private val viewModel: MainViewModel) {

    /**
     * Load the main component of the application.
     *
     * @param sports                The list of sports to display in the main component.
     * @param navBottomItems        The list of navigation bar items.
     * @param selectedItemIndex     The index of the selected item in the navigation bar.
     * @param isFavouriteView       Indicates whether the view is in favorites mode.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoadMainComponent(
        sports: List<SportModel>,
        navBottomItems: List<NavBottomItem>,
        selectedItemIndex: Int?,
        isFavouriteView: Boolean?
    ) {
        Scaffold(
            modifier = Modifier.background(color = surface),
            topBar = { LoadHeader()},
            bottomBar = { LoadBottomNavBar(navBottomItems, selectedItemIndex)},
            content = { paddingValues ->
                if (sports.isNotEmpty()) {
                    LoadSportSections(sports, paddingValues)
                } else {
                    LoadNoResultsView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = surface),
                        imageSizeDp = dp_180,
                        noResultsText = if (isFavouriteView == true) NO_FAVOURITES_YET else Constants.NO_RECORDS_SYNCED_YET
                    )
                }
            }
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
                    color = secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dp_16),
                    style =  MaterialTheme.typography.titleMedium
                )
            },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            backgroundColor = primary
        )
    }

    /**
     * Loads the bottom navigation bar for the main component.
     *
     * @param items                 The list of navigation bar items.
     * @param selectedItemIndex     The index of the selected item.
     */
    @Composable
    private fun LoadBottomNavBar(
        items: List<NavBottomItem>,
        selectedItemIndex: Int?,
    ) {
        var selectedItem by remember { mutableIntStateOf(selectedItemIndex?: VALUE_ZERO) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(dp_68)
        ) {
            NavigationBar(
                containerColor = primary,
                content = {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                item.onSelected()
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    color = if (selectedItem == index) item.selectedTintColor else item.tintColor
                                )
                            },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(dp_18),
                                    imageVector = item.imageVector,
                                    contentDescription = Constants.ICON,
                                    tint = if (selectedItem == index) item.selectedTintColor else item.tintColor
                                )
                            },
                            colors = customNavigationBarItemColors(primary, item.selectedTintColor, item.tintColor)
                        )
                    }
                }
            )
        }
    }

    /**
     * Load the sport sections in the main component.
     *
     * @param sports            The list of sports to display.
     * @param paddingValues     The padding values for the content.
     */
    @Composable
    private fun LoadSportSections(
        sports: List<SportModel>,
        paddingValues: PaddingValues
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = surface)
        ) {
            for (sport in sports) {
                LoadSportSection(viewModel, sport)
            }
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
}