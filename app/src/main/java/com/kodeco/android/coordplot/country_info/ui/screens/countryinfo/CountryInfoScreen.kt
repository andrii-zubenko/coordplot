package com.kodeco.android.coordplot.country_info.ui.screens.countryinfo

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.ui.components.CountryList
import com.kodeco.android.coordplot.country_info.networking.CountryInfoState
import com.kodeco.android.coordplot.country_info.ui.components.LoadingScreen

@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel,
    onCountryRowTap: (Int) -> Unit,
    onAboutTap: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (state) {
        is CountryInfoState.Success -> {
            CountryList(
                countries = (state as CountryInfoState.Success).countryList,
                onRefreshTap = { viewModel.fetchCountryList() },
                onCountryRowTap = { countryIndex ->
                    onCountryRowTap(countryIndex)
                },
                onAboutTap = { onAboutTap() },
                onFavoriteTap = { countryIndex ->
                    val country = viewModel.getCountryById(countryIndex)
                    if (country != null) {
                        viewModel.favorite(country)
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry. Can't favorite this country.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }

        is CountryInfoState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.oops_something_is_not_right))
            }
        }

        CountryInfoState.Loading -> {
            LoadingScreen()
        }
    }
}
