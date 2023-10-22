package com.kodeco.android.coordplot.country_info.ui.screens.countryinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.ui.components.CountryList
import com.kodeco.android.coordplot.country_info.networking.CountryInfoState
import com.kodeco.android.coordplot.country_info.ui.components.CountersTopBarViewModel
import com.kodeco.android.coordplot.country_info.ui.components.LoadingScreen

@Composable
fun CountryInfoScreen(
    navigation: NavController,
    countersTopBar: @Composable () -> Unit,
    viewModel: CountryInfoViewModel,
    countersTopBarViewModel: CountersTopBarViewModel
) {
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is CountryInfoState.Success -> {
            CountryList(
                countries = (state.value as CountryInfoState.Success).countryList,
                navigation = navigation,
                countersTopBar = { countersTopBar() },
                countersTopBarViewModel = countersTopBarViewModel
            )
        }

        is CountryInfoState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.oops_something_is_not_right))
            }
        }

        CountryInfoState.Loading -> {
            LoadingScreen(countryInfoViewModel = viewModel)
        }
    }
}