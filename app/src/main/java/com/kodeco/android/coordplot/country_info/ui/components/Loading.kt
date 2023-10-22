package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.model.CountryFlags
import com.kodeco.android.coordplot.country_info.model.CountryName
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun LoadingScreen(countryInfoViewModel: CountryInfoViewModel) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(
                    R.string.loading_app_up_time_seconds,
                    countryInfoViewModel.counter.value
                )
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun LoadingScreenPreview() {

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

    LoadingScreen(countryInfoViewModel = CountryInfoViewModel(testRepository))
}
