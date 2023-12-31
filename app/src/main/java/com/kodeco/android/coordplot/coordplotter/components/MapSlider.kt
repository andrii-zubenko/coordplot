package com.kodeco.android.coordplot.coordplotter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

@Composable
fun MapSlider(
    floatValue: Float,
    onPercentageChanged: (Float) -> Unit,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = text,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 20.sp),
            modifier = Modifier
            .padding(start = 8.dp)
            .width(120.dp))
        Slider(
            value = floatValue,
            onValueChange = onPercentageChanged
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapSliderPreview() {
    CoordPlotTheme {
        MapSlider(floatValue = 0.5f, onPercentageChanged = {}, text = "Slider desc")
    }
}
