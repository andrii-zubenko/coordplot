package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kodeco.android.coordplot.R

@Composable
fun RefreshButton(onRefreshTap: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                onRefreshTap()
            }
        ) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}