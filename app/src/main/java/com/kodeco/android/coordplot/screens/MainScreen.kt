package com.kodeco.android.coordplot.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.kodeco.android.coordplot.components.PlotSurface

@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current

    PlotSurface(configuration.orientation)
}