package com.nikoarap.gametime.view.composables

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.nikoarap.gametime.models.SportModel

@Composable
fun LoadSportList(
    sports: List<SportModel>,
    refreshing: Boolean,
    onRefresh: () -> Unit
) {

    Toast.makeText(LocalContext.current, "SPORT ITEMS COUNT: " + sports.size, LENGTH_SHORT).show()
}