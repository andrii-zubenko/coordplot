package com.kodeco.android.coordplot.country_info.ui.screens.countrydetails

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
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.ui.components.DetailItem
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CountryDetailsScreen(
    onBackTap: () -> Unit,
    viewModel: CountryDetailsViewModel,
    countryIndex: Int
) {
    viewModel.getSelectedCountry(countryIndex)
    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { viewModel.selectedCountry.value?.commonName?.let { Text(text = it) } },
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackTap()
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
                    viewModel.selectedCountry.value?.mainCapital?.let {
                        DetailItem(
                            stringResource(R.string.capital),
                            it
                        )
                    }
                    DetailItem(
                        stringResource(R.string.population),
                        viewModel.selectedCountry.value?.population.toString()
                    )
                    DetailItem(
                        stringResource(R.string.area),
                        viewModel.selectedCountry.value?.area.toString()
                    )
                    AsyncImage(
                        model = viewModel.selectedCountry.value?.flagUrl ?: "",
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

    val testCountry = Country(
        commonName = "United States of America",
        mainCapital = "Washington, D.C.",
        population = 331449281,
        area = 9833520.0F,
        flagUrl = ""
    )

    CountryDetailsScreen(
        onBackTap = {},
        viewModel = CountryDetailsViewModel(
            repository = object : CountryRepository {
                override val countries: Flow<List<Country>> = MutableStateFlow(listOf(testCountry))

                override suspend fun fetchCountries() {}

                override fun getCountry(index: Int): Country = testCountry

                override suspend fun favorite(country: Country) {}
            },
        ),
        countryIndex = 0
    )
}
