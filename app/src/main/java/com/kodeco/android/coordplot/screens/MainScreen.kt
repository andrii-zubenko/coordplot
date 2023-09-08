package com.kodeco.android.coordplot.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.kodeco.android.coordplot.components.PlotSurface
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current

    CoordPlotTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PlotSurface(configuration.orientation)
        }
    }
}
