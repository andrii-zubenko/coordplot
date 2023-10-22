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
import com.kodeco.android.coordplot.country_info.ui.components.DetailItem
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.model.CountryFlags
import com.kodeco.android.coordplot.country_info.model.CountryName
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CountryDetailsScreen(
    onBackClicked: () -> Unit,
    viewModel: CountryDetailsViewModel,
) {
    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { viewModel.selectedCountry.value?.name?.let { Text(text = it.common) } },
                    navigationIcon = {
                        IconButton(onClick = {
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
                        viewModel.selectedCountry.value?.capital?.get(0).toString()
                    )
                    DetailItem(
                        stringResource(R.string.population),
                        viewModel.selectedCountry.value?.population.toString()
                    )
                    DetailItem(
                        stringResource(R.string.area),
                        viewModel.selectedCountry.value?.area.toString()
                    )
                    AsyncImage(
                        model = viewModel.selectedCountry?.value?.flags?.png,
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
        name = CountryName(common = "United States of America"),
        capital = listOf("Washington, D.C."),
        population = 331449281,
        area = 9833520.0,
        flags = CountryFlags("")
    )

    val testRepository = object : CountryRepository {
        override suspend fun fetchCountries(refreshNeeded: Boolean): Flow<List<Country>> {
            return flow {
                emit(listOf(testCountry))
            }
        }

        override fun getCountry(countryIndex: Int): Country? {
            return testCountry
        }
    }

    CountryDetailsScreen(
        onBackClicked = {},
        viewModel = CountryDetailsViewModel(0, testRepository)
    )
}
