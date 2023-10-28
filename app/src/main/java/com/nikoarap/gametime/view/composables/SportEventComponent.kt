package com.nikoarap.gametime.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.nikoarap.gametime.R
import com.nikoarap.gametime.models.EventModel
import com.nikoarap.gametime.utils.Constants
import com.nikoarap.gametime.utils.Constants.Companion.VS_VALUE
import com.nikoarap.gametime.view.themes.dp_16
import com.nikoarap.gametime.view.themes.dp_8
import com.nikoarap.gametime.view.themes.onSurface
import com.nikoarap.gametime.view.themes.secondary
import com.nikoarap.gametime.view.themes.surface
import com.nikoarap.gametime.view.themes.tertiary

@Composable
fun LoadEventGridItem(
    event: EventModel
) {
    Column(
        modifier = Modifier
            .background(color = surface)
            .wrapContentSize()
            .padding(dp_8)
    ) {
        TextWithBorder(text = event.startTime.toString())
        Icon(
            modifier = Modifier.size(dp_16),
            painter = painterResource(id = R.drawable.ic_star_hollow),
            contentDescription = Constants.ICON,
            tint = onSurface
        )
        Text(
            text = event.name,
            style = MaterialTheme.typography.caption,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
        Text(
            text = VS_VALUE,
            style = MaterialTheme.typography.caption,
            color = tertiary,
            modifier = Modifier
        )
        Text(
            text = event.name,
            style = MaterialTheme.typography.caption,
            color = secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}