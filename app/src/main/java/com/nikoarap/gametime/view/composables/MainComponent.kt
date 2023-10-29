package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.NavBottomItem
import com.nikoarap.gametime.models.SportModel
import com.nikoarap.gametime.utils.Constants.Companion.ICON
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_18
import com.nikoarap.gametime.view.themes.dp_68
import com.nikoarap.gametime.view.themes.primary
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadMainComponent(
    sports: List<SportModel>,
    navBottomItems: List<NavBottomItem>,
    selectedItemIndex: Int,
) {
    Scaffold(
        topBar = { LoadHeader()},
        bottomBar = { LoadBottomNavBar(navBottomItems, selectedItemIndex)},
        content = { paddingValues ->
            LoadSportSections(sports, paddingValues)
        }
    )
}

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

@Composable
private fun LoadBottomNavBar(
    items: List<NavBottomItem>,
    selectedItemIndex: Int,
) {
    var selectedItem by remember { mutableIntStateOf(selectedItemIndex) }

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
                                contentDescription = ICON,
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
 * A composable function that creates custom colors for a selected NavigationBarItem.
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

@Composable
private fun LoadSportSections(
    sports: List<SportModel>,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .background(color = surface)
    ) {
        for (sport in sports) {
            LoadSportSection(sport)
        }
    }
}