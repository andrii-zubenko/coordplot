package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.ui.theme.Black
import com.kodeco.android.coordplot.ui.theme.LightBlue
import com.kodeco.android.coordplot.ui.theme.White

@Composable
fun CountersTopBar(viewModel: CountersTopBarViewModel, onRefreshClick: () -> Unit) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = stringResource(R.string.taps, viewModel.taps))
            Text(text = stringResource(R.string.backs, viewModel.backs))
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
                    viewModel.setTheme(
                        if (viewModel.currentTheme == Theme.LIGHT) {
                            Theme.DARK
                        } else Theme.LIGHT
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (viewModel.currentTheme) {
                        Theme.LIGHT -> LightBlue
                        Theme.DARK -> Black
                    },
                    contentColor = White
                )
            ) {
                Text(
                    text = when (viewModel.currentTheme) {
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
    val testViewModel = CountersTopBarViewModel()
    CountersTopBar(viewModel = testViewModel, onRefreshClick = { })
}
