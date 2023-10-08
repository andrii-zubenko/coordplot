package com.kodeco.android.coordplot.country_info.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R

@Composable
fun CountersTopBar(taps: Int, backs: Int, onRefreshClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = stringResource(R.string.taps, taps))
        Button(onClick = {
            onRefreshClick()
        }) {
            Text(text = stringResource(R.string.refresh))
        }
        Text(text = stringResource(R.string.backs, backs))
    }
}

@Composable
@Preview(showBackground = true)
fun CountersPreview() {
    CountersTopBar(taps = 0, backs = 0, onRefreshClick = { })
}
