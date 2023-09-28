package com.kodeco.android.coordplot.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

@Composable
fun MainScreen(
    onNavigateToCoordPlot: () -> Unit,
    onNavigateToCountryInfo: () -> Unit
) {
    CoordPlotTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { onNavigateToCoordPlot() }) {
                    Text(text = stringResource(id = R.string.app_name))
                }
                Button(onClick = { onNavigateToCountryInfo() }) {
                    Text(text = stringResource(id = R.string.country_info))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(
        onNavigateToCoordPlot = {},
        onNavigateToCountryInfo = {}
    )
}
