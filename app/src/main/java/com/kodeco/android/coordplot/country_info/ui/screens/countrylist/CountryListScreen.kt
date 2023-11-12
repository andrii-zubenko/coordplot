package com.kodeco.android.coordplot.country_info.ui.screens.countrylist

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.ui.components.CountryList
import com.kodeco.android.coordplot.country_info.networking.CountryListState
import com.kodeco.android.coordplot.country_info.ui.components.LoadingScreen
import com.kodeco.android.coordplot.country_info.ui.screens.settingsscreen.SettingsScreenViewModel

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel,
    onCountryRowTap: (Int) -> Unit,
    onAboutTap: () -> Unit,
    onSettingsTap: () -> Unit,
    settingsScreenViewModel: SettingsScreenViewModel
) {
    val state by viewModel.uiState.collectAsState()

    val transition = updateTransition(
        targetState = state,
        label = "list_state_transition",
    )

    transition.Crossfade(
        modifier = Modifier
            .fillMaxSize(),
        contentKey = { it.javaClass },
        animationSpec = tween(600)
    ) { state ->
        when (state) {
            is CountryListState.Success -> {
                CountryList(
                    countries = state.countryList,
                    onRefreshTap = viewModel::fetchCountryList,
                    onCountryRowTap = onCountryRowTap,
                    onAboutTap = { onAboutTap() },
                    onFavoriteTap = viewModel::favorite,
                    onSettingsTap = { onSettingsTap() },
                    settingsScreenViewModel = settingsScreenViewModel
                )
            }

            is CountryListState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.oops_something_is_not_right))
                }
            }

            CountryListState.Loading -> {
                LoadingScreen()
            }
        }
    }
}
