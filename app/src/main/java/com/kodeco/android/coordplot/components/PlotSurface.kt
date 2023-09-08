package com.kodeco.android.coordplot.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme
import com.kodeco.android.coordplot.utils.toIntPercentage

@Composable
fun PlotSurface(screenOrientation: Int) {
    var xFloatValue: Float by rememberSaveable { mutableStateOf(0.5f) }
    var yFloatValue: Float by rememberSaveable { mutableStateOf(0.5f) }

    if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Map(xPercent = xFloatValue, yPercent = yFloatValue)
            MapSlider(
                floatValue = xFloatValue,
                onValueChanged = { xFloatValue = it },
                text = stringResource(
                    id = R.string.slider_axis_value,
                    stringResource(R.string.x_label),
                    xFloatValue.toIntPercentage()
                )
            )
            MapSlider(
                floatValue = yFloatValue,
                onValueChanged = { yFloatValue = it },
                text = stringResource(
                    id = R.string.slider_axis_value,
                    stringResource(R.string.y_label),
                    yFloatValue.toIntPercentage()
                )
            )
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Map(xPercent = xFloatValue, yPercent = yFloatValue)
            Column {
                MapSlider(
                    floatValue = xFloatValue,
                    onValueChanged = { xFloatValue = it },
                    text = stringResource(
                        id = R.string.slider_axis_value,
                        stringResource(R.string.x_label),
                        xFloatValue.toIntPercentage()
                    )
                )
                MapSlider(
                    floatValue = yFloatValue,
                    onValueChanged = { yFloatValue = it },
                    text = stringResource(
                        id = R.string.slider_axis_value,
                        stringResource(R.string.y_label),
                        yFloatValue.toIntPercentage()
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlotSurfacePreviewPortrait() {
    MyApplicationTheme {
        PlotSurface(Configuration.ORIENTATION_PORTRAIT)
    }
}

@Preview(showBackground = true,device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun PlotSurfacePreviewLandscape() {
    MyApplicationTheme {
        PlotSurface(Configuration.ORIENTATION_LANDSCAPE)
    }
}
