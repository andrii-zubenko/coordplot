package com.kodeco.android.coordplot.country_info.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.Flows.currentTheme
import com.kodeco.android.coordplot.country_info.Flows.setTheme
import com.kodeco.android.coordplot.country_info.Theme
import com.kodeco.android.coordplot.ui.theme.Black
import com.kodeco.android.coordplot.ui.theme.LightBlue

@Composable
fun CountersTopBar(taps: Int, backs: Int, countriesSelected: Int, onRefreshClick: () -> Unit) {
    val currentTheme = currentTheme.collectAsState()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = stringResource(R.string.taps, taps))
            Text(text = stringResource(R.string.backs, backs))
            Text(text = stringResource(R.string.unique_countries_selected, countriesSelected))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = {
                onRefreshClick()
            }) {
                Text(text = stringResource(R.string.refresh))
            }
            Button(
                onClick = {
                    setTheme(if (currentTheme.value == Theme.LIGHT) Theme.DARK else Theme.LIGHT)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (currentTheme.value) {
                        Theme.LIGHT -> LightBlue
                        Theme.DARK -> Black
                    },
                    contentColor = when (currentTheme.value) {
                        Theme.LIGHT -> Color.White
                        Theme.DARK -> Color.White
                    }

                )
            ) {
                Text(
                    text = when (currentTheme.value) {
                        Theme.LIGHT -> stringResource(R.string.go_dark)
                        Theme.DARK -> stringResource(R.string.go_light)
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CountersPreview() {
    CountersTopBar(taps = 0, backs = 0, countriesSelected = 0, onRefreshClick = { })
}
