package com.kodeco.android.coordplot.country_info.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.Flows.tapBack
import com.kodeco.android.coordplot.country_info.components.CountersTopBar
import com.kodeco.android.coordplot.country_info.components.DetailItem
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.model.CountryFlags
import com.kodeco.android.coordplot.country_info.model.CountryName

@Composable
fun CountryDetailsScreen(
    countryData: List<Country>,
    countryIndex: Int,
    onBackClicked: () -> Unit,
    countersTopBar: @Composable () -> Unit
) {
    Column {
        countersTopBar()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = countryData[countryIndex].name.common) },
                    navigationIcon = {
                        IconButton(onClick = {
                            tapBack()
                            onBackClicked()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DetailItem(
                        stringResource(R.string.capital),
                        countryData[countryIndex].capital?.get(0).toString()
                    )
                    DetailItem(
                        stringResource(R.string.population),
                        countryData[countryIndex].population.toString()
                    )
                    DetailItem(
                        stringResource(R.string.area),
                        countryData[countryIndex].area.toString()
                    )
                    AsyncImage(
                        model = countryData[countryIndex].flags.png,
                        contentDescription = stringResource(R.string.country_flag),
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CountryDetailsScreenPreview() {
    val data = listOf(
        Country(
            name = CountryName(common = "United States of America"),
            capital = listOf("Washington, D.C."),
            population = 331449281,
            area = 9833520.0,
            flags = CountryFlags("")
        )
    )

    CountryDetailsScreen(countryData = data,
        countryIndex = 0,
        onBackClicked = {},
        countersTopBar = {
            CountersTopBar(taps = 0, backs = 0, countriesSelected = 0, onRefreshClick = { })
        })
}
