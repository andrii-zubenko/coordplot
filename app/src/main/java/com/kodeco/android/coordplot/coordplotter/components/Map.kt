package com.kodeco.android.coordplot.coordplotter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

@Composable
fun Map(
    xPercent: Float,
    yPercent: Float
) {
    Box(
        modifier = Modifier
            .padding(18.dp)
            .size(300.dp)
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cursor_icon),
            contentDescription = stringResource(R.string.cursor_icon_desc),
            modifier = Modifier
                .size(36.dp)
                .offset(
                    x = (xPercent * 300 - 18).dp,
                    y = (yPercent * 300 - 18).dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    CoordPlotTheme {
        Map(xPercent = 0.5f, yPercent = 0.5f)
    }
}
